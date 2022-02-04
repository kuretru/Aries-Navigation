package com.kuretru.web.aries.controller;

import com.kuretru.api.common.controller.BaseCrudController;
import com.kuretru.api.common.entity.ApiResponse;
import com.kuretru.api.common.exception.ServiceException;
import com.kuretru.web.aries.entity.query.WebCategoryQuery;
import com.kuretru.web.aries.entity.transfer.WebCategoryDTO;
import com.kuretru.web.aries.service.WebCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@RestController
@RequestMapping("/api/tags/{tagId}/categories")
public class WebCategoryController extends BaseCrudController<WebCategoryService, WebCategoryDTO, WebCategoryQuery> {

    @Autowired
    public WebCategoryController(WebCategoryService service) {
        super(service);
    }

    @GetMapping("/{id}")
    @Override
    public ApiResponse<WebCategoryDTO> get(@PathVariable("id") UUID id) throws ServiceException {
        return super.get(id);
    }

    @GetMapping
    public ApiResponse<List<WebCategoryDTO>> list(@PathVariable("tagId") UUID tagId) throws ServiceException {
        List<WebCategoryDTO> result = service.list(tagId);
        if (null == result) {
            result = new ArrayList<>();
        }
        if (result.isEmpty()) {
            // 批量查询实体但实体不存在时，认为实体确实有不存在的可能，因此返回相应业务状态码和空列表
            return ApiResponse.notFound(result);
        }
        return ApiResponse.success(result);
    }

    @PostMapping
    public ApiResponse<WebCategoryDTO> create(@PathVariable("tagId") UUID tagId, @RequestBody WebCategoryDTO record) throws ServiceException {
        record.setTagId(tagId);
        return super.create(record);
    }

    @PutMapping("/{id}")
    public ApiResponse<WebCategoryDTO> update(@PathVariable("tagId") UUID tagId, @PathVariable("id") UUID id, @RequestBody WebCategoryDTO record) throws ServiceException {
        record.setTagId(tagId);
        return super.update(id, record);
    }

    @DeleteMapping("/{id}")
    @Override
    public ApiResponse<String> remove(@PathVariable("id") UUID id) throws ServiceException {
        return super.remove(id);
    }

}
