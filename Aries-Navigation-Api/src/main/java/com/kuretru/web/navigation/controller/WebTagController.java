package com.kuretru.web.navigation.controller;

import com.kuretru.api.common.controller.BaseRestController;
import com.kuretru.web.navigation.entity.transfer.WebTagDTO;
import com.kuretru.web.navigation.service.WebTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@RestController
@RequestMapping(value = "/api/tags", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WebTagController extends BaseRestController<WebTagService, WebTagDTO> {

    @Autowired
    public WebTagController(WebTagService service) {
        super(service);
    }

}
