package com.kuretru.web.aries.controller;

import com.kuretru.microservices.authentication.annotaion.RequireAuthorization;
import com.kuretru.microservices.web.controller.BaseSequenceRestController;
import com.kuretru.web.aries.entity.query.WebSiteQuery;
import com.kuretru.web.aries.entity.transfer.WebSiteDTO;
import com.kuretru.web.aries.service.WebSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@RestController
@RequestMapping("/sites")
@RequireAuthorization
public class WebSiteController extends BaseSequenceRestController<WebSiteService, WebSiteDTO, WebSiteQuery> {

    @Autowired
    public WebSiteController(WebSiteService service) {
        super(service);
    }

}
