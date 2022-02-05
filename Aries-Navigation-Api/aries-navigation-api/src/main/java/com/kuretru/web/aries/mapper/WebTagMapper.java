package com.kuretru.web.aries.mapper;

import com.kuretru.api.common.mapper.BaseSequenceMapper;
import com.kuretru.web.aries.entity.data.WebTagDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Mapper
@Repository
public interface WebTagMapper extends BaseSequenceMapper<WebTagDO> {

}
