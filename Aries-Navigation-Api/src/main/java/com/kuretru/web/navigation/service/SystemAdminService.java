package com.kuretru.web.navigation.service;

import com.kuretru.api.common.entity.data.SystemAdminDO;
import com.kuretru.api.common.entity.transfer.SystemAdminDTO;
import com.kuretru.api.common.entity.transfer.SystemLoginDTO;
import com.kuretru.api.common.entity.transfer.SystemLoginRequestDTO;
import com.kuretru.api.common.exception.ApiException;
import com.kuretru.api.common.service.BaseService;
import com.kuretru.web.navigation.mapper.SystemAdminMapper;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
public interface SystemAdminService extends BaseService<SystemAdminMapper, SystemAdminDO, SystemAdminDTO> {

    SystemLoginDTO login(SystemLoginRequestDTO requestDTO) throws ApiException;

    void updateLastLogin(long id);

}
