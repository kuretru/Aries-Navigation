package com.kuretru.web.navigation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuretru.web.navigation.entity.data.WebSiteDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Mapper
@Repository
public interface WebSiteMapper extends BaseMapper<WebSiteDO> {

}
