package com.bigdata.springbootkafkaflink.flink;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.bigdata.springbootkafkaflink.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.calcite.shaded.com.google.common.collect.Lists;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
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
import java.util.List;
import java.util.Properties;

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
        handlerMulti();
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
