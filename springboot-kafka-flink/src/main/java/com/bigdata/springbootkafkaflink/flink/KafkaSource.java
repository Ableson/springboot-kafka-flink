package com.bigdata.springbootkafkaflink.flink;

import com.alibaba.fastjson.JSON;
import com.bigdata.springbootkafkaflink.config.ApplicationConfiguration;
import com.bigdata.springbootkafkaflink.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Properties;

/**
 * 从kafka读取数据，计算设备状态为离线后写入mysql
 *
 * @author wangfenglei
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "customer.flink.cal-device-status", havingValue = "true", matchIfMissing = false)
public class KafkaSource {
    @Value("${spring.kafka.bootstrap-servers:localhost:9092}")
    private String kafkaServer;
    @Value("${spring.kafka.properties.sasl.jaas.config}")
    private String loginConfig;
    @Value("${customer.flink.cal-device-status-topic}")
    private String topic;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    ApplicationConfiguration app;
    private KafkaConsumer<String, String> consumer;
    /**
     * 执行方法
     *
     * @throws Exception 异常
     */
    @PostConstruct
    public void execute() throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(5000);
        env.setParallelism(1);
        Properties properties = kafkaInit();

        FlinkKafkaConsumer010<String> myConsumer = new FlinkKafkaConsumer010<>(topic, new SimpleStringSchema(), properties);

        DataStream<String> stream = env.addSource(myConsumer);
        stream.print().setParallelism(1);

        DataStream<Tuple3<Integer, String, Integer>> sourceStreamTra = stream.filter(new FilterFunction<String>() {
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
        sourceStreamTra.addSink(new MySQLSink());
        env.execute("Flink add kafka data source");

    }

    /**
     * 初始化kafka配置
     */
    private Properties kafkaInit(){
        Properties props = new Properties();
        props.put("bootstrap.servers", app.getServers());
        props.put("max.poll.records", app.getMaxPollRecords());
        props.put("enable.auto.commit", app.getAutoCommit());
        props.put("group.id", app.getGroupId());
        props.put("auto.offset.reset", app.getCommitRule());
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        String topic=app.getTopicName();
        this.consumer.subscribe(Arrays.asList(topic));
        log.info("消息队列[" + topic + "] 开始初始化...");
        return props;
    }
}