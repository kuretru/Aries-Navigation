package com.kuretru.web.aries.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuretru.web.aries.entity.data.WebSiteClickHistoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Mapper
@Repository
public interface WebSiteClickHistoryMapper extends BaseMapper<WebSiteClickHistoryDO> {

}
