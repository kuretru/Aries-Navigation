package com.kuretru.web.aries.controller;

import com.kuretru.api.common.controller.BaseRestController;
import com.kuretru.web.aries.entity.query.WebTagQuery;
import com.kuretru.web.aries.entity.transfer.WebTagDTO;
import com.kuretru.web.aries.service.WebTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@RestController
@RequestMapping("/api/tags")
public class WebTagController extends BaseRestController<WebTagService, WebTagDTO, WebTagQuery> {

    @Autowired
    public WebTagController(WebTagService service) {
        super(service);
    }

}
