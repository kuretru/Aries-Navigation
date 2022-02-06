package com.kuretru.web.aries.controller;

import com.kuretru.api.common.controller.BaseSequenceRestController;
import com.kuretru.web.aries.entity.query.WebCategoryQuery;
import com.kuretru.web.aries.entity.transfer.WebCategoryDTO;
import com.kuretru.web.aries.service.WebCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@RestController
@RequestMapping("/api/categories")
public class WebCategoryController extends BaseSequenceRestController<WebCategoryService, WebCategoryDTO, WebCategoryQuery> {

    @Autowired
    public WebCategoryController(WebCategoryService service) {
        super(service);
    }

}
