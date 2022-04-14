package com.kuretru.web.aries.mapper;

import com.kuretru.microservices.web.mapper.BaseSequenceMapper;
import com.kuretru.web.aries.entity.data.WebSiteDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Mapper
@Repository
public interface WebSiteMapper extends BaseSequenceMapper<WebSiteDO> {

}
