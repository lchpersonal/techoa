package com.tech.oa.env;

import org.activiti.spring.annotations.EnableActiviti;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
@EnableActiviti
public class ActivitiConfig {

 /*   @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<? extends Driver>) Class.forName("com.mysql.jdbc.Driver"));
        dataSource.setUrl("jdbc:mysql://10.18.20.5:3306/activiti?autoReconnect=true");
        dataSource.setUsername("fourbdev");
        dataSource.setPassword("fbdevadmin");
        return dataSource;
    }*/
}