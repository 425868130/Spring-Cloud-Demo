package com.example.userservice.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * mybatis数据源配置,所有当前数据源的Bean都需要命名以进行区分
 */
@Configuration
@MapperScan(basePackages = "com.example.userservice.dao", sqlSessionTemplateRef = "st-user-service")
public class DataSourceConfig {
    /**
     * 配置数据源
     */
    @Bean("ds-user-service")
    @ConfigurationProperties(prefix = "spring.datasource.user-service")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建sql会话工厂
     */
    @Bean("sf-user-service")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("ds-user-service") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 创建当前数据源的事务管理器
     */
    @Bean("tm-user-service")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("ds-user-service") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 通过会话工厂创建SqlSessionTemplate
     */
    @Bean("st-user-service")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sf-user-service") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

