package com.kuretru.web.aries.controller;

import com.kuretru.microservices.web.controller.BaseController;
import com.kuretru.microservices.web.entity.ApiResponse;
import com.kuretru.web.aries.constant.DocumentConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@RestController
@RequestMapping("/api")
public class DocumentController extends BaseController {

    @GetMapping
    public ApiResponse<?> index() {
        return ApiResponse.success(DocumentConstants.INDEX);
    }

}
