package com.kuretru.web.navigation.controller;

import com.kuretru.api.common.controller.BaseRestController;
import com.kuretru.api.common.entity.ApiResponse;
import com.kuretru.web.navigation.service.WebTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@RestController
@RequestMapping(value = "/api/tags", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WebTagController extends BaseRestController {
    @Autowired
    private WebTagService service;

    @GetMapping()
    public ApiResponse list() {
        return ApiResponse.success(service.list());
    }

}
