package com.example.authcenter.config;

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
 * todo 整合数据源配置，动态生成
 */
//@Configuration
//@MapperScan(value = "com.example.authcenter.dao", sqlSessionTemplateRef = "st-auth-center")
public class DataSourceConfig {
    /**
     * 配置数据源
     */
    @Bean("ds-auth-center")
    @ConfigurationProperties(prefix = "spring.datasource.auth-center")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建sql会话工厂
     */
    @Bean("sf-auth-center")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("ds-auth-center") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 创建当前数据源的事务管理器
     */
    @Bean("tm-auth-center")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("ds-auth-center") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 通过会话工厂创建SqlSessionTemplate
     */
    @Bean("st-auth-center")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sf-auth-center") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

