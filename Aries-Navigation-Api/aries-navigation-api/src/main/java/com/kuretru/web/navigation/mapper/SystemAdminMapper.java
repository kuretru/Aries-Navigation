package com.kuretru.web.navigation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuretru.api.common.entity.data.SystemAdminDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Mapper
@Repository
public interface SystemAdminMapper extends BaseMapper<SystemAdminDO> {

}
