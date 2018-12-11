package com.liang.report.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author lianghaiyang
 * @date 2018/11/22
 */
@Configuration
public class QuartzDataSourceConfiguration {

    @Bean(name = "quartzDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.quartz")
    @QuartzDataSource
    public DataSource quartzDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "quartzSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("quartzDataSource") DataSource quartzDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(quartzDataSource);
        return bean.getObject();
    }

    @Bean(name = "quartzTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("quartzDataSource") DataSource quartzDataSource) {
        return new DataSourceTransactionManager(quartzDataSource);
    }

    @Bean(name = "quartzSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("quartzSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
