package com.dtinone.datashare.common.config;
import com.cdjiamigu.datasource.common.pool.DbConnectPool;
import com.cdjiamigu.datasource.common.pool.impl.DbConnectPoolImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Long on 2020/6/12.
 */
@Configuration
public class BeanConfig {

    @Bean
    public DbConnectPool dbConnectPool() {
        return new DbConnectPoolImpl();
    }
}
