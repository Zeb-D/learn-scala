package com.yd.scala.hello.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author created by Zeb灬D on 2021-02-05 11:05
 */
@Component
public class MybatisConfig {

    @Value("${mysql.address:jdbc:mysql://127.0.0.1:3306/my_test?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true}")
    private String address = "jdbc:mysql://127.0.0.1:3306/my_test?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true";
    @Value("${mysql.address:root}")
    private String username = "root";
    @Value("${mysql.password:yd_mysql}")
    private String password = "yd_mysql";

    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(address);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setInitialSize(1);
        druidDataSource.setMinIdle(1);
        druidDataSource.setMaxActive(10);
        druidDataSource.setMaxWait(100_00);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60_000);
        druidDataSource.setMinEvictableIdleTimeMillis(30_000);
        druidDataSource.setValidationQuery("select 1");
        druidDataSource.setTestOnBorrow(true);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return druidDataSource;
    }

    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(DataSource dataSource) {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage("com.yd.scala.hello.dao.base");//保持与MapperScannerConfigurer 一致
        mybatisSqlSessionFactoryBean.setMapperLocations(resolveMapperLocations("classpath:/mybatis/*.xml"));
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLimit(1000);
        MybatisSqlPrintInterceptor sqlPrintInterceptor = new MybatisSqlPrintInterceptor();
        mybatisSqlSessionFactoryBean.setPlugins(new Interceptor[]{paginationInterceptor, sqlPrintInterceptor});
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDatabaseId("mysql");
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        mybatisSqlSessionFactoryBean.setConfiguration(configuration);
        return mybatisSqlSessionFactoryBean;
    }

    private Resource[] resolveMapperLocations(String... mapperLocations) {
        return Stream.of(Optional.ofNullable(mapperLocations).orElse(new String[0]))
                .flatMap((location) -> Stream.of(this.getResources(location)))
                .toArray((x$0) -> new Resource[x$0]);
    }

    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    private Resource[] getResources(String location) {
        try {
            return resourceResolver.getResources(location);
        } catch (IOException var3) {
            return new Resource[0];
        }
    }

    @Bean
    @Order
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.yd.scala.hello.dao.base");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("mybatisSqlSessionFactoryBean");
        return mapperScannerConfigurer;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
