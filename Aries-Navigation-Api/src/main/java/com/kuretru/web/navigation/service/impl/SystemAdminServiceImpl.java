package com.kuretru.web.navigation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuretru.api.common.entity.business.AccessTokenBO;
import com.kuretru.api.common.entity.data.SystemAdminDO;
import com.kuretru.api.common.entity.enums.UserRoleEnum;
import com.kuretru.api.common.entity.transfer.SystemAdminDTO;
import com.kuretru.api.common.entity.transfer.SystemLoginDTO;
import com.kuretru.api.common.entity.transfer.SystemLoginRequestDTO;
import com.kuretru.api.common.exception.ApiException;
import com.kuretru.api.common.exception.AuthenticationFailedException;
import com.kuretru.api.common.manager.AccessTokenManager;
import com.kuretru.api.common.service.impl.BaseServiceImpl;
import com.kuretru.api.common.util.HashUtils;
import com.kuretru.web.navigation.mapper.SystemAdminMapper;
import com.kuretru.web.navigation.service.SystemAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Service
public class SystemAdminServiceImpl extends BaseServiceImpl<SystemAdminMapper, SystemAdminDO, SystemAdminDTO> implements SystemAdminService {

    private AccessTokenManager tokenManager;

    @Autowired
    public SystemAdminServiceImpl(SystemAdminMapper mapper, AccessTokenManager tokenManager) {
        super(mapper, SystemAdminDO.class, SystemAdminDTO.class);
        this.tokenManager = tokenManager;
    }

    @Override
    public SystemLoginDTO login(SystemLoginRequestDTO requestDTO) throws ApiException {
        // 从数据库中查询数据
        QueryWrapper<SystemAdminDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", requestDTO.getUsername());
        SystemAdminDO record = mapper.selectOne(queryWrapper);

        // 判断用户名与密码是否正确
        if (record == null) {
            throw new AuthenticationFailedException("用户名不存在");
        }
        String encrypted = encrypt(requestDTO.getPassword(), record.getSalt());
        encrypted = HashUtils.computeMd5(encrypted);
        if (!encrypted.equals(record.getPassword())) {
            throw new AuthenticationFailedException("密码错误");
        }

        // 生成返回实体类
        SystemLoginDTO result = new SystemLoginDTO();
        BeanUtils.copyProperties(record, result);
        result.setUserRole(UserRoleEnum.valueOf(record.getUserRole()));

        // 生成AccessToken
        UserRoleEnum userRole = UserRoleEnum.valueOf(record.getUserRole());
        AccessTokenBO accessToken = AccessTokenBO.build(record.getId(), userRole);
        tokenManager.createToken(accessToken);
        result.setAccessToken(accessToken.getToken());

        // 更新上一次登录时间
        updateLastLogin(record.getId());
        return result;
    }

    @Override
    public void updateLastLogin(long id) {
        SystemAdminDO record = new SystemAdminDO();
        record.setId(id);
        record.setLastLogin(Instant.now());
        mapper.updateById(record);
    }

    @Override
    public SystemAdminDTO doToDTO(SystemAdminDO record) {
        SystemAdminDTO result = super.doToDTO(record);
        result.setUserRole(UserRoleEnum.valueOf(record.getUserRole()));
        return result;
    }

    @Override
    public SystemAdminDO dtoToDO(SystemAdminDTO record) {
        SystemAdminDO result = super.dtoToDO(record);
        result.setUserRole(record.getUserRole().getCode());
        return result;
    }

    private String encrypt(String password, String salt) {
        String left = salt.substring(0, 16).toLowerCase();
        String right = salt.substring(16).toUpperCase();
        password = password.trim();
        return HashUtils.computeMd5(left + password + right);
    }

}
