package com.kuretru.web.navigation.controller;

import com.kuretru.api.common.annotation.RequestAuthorization;
import com.kuretru.api.common.controller.BaseCrudController;
import com.kuretru.api.common.entity.ApiResponse;
import com.kuretru.api.common.entity.transfer.ReorderDTO;
import com.kuretru.api.common.exception.ApiException;
import com.kuretru.api.common.exception.NotFoundException;
import com.kuretru.web.navigation.entity.transfer.WebCategoryDTO;
import com.kuretru.web.navigation.service.WebCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@RestController
@RequestMapping(value = "/api/tags/{tagId}/categories", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WebCategoryController extends BaseCrudController<WebCategoryService, WebCategoryDTO> {

    @Autowired
    public WebCategoryController(WebCategoryService service) {
        super(service);
    }

    @RequestAuthorization
    @GetMapping()
    public ApiResponse list(@PathVariable("tagId") Long tagId) throws ApiException {
        List<WebCategoryDTO> result = service.list(tagId);
        if (result.isEmpty()) {
            throw new NotFoundException("未找到相关对象！");
        }
        return ApiResponse.success(result);
    }

    @RequestAuthorization
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@PathVariable("tagId") long tagId, @RequestBody WebCategoryDTO record) throws ApiException {
        record.setTagId(tagId);
        return super.create(record);
    }

    @RequestAuthorization
    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable("tagId") Long tagId, @PathVariable("id") Long id, @RequestBody WebCategoryDTO record) throws ApiException {
        record.setTagId(tagId);
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
    public ApiResponse reorder(@PathVariable("tagId") Long tagId, @RequestBody ReorderDTO record) throws ApiException {
        service.reorder(record.getIdList());
        List<WebCategoryDTO> result = service.list(tagId);
        return ApiResponse.updated(result);
    }

}
