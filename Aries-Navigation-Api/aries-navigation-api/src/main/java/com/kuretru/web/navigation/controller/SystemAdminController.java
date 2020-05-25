package com.kuretru.web.navigation.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.kuretru.api.common.controller.BaseRestController;
import com.kuretru.api.common.entity.ApiResponse;
import com.kuretru.api.common.entity.transfer.SystemAdminDTO;
import com.kuretru.api.common.entity.transfer.SystemLoginDTO;
import com.kuretru.api.common.entity.transfer.SystemLoginRequestDTO;
import com.kuretru.api.common.exception.ApiException;
import com.kuretru.web.navigation.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@RestController
@RequestMapping(value = "/api/admins", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SystemAdminController extends BaseRestController<SystemAdminService, SystemAdminDTO> {

    private final Kaptcha kaptcha;

    @Autowired
    public SystemAdminController(SystemAdminService service, Kaptcha kaptcha) {
        super(service);
        this.kaptcha = kaptcha;
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody SystemLoginRequestDTO requestDTO) throws ApiException {
        SystemLoginDTO result = service.login(requestDTO);
        return ApiResponse.success(result);
    }

    @PostMapping("/logout")
    public ApiResponse logout(String accessToken) {
        service.logout(accessToken);
        return ApiResponse.success("退出成功");
    }

    @GetMapping("/kaptcha")
    public ApiResponse kaptcha() {
        String code = kaptcha.render();
        return ApiResponse.success(code);
    }

}
