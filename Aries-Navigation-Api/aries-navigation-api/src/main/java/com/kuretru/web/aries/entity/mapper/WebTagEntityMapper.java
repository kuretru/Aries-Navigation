package com.kuretru.web.aries.entity.mapper;

import com.kuretru.microservices.web.entity.mapper.BaseSequenceEntityMapper;
import com.kuretru.web.aries.entity.data.WebTagDO;
import com.kuretru.web.aries.entity.transfer.WebTagDTO;
import org.mapstruct.Mapper;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Mapper(componentModel = "spring")
public interface WebTagEntityMapper extends BaseSequenceEntityMapper<WebTagDO, WebTagDTO> {

}
