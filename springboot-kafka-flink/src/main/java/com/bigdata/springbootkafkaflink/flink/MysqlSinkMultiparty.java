package com.bigdata.springbootkafkaflink.flink;

import com.bigdata.springbootkafkaflink.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *多条处理
 * @author 15011
 */
@Component
@Slf4j
public class MysqlSinkMultiparty extends RichSinkFunction<List<User>> {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;
    String username = "root";
    String password = "root";
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
    public void invoke(List<User> value, Context context) throws Exception {
        super.invoke(value,context);
        value.forEach(o->{
            try {
                preparedStatement.setString(1, o.getName());
                preparedStatement.setInt(2, o.getAge());
                preparedStatement.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        preparedStatement.executeBatch();
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

