package com.bigdata.springbootkafkaflink.flink;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.bigdata.springbootkafkaflink.flinkTrigger.CountWithTimeoutTrigger;
import com.bigdata.springbootkafkaflink.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.calcite.shaded.com.google.common.collect.Lists;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.AllWindowedStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * FlatMap算子：将数据流一行按逻辑或规则拆分成0行或多行输出
 * 数据流flatMap -> FlatMapFunction 可以单独一个实现类，也可匿名内部类得方式
 * 数据流map --> MapFunction
 *
 * TimeCharacteristic --> https://blog.csdn.net/MyySophia/article/details/120706790
 * Event Time       事件时间，事件(Event)本身的时间，即数据流中事件实际发生的时间
 * Ingestion Time   摄入时间，事件进入Flink的时间，即将每一个事件在数据源算子的处理时间作为事件时间的时间戳
 * Processing Time  处理时间，根据处理机器的系统时钟决定数据流当前的时间，即事件被处理时当前系统的时间
 *
 * RichFunction     存在生命周期得概念 open close方法得存在
 * keyBy            类似于groupBy得作用，对数据进行分流
 *
 * 使用大致步骤
 * 1.evn.addSource(...);
 * 2.dataStream.map(...);
 * 3.outStream.apply(...);//timeWindowAll->trigger->apply
 * 4.apply.addSink(...);
 * 5.env.execute(...);
 *
 * Side OutPut替代split分流-->https://blog.csdn.net/weixin_42642502/article/details/106586255
 */
@Component
@Slf4j
public class MysqlKafkaSource {

    @Value("${kafka.servers}")
    private String kafkaServer;
    @Value("${kafka.topicName}")
    private String topic;
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void execute(){

//        handlerSingle();
//        handlerMulti();
//        handlerMultiTrigger();
        handlerMultiKeyBy();
    }

