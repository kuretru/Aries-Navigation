package com.kuretru.web.aries.controller;

import com.kuretru.api.common.controller.BaseCrudController;
import com.kuretru.api.common.entity.ApiResponse;
import com.kuretru.api.common.exception.ServiceException;
import com.kuretru.web.aries.entity.transfer.WebCategoryDTO;
import com.kuretru.web.aries.service.WebCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@RestController
@RequestMapping("/api/tags/{tagId}/categories")
public class WebCategoryController extends BaseCrudController<WebCategoryService, WebCategoryDTO> {

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
    @Override
    public ApiResponse<List<WebCategoryDTO>> list() throws ServiceException {
        return super.list();
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
