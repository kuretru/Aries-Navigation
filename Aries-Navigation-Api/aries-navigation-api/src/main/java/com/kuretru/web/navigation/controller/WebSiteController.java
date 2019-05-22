package com.kuretru.web.navigation.controller;

import com.kuretru.api.common.annotation.RequestAuthorization;
import com.kuretru.api.common.controller.BaseCrudController;
import com.kuretru.api.common.entity.ApiResponse;
import com.kuretru.api.common.exception.ApiException;
import com.kuretru.api.common.exception.NotFoundException;
import com.kuretru.web.navigation.entity.transfer.WebSiteDTO;
import com.kuretru.web.navigation.service.WebSiteService;
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
@RequestMapping(value = "/api/tags/{tagId}/categories/{categoryId}/sites", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WebSiteController extends BaseCrudController<WebSiteService, WebSiteDTO> {

    @Autowired
    public WebSiteController(WebSiteService service) {
        super(service);
    }

    @RequestAuthorization
    @GetMapping()
    public ApiResponse list(@PathVariable("categoryId") Long categoryId) throws ApiException {
        List<WebSiteDTO> result = service.list(categoryId);
        if (result.isEmpty()) {
            throw new NotFoundException("未找到相关对象！");
        }
        return ApiResponse.success(result);
    }

}
