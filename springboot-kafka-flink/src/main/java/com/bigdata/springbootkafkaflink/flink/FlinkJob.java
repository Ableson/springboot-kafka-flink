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
        //???????????????
        sourceStreamTra.addSink(new MySQLSink());
        try {
            env.execute("Flink add kafka data source");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void handlerKafkaData(){
        for (;;) {
            try {
                //??????poll(5000)??? ???????????????????????? ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????5s??????????????????????????????????????????????????????
                msgList = consumer.poll(100);
                if (null != msgList && !msgList.isEmpty()) {
                    String msg = "";
                    List<User> list=new ArrayList<User>();
                    for (ConsumerRecord<String, String> record : msgList) {
                        // ????????????
                        msg = record.value();
                        if (null == msg || "".equals(msg.trim())) continue;
                        try{
                            list.add(JSON.parseObject(msg, User.class));
                        }catch(Exception e){
                            log.error("??????????????????!??????:{}",msg);
                            continue;
                        }
                    }
                    log.info("Spout???????????????:"+list);
                    //TODO Flink ??????kafka??????
                    consumer.commitAsync();
                }else{
                    TimeUnit.SECONDS.sleep(3);
                    log.info("??????????????????...");
                }
            } catch (Exception e) {
                log.error("????????????????????????!", e);
                try {TimeUnit.SECONDS.sleep(10);} catch (InterruptedException e1) {log.error("????????????!",e1);}
            }
        }
    }

    /**
     * ?????????kafka??????
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
//        log.info("????????????[" + topic + "] ???????????????...");
//        return props;
//    }
}


