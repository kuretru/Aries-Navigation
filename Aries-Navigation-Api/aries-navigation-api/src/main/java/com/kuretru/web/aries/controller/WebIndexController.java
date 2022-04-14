package com.kuretru.web.aries.controller;

import com.kuretru.microservices.web.controller.BaseController;
import com.kuretru.microservices.web.entity.ApiResponse;
import com.kuretru.web.aries.service.WebIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@RestController
@RequestMapping("/index")
public class WebIndexController extends BaseController {

    private final WebIndexService service;

    @Autowired
    public WebIndexController(WebIndexService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<?> getRecords() {
        return ApiResponse.success(service.getRecords(false));
    }

}
