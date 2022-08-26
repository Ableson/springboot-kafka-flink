package com.bigdata.springbootkafkaflink.flink;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Component
@Slf4j
public class MySQLSink extends RichSinkFunction<Tuple3<Integer, String, Integer>> {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;
    String username = "root";
    String password = "root";
    String drivername = "com.mysql.jdbc.Driver";
    String dburl = "jdbc:mysql://localhost:3306/springBoot2?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&useSSL=false";

    @Override
    public void open(Configuration parameters) throws Exception {
        // super.open(parameters);
        log.info("获取数据库连接");
        connection = DriverManager.getConnection(dburl, username, password);
    }

    @Override
    public void invoke(Tuple3<Integer, String, Integer> value, Context context) throws Exception {
        super.invoke(value, context);
        Class.forName(drivername);

        String sql = "replace into user(id,name,age) values(?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, value.f0.toString());
        preparedStatement.setString(2, value.f1.toString());
        preparedStatement.setString(3, value.f2.toString());
        preparedStatement.executeUpdate();
        log.info("写入数据成功!");
    }

    @Override
    public void close() throws Exception {
        //关闭并释放资源
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
        log.info("连接关闭!");
    }
}

