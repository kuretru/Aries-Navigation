package com.kuretru.web.navigation.controller;

import com.kuretru.api.common.annotation.RequestAuthorization;
import com.kuretru.api.common.controller.BaseController;
import com.kuretru.api.common.entity.ApiResponse;
import com.kuretru.api.common.exception.ApiException;
import com.kuretru.api.common.exception.NotFoundException;
import com.kuretru.web.navigation.entity.view.WebTagVO;
import com.kuretru.web.navigation.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@RestController
@RequestMapping(value = "/api/data", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WebController extends BaseController {

    private final WebService service;

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

    @RequestAuthorization
    @DeleteMapping("/cache")
    public ApiResponse removeCache() {
        service.removeCache();
        return ApiResponse.removed();
    }

}
