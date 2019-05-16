package com.kuretru.web.navigation.controller;

import com.kuretru.api.common.annotation.RequestAuthorization;
import com.kuretru.api.common.controller.BaseRestController;
import com.kuretru.api.common.entity.ApiResponse;
import com.kuretru.api.common.entity.transfer.ReorderDTO;
import com.kuretru.api.common.exception.ApiException;
import com.kuretru.web.navigation.entity.transfer.WebTagDTO;
import com.kuretru.web.navigation.service.WebTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestAuthorization
    @PutMapping("/reorder")
    public ApiResponse reorder(@RequestBody ReorderDTO record) throws ApiException {
        service.reorder(record.getIdList());
        ApiResponse result = list();
        result.setCode(ApiResponse.UPDATED);
        return result;
    }

}
