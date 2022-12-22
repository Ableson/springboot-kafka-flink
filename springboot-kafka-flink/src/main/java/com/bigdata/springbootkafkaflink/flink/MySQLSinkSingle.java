package com.bigdata.springbootkafkaflink.flink;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *单条处理
 */
@Component
@Slf4j
public class MySQLSinkSingle extends RichSinkFunction<Tuple3<Integer, String, Integer>> {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;
    String username = "root";
    String password = "root";
    String drivername = "com.mysql.jdbc.Driver";
    String dburl = "jdbc:mysql://localhost:3306/springBoot2?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&useSSL=false";

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        log.info("获取数据库连接");
        connection = DriverManager.getConnection(dburl, username, password);
        String sql = "replace into t_user(name,age) values(?,?);";
        preparedStatement = connection.prepareStatement(sql);
    }

    @Override
    public void invoke(Tuple3<Integer, String, Integer> value, Context context) throws Exception {
        super.invoke(value, context);
        preparedStatement.setString(1, value.f1);
        preparedStatement.setInt(2, value.f2);
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

