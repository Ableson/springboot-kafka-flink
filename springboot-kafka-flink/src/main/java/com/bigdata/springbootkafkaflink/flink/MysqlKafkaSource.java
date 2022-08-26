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
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        Properties properties = new Properties();
        properties.setProperty("topic", topic);
        properties.setProperty("bootstrap.servers", kafkaServer);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ObjectDeserializer.class.getName());

        DataStream<String> messageStream = env.addSource(new FlinkKafkaConsumer<String>(properties.getProperty("topic"),new SimpleStringSchema(), properties));
        DataStream<Tuple3<Integer, String, Integer>> dataStreamSource = messageStream.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String value) throws Exception {
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
        dataStreamSource.addSink((SinkFunction) applicationContext.getBean("mySQLSink"));
        //启动任务
        new Thread(() -> {
            try {
                env.execute("job");
            } catch (Exception e) {
                log.error(e.toString(), e);
            }
        }).start();
    }
}
