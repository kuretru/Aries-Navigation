package com.kuretru.web.navigation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@SpringBootApplication
@ComponentScan({"com.kuretru.web.navigation", "com.kuretru.api.common"})
@MapperScan({"com.kuretru.web.navigation.mapper", "com.kuretru.api.common.mapper"})
@EnableAsync
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
