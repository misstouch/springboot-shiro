package com.misstouch.shirolearn.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author hechao
 * @date create in 11:19 2018/5/8/008
 */
@Configuration
@PropertySource("classpath:jdbc.properties")
public class DruidDBConfig {
    @Value("${datasource.name}")
    private String name;

    @Value("${datasource.dbUrl}")
    private String dbUrl;

    @Value("${datasource.username}")
    private String username;

    @Value("${datasource.password}")
    private String password;

    @Value("${datasource.driverClassName}")
    private String driverClassName;

    @Value("${datasource.initialSize}")
    private int initialSize;

    @Value("${datasource.minIdle}")
    private int minIdle;

    @Value("${datasource.maxActive}")
    private int maxActive;

    @Value("${datasource.maxWait}")
    private int maxWait;

    @Value("${datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${datasource.validationQuery}")
    private String validationQuery;

    @Value("${datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${datasource.filters}")
    private String filters;

    @Value("{datasource.connectionProperties}")
    private String connectionProperties;

    public DataSource setDataSource(String driverClassName,String dburl,String username,String password){
        DruidDataSource datasource = new DruidDataSource();

        datasource.setName(name);
        datasource.setUrl(dburl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        // configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        datasource.setConnectionProperties(connectionProperties);
        return datasource;

    }

    @Bean
    @Primary
    DataSource dataSource(){
        return setDataSource(driverClassName,dbUrl,username,password);
    }

}
