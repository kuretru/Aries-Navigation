package com.kuretru.web.aries;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
class ApplicationTests {

    @Test
    void contextLoads() {
    }

}
