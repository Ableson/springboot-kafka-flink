package com.bigdata.springbootkafkaflink.flink;

import com.alibaba.fastjson.JSON;
import com.bigdata.springbootkafkaflink.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Slf4j
public class FlinkJob {

    private ConsumerRecords<String, String> msgList;
    private KafkaConsumer<String, String> consumer;

    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.109.134:9093,192.168.109.134:9094,192.168.109.134:9095");
        props.put("max.poll.records", 100);
        props.put("enable.auto.commit", false);
        props.put("group.id", "groupA");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        FlinkKafkaConsumer010<String> consumer = new FlinkKafkaConsumer010<>("TEST_01", new SimpleStringSchema(), props);
        DataStreamSource<String> dataStreamSource = env.addSource(consumer).setParallelism(1);
        DataStream<Tuple3<Integer, String, Integer>> sourceStreamTra = dataStreamSource.filter(new FilterFunction<String>() {
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
        //传入数据库
        sourceStreamTra.addSink(new MySQLSinkSingle());
        try {
            env.execute("Flink add kafka data source");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void handlerKafkaData(){
        for (;;) {
            try {
                //例如poll(5000)： 如果拉到数据的话 会立即放回；如果拉不到数据的话，这个是最长的等待时间；如果一直没有数据的话，每5s拉一次返回一次，有数据就立即返回再拉
                msgList = consumer.poll(100);
                if (null != msgList && !msgList.isEmpty()) {
                    String msg = "";
                    List<User> list=new ArrayList<User>();
                    for (ConsumerRecord<String, String> record : msgList) {
                        // 原始数据
                        msg = record.value();
                        if (null == msg || "".equals(msg.trim())) continue;
                        try{
                            list.add(JSON.parseObject(msg, User.class));
                        }catch(Exception e){
                            log.error("数据格式不符!数据:{}",msg);
                            continue;
                        }
                    }
                    log.info("Spout发射的数据:"+list);
                    //TODO Flink 处理kafka数据
                    consumer.commitAsync();
                }else{
                    TimeUnit.SECONDS.sleep(3);
                    log.info("未拉取到数据...");
                }
            } catch (Exception e) {
                log.error("消息队列处理异常!", e);
                try {TimeUnit.SECONDS.sleep(10);} catch (InterruptedException e1) {log.error("暂停失败!",e1);}
            }
        }
    }

    /**
     * 初始化kafka配置
     */
//    private static Properties kafkaInit(){
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "192.168.109.134:9093,192.168.109.134:9094,192.168.109.134:9095");
//        props.put("max.poll.records", 100);
//        props.put("enable.auto.commit", false);
//        props.put("group.id", "groupA");
//        props.put("auto.offset.reset", "earliest");
//        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        consumer = new KafkaConsumer<String, String>(props);
//        String topic=app.getTopicName();
//        this.consumer.subscribe(Arrays.asList(topic));
//        log.info("消息队列[" + topic + "] 开始初始化...");
//        return props;
//    }
}


