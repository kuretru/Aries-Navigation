package com.kuretru.web.navigation.controller;

import com.kuretru.api.common.controller.BaseController;
import com.kuretru.api.common.entity.ApiResponse;
import com.kuretru.api.common.exception.ApiException;
import com.kuretru.api.common.exception.NotFoundException;
import com.kuretru.web.navigation.entity.view.WebTagVO;
import com.kuretru.web.navigation.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@RestController
@RequestMapping(value = "/api/data", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WebController extends BaseController {

    private WebService service;

    @Autowired
    public WebController(WebService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse list(@RequestParam(value = "use_cache", defaultValue = "true") boolean useCache) throws ApiException {
        List<WebTagVO> result = service.list(useCache);
        if (result.isEmpty()) {
            throw new NotFoundException("没有数据！");
        }
        return ApiResponse.success(result);
    }

}
