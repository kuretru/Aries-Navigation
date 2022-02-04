package com.kuretru.web.aries.controller;

import com.kuretru.api.common.controller.BaseCrudController;
import com.kuretru.api.common.entity.ApiResponse;
import com.kuretru.api.common.exception.ServiceException;
import com.kuretru.web.aries.entity.query.WebSiteQuery;
import com.kuretru.web.aries.entity.transfer.WebSiteDTO;
import com.kuretru.web.aries.service.WebSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@RestController
@RequestMapping("/api/tags/{tagId}/categories/{categoryId}/sites")
public class WebSiteController extends BaseCrudController<WebSiteService, WebSiteDTO, WebSiteQuery> {

    @Autowired
    public WebSiteController(WebSiteService service) {
        super(service);
    }

    @GetMapping("/{id}")
    @Override
    public ApiResponse<WebSiteDTO> get(@PathVariable("id") UUID id) throws ServiceException {
        return super.get(id);
    }

    @GetMapping
    public ApiResponse<List<WebSiteDTO>> list(@PathVariable("categoryId") UUID categoryId) throws ServiceException {
        List<WebSiteDTO> result = service.list(categoryId);
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
    public ApiResponse<WebSiteDTO> create(@PathVariable("categoryId") UUID categoryId, @RequestBody WebSiteDTO record) throws ServiceException {
        record.setCategoryId(categoryId);
        return super.create(record);
    }

    @PutMapping("/{id}")
    public ApiResponse<WebSiteDTO> update(@PathVariable("categoryId") UUID categoryId, @PathVariable("id") UUID id, @RequestBody WebSiteDTO record) throws ServiceException {
        record.setCategoryId(categoryId);
        return super.update(id, record);
    }

    @DeleteMapping("/{id}")
    @Override
    public ApiResponse<String> remove(@PathVariable("id") UUID id) throws ServiceException {
        return super.remove(id);
    }

}
