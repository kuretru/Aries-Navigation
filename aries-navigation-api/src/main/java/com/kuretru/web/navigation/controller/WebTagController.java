package com.kuretru.web.navigation.controller;

import com.kuretru.api.common.controller.BaseRestController;
import com.kuretru.api.common.entity.ApiResponse;
import com.kuretru.web.navigation.service.WebCategoryService;
import com.kuretru.web.navigation.service.WebTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@RestController
@RequestMapping(value = "/api/tags", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WebTagController extends BaseRestController {

    private WebTagService service;

    private WebCategoryService categoryService;

    @Autowired
    public WebTagController(WebTagService service, WebCategoryService categoryService) {
        this.service = service;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ApiResponse list() {
        return ApiResponse.success(service.list());
    }

    @GetMapping("/{tag_id}/categories")
    public ApiResponse listCategories(@PathVariable("tag_id") long tagId) {
        return ApiResponse.success(categoryService.listByTagId(tagId));
    }

}
