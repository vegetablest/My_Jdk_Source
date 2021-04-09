package com.bysj.logistics.manage.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/2 22:28
 * @verson
 */
@Configuration
public class NopDataSourceConfiguration {

    @Configuration
    @ConfigurationProperties(prefix = "spring.datasource")
    public class NopJpaConfig extends HikariConfig {
        @Bean(name = "dataSource")
        public DataSource nopdataSource() throws SQLException {
            return new HikariDataSource(this);
        }

        /**
         * 事务支持
         * */
        @Bean(name = "transactionManager")
        public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }

        @Bean
        public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(dataSource);
            return factoryBean.getObject();
        }
    }
}
