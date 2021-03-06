package com.shop.core.config;

import com.shop.core.mybatis.enums.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by 36kr on 16/1/28.
 */
@Configuration
@Import(CoreDataSourceConfiguration.class)
@MapperScan(basePackageClasses = {com.shop.core.mapper.Pkg.class}, sqlSessionFactoryRef = MyBatisConfiguration.SQL_SESSION_FACTORY_NAME)
public class MyBatisConfiguration {
    public static final String SQL_SESSION_FACTORY_NAME = "com_shop_SqlSessionFactory";

    private String enumBasePackages = "com.shop.core.mybatis";

    @Bean(name = MyBatisConfiguration.SQL_SESSION_FACTORY_NAME)
    public MybatisSqlSessionFactoryBean com_shop_MybatisSqlSessionFactoryBean(CoreDataSourceConfiguration config) {

        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(config.core_dataSource());
        sessionFactoryBean.setEnumBasePackages(enumBasePackages);
        return sessionFactoryBean;
    }
}
