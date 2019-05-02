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

    /**
     * 管理员登录
     *
     * @param requestDTO 登录请求实体
     * @return 登录响应实体
     * @throws ApiException 登录失败异常
     */
    SystemLoginDTO login(SystemLoginRequestDTO requestDTO) throws ApiException;

    /**
     * 管理员登出
     *
     * @param incomingToken 下发的AccessToken
     */
    void logout(String incomingToken);

    /**
     * 更新上一次登录的时间
     *
     * @param id 管理员ID
     */
    void updateLastLogin(long id);

}
