package com.kuretru.web.aries.controller;

import com.kuretru.microservices.authentication.annotaion.RequireAuthorization;
import com.kuretru.microservices.web.constant.code.UserErrorCodes;
import com.kuretru.microservices.web.controller.BaseController;
import com.kuretru.microservices.web.entity.ApiResponse;
import com.kuretru.microservices.web.exception.ServiceException;
import com.kuretru.web.aries.entity.transfer.WebSiteClickHistoryDTO;
import com.kuretru.web.aries.service.WebSiteClickHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@RestController
@RequestMapping("/sites/history")
@RequireAuthorization
public class WebSiteClickHistoryController extends BaseController {

    private final WebSiteClickHistoryService service;

    @Autowired
    public WebSiteClickHistoryController(WebSiteClickHistoryService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<?> create(@Validated @RequestBody WebSiteClickHistoryDTO record) throws ServiceException {
        if (record == null) {
            throw ServiceException.build(UserErrorCodes.REQUEST_PARAMETER_ERROR, "未指定记录");
        }
        service.save(record);
        return ApiResponse.created("新增成功");
    }

}
