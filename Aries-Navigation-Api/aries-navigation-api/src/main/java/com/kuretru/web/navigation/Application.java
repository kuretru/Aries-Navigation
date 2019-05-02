package com.kuretru.web.navigation;

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
@EnableAsync
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
