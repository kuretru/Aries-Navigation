package com.kuretru.web.navigation.controller;

import com.kuretru.api.common.annotation.RequestAuthorization;
import com.kuretru.api.common.controller.BaseController;
import com.kuretru.api.common.entity.ApiResponse;
import com.kuretru.api.common.exception.ApiException;
import com.kuretru.api.common.exception.NotFoundException;
import com.kuretru.web.navigation.entity.transfer.WebCategoryDTO;
import com.kuretru.web.navigation.service.WebCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@RestController
@RequestMapping(value = "/api/tags/{tagId}/categories", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WebCategoryController extends BaseController {

    private WebCategoryService service;

    @Autowired
    public WebCategoryController(WebCategoryService service) {
        this.service = service;
    }

    @RequestAuthorization
    @GetMapping()
    public ApiResponse listByTagId(@PathVariable("tagId") Long tagId) throws ApiException {
        List<WebCategoryDTO> result = service.list(tagId);
        if (result.isEmpty()) {
            throw new NotFoundException("未找到相关对象！");
        }
        return ApiResponse.success(result);
    }

}
