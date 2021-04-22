package com.funny.study.camel.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * 数据源配置工具类
 */
@Configuration
@MapperScan(basePackages = "com.funny.study.camel",
        annotationClass = Repository.class,
        sqlSessionTemplateRef = "statSqlSessionTemplate")
public class DataSourceLeadsConfig {
    @Value("${spring.profiles.active}")
    private String profile;

    @Bean(name = "statDataSource")
    @Qualifier("statDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.stat")
    public DataSource leadsDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "statTransactionManager")
    @Qualifier("statTransactionManager")
    public DataSourceTransactionManager leadsTransactionManager(@Qualifier("statDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "statSqlSessionFactory")
    @Qualifier("statSqlSessionFactory")
    public SqlSessionFactory leadsSqlSessionFactory(@Qualifier("statDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*/*.xml"));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        if (profile.equalsIgnoreCase("yzdev")) {
            configuration.setLogImpl(StdOutImpl.class);
        }
        return bean.getObject();
    }

    @Bean(name = "statSqlSessionTemplate")
    @Qualifier("statSqlSessionTemplate")
    public SqlSessionTemplate leadsSqlSessionTemplate(@Qualifier("statSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
