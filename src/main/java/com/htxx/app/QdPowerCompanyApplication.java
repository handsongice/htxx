package com.htxx.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
/**
 * 如果mybatis中service实现类中加入事务注解，需要此处添加该注解
 */
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.htxx.*"})
@MapperScan(basePackages = {"com.htxx.mapper"})
public class QdPowerCompanyApplication {

    /**
     * extends SpringBootServletInitializer
     * 实现SpringBootServletInitializer可以让spring-boot项目在web容器中运行
     */
    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(this.getClass());
        return super.configure(builder);
    }*/
    public static void main(String[] args) {
        SpringApplication.run(QdPowerCompanyApplication.class, args);
    }
}

/*
@SpringBootApplication
*/
/**
 * 如果mybatis中service实现类中加入事务注解，需要此处添加该注解
 * <p>
 * extends SpringBootServletInitializer
 * 实现SpringBootServletInitializer可以让spring-boot项目在web容器中运行
 * <p>
 * extends SpringBootServletInitializer
 * 实现SpringBootServletInitializer可以让spring-boot项目在web容器中运行
 *//*

@EnableTransactionManagement
@ComponentScan(basePackages = {"com.htxx.*"})
@MapperScan(basePackages = {"com.htxx.mapper"})
public class QdPowerCompanyApplication extends SpringBootServletInitializer {
    */
/**
 * extends SpringBootServletInitializer
 * 实现SpringBootServletInitializer可以让spring-boot项目在web容器中运行
 *//*

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(this.getClass());
        return super.configure(builder);
    }

    public static void main(String[] args) {
        SpringApplication.run(QdPowerCompanyApplication.class, args);
    }
}
*/

