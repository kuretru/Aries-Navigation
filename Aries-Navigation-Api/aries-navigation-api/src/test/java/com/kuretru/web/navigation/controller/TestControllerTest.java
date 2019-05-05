package com.kuretru.web.navigation.controller;

import com.kuretru.api.common.entity.ApiResponse;
import com.kuretru.web.navigation.ApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.Assert.assertEquals;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
public class TestControllerTest extends ApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void ping() throws Exception {
        String url = "http://localhost:" + port + "/api/ping";
        ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);
        assertEquals(ApiResponse.SUCCESS, response.getCode());
        assertEquals("success", response.getMessage());
    }

}
