package com.kuretru.web.aries;

import com.kuretru.microservices.oauth2.client.system.EnableOAuth2System;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@SpringBootApplication(exclude = RedisAutoConfiguration.class)
@EnableOAuth2System
@MapperScan("com.kuretru.web.aries.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
