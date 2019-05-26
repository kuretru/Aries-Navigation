package com.kuretru.web.navigation.controller;

import com.kuretru.api.common.annotation.RequestAuthorization;
import com.kuretru.api.common.controller.BaseCrudController;
import com.kuretru.api.common.entity.ApiResponse;
import com.kuretru.api.common.entity.transfer.ReorderDTO;
import com.kuretru.api.common.exception.ApiException;
import com.kuretru.api.common.exception.NotFoundException;
import com.kuretru.web.navigation.entity.transfer.WebFaviconDTO;
import com.kuretru.web.navigation.entity.transfer.WebSiteDTO;
import com.kuretru.web.navigation.service.WebSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @RequestAuthorization
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@PathVariable("categoryId") long categoryId, @RequestBody WebSiteDTO record) throws ApiException {
        record.setCategoryId(categoryId);
        return super.create(record);
    }

    @RequestAuthorization
    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable("categoryId") Long categoryId, @PathVariable("id") Long id, @RequestBody WebSiteDTO record) throws ApiException {
        record.setCategoryId(categoryId);
        return super.update(id, record);
    }

    @RequestAuthorization
    @DeleteMapping("/{id}")
    @Override
    public ApiResponse remove(@PathVariable("id") Long id) throws ApiException {
        return super.remove(id);
    }

    @RequestAuthorization
    @PutMapping("/reorder")
    public ApiResponse reorder(@PathVariable("categoryId") Long categoryId, @RequestBody ReorderDTO record) throws ApiException {
        service.reorder(record.getIdList());
        List<WebSiteDTO> result = service.list(categoryId);
        return ApiResponse.updated(result);
    }

    @RequestAuthorization
    @PostMapping("/favicon")
    public ApiResponse fetchFavicon(@RequestBody WebFaviconDTO record) throws ApiException {
        WebFaviconDTO result = service.fetchFavicon(record);
        return ApiResponse.success(result);
    }

}
