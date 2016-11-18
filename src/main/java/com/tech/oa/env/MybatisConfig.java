package com.tech.oa.env;

import com.baitian.fourb.common.mybatis.MybatisMonitorInterceptor;
import com.bt.bumps.common.mybatis.DataSourceFactory;
import com.bt.bumps.common.mybatis.MybatisInitializationBean;
import com.bt.bumps.common.mybatis.PropertiesDataSourcesFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Driver;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MybatisConfig {

    @Bean(name = "dataSourceFactory")
    public static DataSourceFactory getDataSourceFactory() throws Exception {
        PropertiesDataSourcesFactoryBean bean = new PropertiesDataSourcesFactoryBean();
        Properties properties = new Properties();
        properties.setProperty("oa_test.url", "jdbc:mysql://10.18.20.5:3306/oa_test?autoReconnect=true");
        properties.setProperty("oa_test.username", "fourbdev");
        properties.setProperty("oa_test.password", "fbdevadmin");
        bean.setProperties(properties);
        return bean;
    }

    @Bean
    public static MybatisInitializationBean getMybatisInitializationBean(@Qualifier("dataSourceFactory") DataSourceFactory dataSourceFactory) {
        MybatisInitializationBean bean = new MybatisInitializationBean();
        bean.setDbs("oa_test");//这里替换为当前站点用到的数据库
        bean.setDsFactoryBean("dataSourceFactory");
        bean.setScanMapperPackage("com.tech.oa");//记得替换包路径
        bean.setMapperLocations("classpath*:com/tech/oa/**/*-mapper.xml");
        bean.setInterceptors(new MybatisMonitorInterceptor());
        return bean;
    }
}
