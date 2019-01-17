package com.kuretru.web.navigation.controller;

import com.kuretru.api.common.controller.BaseRestController;
import com.kuretru.api.common.entity.ApiResponse;
import com.kuretru.api.common.util.InstantUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

/**
 * @author 呉真 <kuretru@gmail.com>
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BaseRestController extends BaseRestController {

    @GetMapping("/ping")
    public ApiResponse ping() {
        String now = InstantUtils.instantToString(Instant.now());
        return ApiResponse.success(now);
    }

}