    private void handlerSingle(){
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        Properties properties = getProperties();

        DataStream<String> messageStream = env.addSource(new FlinkKafkaConsumer<>(properties.getProperty("topic"),new SimpleStringSchema(), properties));
        DataStream<Tuple3<Integer, String, Integer>> dataStreamSource = messageStream.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String value) {
                return StringUtils.isNotBlank(value);
            }
        }).map(new MapFunction<String, Tuple3<Integer, String, Integer>>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Tuple3<Integer, String, Integer> map(String value) {
                User user   = JSON.parseObject(value,User.class);
                return new Tuple3<>(user.getId(), user.getName(), user.getAge());
            }
        });
        //写入数据库
        dataStreamSource.addSink((SinkFunction) applicationContext.getBean("mySQLSinkSingle"));
        //启动任务
        startTask(env);
    }

    /**
     * 按时间段处理数据
     * 一分钟处理
     */
    private void handlerMulti(){
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        Properties properties = getProperties();
        DataStream<User> messageStream = env.addSource(new FlinkKafkaConsumer<>(properties.getProperty("topic"),new SimpleStringSchema(), properties)).map(string->JSON.parseObject(string,User.class));
        DataStream<User> dataStreamSource = messageStream.filter((FilterFunction<User>) user -> user.getAge()>18);
        SingleOutputStreamOperator<List<User>> apply = dataStreamSource.timeWindowAll(Time.minutes(1)).apply(new AllWindowFunction<User, List<User>, TimeWindow>() {
            @Override
            public void apply(TimeWindow timeWindow, Iterable<User> iterable, Collector<List<User>> collector) {
                List<User> users = Lists.newArrayList(iterable);
                if (users.size() > 0) {
                    log.info("1分钟收集到得条数：" + users.size());
                    collector.collect(users);
                }
            }
        });
        //写入数据库
        apply.addSink(new MysqlSinkMultiparty());
        //启动任务
        startTask(env);
    }

    /**
     * 10s最多收集3个提交一次
     */
    private void handlerMultiTrigger(){
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        Properties properties = getProperties();
        DataStream<User> messageStream = env.addSource(new FlinkKafkaConsumer<>(properties.getProperty("topic"),new SimpleStringSchema(), properties)).map(string->JSON.parseObject(string,User.class));
        //=↓=分流=↓=
        //=↓=分流=↓=
//        SplitStream<User> split = messageStream.split(user -> {
//            List<String> list = new ArrayList<>();
//            if (user.getAge() > 20) {
//                list.add("大于20");
//            } else {
//                list.add("小于20");
//            }
//            return list;
//        });
//        split.select("小于20").print();
//        log.info("-------");
//        split.select("大于20").printToErr();
        //=↑=分流=↑=
        //=↑=分流=↑=
        SingleOutputStreamOperator<User> singleOutputStreamOperator = messageStream.flatMap(new FlatMapFunction<User, User>() {
            @Override
            public void flatMap(User user, Collector<User> collector) {
                collector.collect(user);
            }
        });
        //keyBy
        //=↓=分组=↓=
        //=↓=分组=↓=
//        SingleOutputStreamOperator<User> sum = singleOutputStreamOperator.keyBy("age").reduce((o1,o2)->{
//            log.info(o1+"===="+o2);
//            User user = new User();
//            user.setAge(o1.getAge()+o2.getAge());
//            user.setName(o2.getName());
//            log.info(">>:"+user);
//            return user;
//        });
        //=↑=分组=↑=singleOutputStreamOperator需要改为sum
        //=↑=分组=↑=
        //对前10s内的输入数据流超过10条，提交一次
//        AllWindowedStream<User, TimeWindow> trigger = singleOutputStreamOperator.keyBy("age").sum("age").timeWindowAll(Time.seconds(10)).trigger(new CountWithTimeoutTrigger<>(3, TimeCharacteristic.ProcessingTime));
        AllWindowedStream<User, TimeWindow> trigger = singleOutputStreamOperator.timeWindowAll(Time.seconds(10)).trigger(new CountWithTimeoutTrigger<>(3, TimeCharacteristic.ProcessingTime));
        SingleOutputStreamOperator<List<User>> apply = trigger.apply(new AllWindowFunction<User, List<User>, TimeWindow>() {
            @Override
            public void apply(TimeWindow timeWindow, Iterable<User> iterable, Collector<List<User>> collector) {
                List<User> users = Lists.newArrayList(iterable);
                if(users.size() > 0){
                    log.info("10秒收集到得条数：" + users.size());
                    collector.collect(users);
                }
            }
        });
        //写入数据库
        apply.addSink(new MysqlSinkMultiparty());
        //启动任务
        startTask(env);
    }

    /**
     * 分流/分组统计数据 按age分组累加
     */
    private void handlerMultiKeyBy(){
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        Properties properties = getProperties();
        DataStream<User> messageStream = env.addSource(new FlinkKafkaConsumer<>(properties.getProperty("topic"),new SimpleStringSchema(), properties)).map(string->JSON.parseObject(string,User.class));
        //=↓=分流=↓=
        //=↓=分流=↓=
        SplitStream<User> split = messageStream.split(user -> {
            List<String> list = new ArrayList<>();
            if (user.getAge() > 20) {
                list.add("大于20");
            } else {
                list.add("小于20");
            }
            return list;
        });
        split.select("小于20").print();
        log.info("-------");
        split.select("大于20").printToErr();
        //=↑=分流=↑=
        //=↑=分流=↑=
        SingleOutputStreamOperator<User> singleOutputStreamOperator = messageStream.flatMap(new FlatMapFunction<User, User>() {
            @Override
            public void flatMap(User user, Collector<User> collector) {
                collector.collect(user);
            }
        });
        //keyBy
        //=↓=分组=↓=会按照年龄 累加并使用新数据/后者数据得name
        //=↓=分组=↓=
        SingleOutputStreamOperator<User> reduce = singleOutputStreamOperator.keyBy("age").reduce((o1,o2)->{
            log.info(o1+"===="+o2);
            User user = new User();
            user.setAge(o1.getAge()+o2.getAge());
            user.setName(o2.getName());
            log.info(">>:"+user);
            return user;
        });
        //=↑=分组=↑=
        //=↑=分组=↑=
        //对前10s内的输入数据流超过10条，提交一次 trigger = singleOutputStreamOperator.keyBy("age").sum("age").timeWindowAll(Time.seconds(10))
        AllWindowedStream<User, TimeWindow> trigger = reduce.timeWindowAll(Time.seconds(10)).trigger(new CountWithTimeoutTrigger<>(3, TimeCharacteristic.ProcessingTime));
        SingleOutputStreamOperator<List<User>> apply = trigger.apply(new AllWindowFunction<User, List<User>, TimeWindow>() {
            @Override
            public void apply(TimeWindow timeWindow, Iterable<User> iterable, Collector<List<User>> collector) {
                List<User> users = Lists.newArrayList(iterable);
                if(users.size() > 0){
                    log.info("10秒收集到得条数：" + users.size());
                    collector.collect(users);
                }
            }
        });
        //写入数据库
        apply.addSink(new MysqlSinkMultiparty());
        //启动任务
        startTask(env);
    }
    private void startTask(StreamExecutionEnvironment env) {
        new Thread(() -> {
            try {
                env.execute("job");
            } catch (Exception e) {
                log.error(e.toString(), e);
            }
        }).start();
    }
    private Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("topic", topic);
        properties.setProperty("bootstrap.servers", kafkaServer);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ObjectDeserializer.class.getName());
        return properties;
    }
}
