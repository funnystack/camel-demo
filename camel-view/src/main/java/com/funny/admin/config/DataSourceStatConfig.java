package com.funny.admin.config;

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

import javax.sql.DataSource;

/**
 * 数据源配置工具类
 */
@Configuration
@MapperScan(basePackages = "com.funny.admin.camelv.dao", sqlSessionTemplateRef = "leadsSqlSessionTemplate")
public class DataSourceStatConfig {

    @Value("${spring.profiles.active}")
    private String profile;

    @Bean(name = "leadsDataSource")
    @Qualifier("leadsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.stat")
    public DataSource leadsDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "leadsTransactionManager")
    @Qualifier("leadsTransactionManager")
    public DataSourceTransactionManager leadsTransactionManager(@Qualifier("leadsDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "leadsSqlSessionFactory")
    @Qualifier("leadsSqlSessionFactory")
    public SqlSessionFactory leadsSqlSessionFactory(@Qualifier("leadsDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/camelv/*.xml"));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        if(profile.equalsIgnoreCase("dev")){
            configuration.setLogImpl(StdOutImpl.class);
        }
        return bean.getObject();
    }

    @Bean(name = "leadsSqlSessionTemplate")
    @Qualifier("leadsSqlSessionTemplate")
    public SqlSessionTemplate leadsSqlSessionTemplate(@Qualifier("leadsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
