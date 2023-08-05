package com.kuretru.web.aries.entity.mapper;

import com.kuretru.microservices.web.entity.mapper.BaseHistoryEntityMapper;
import com.kuretru.web.aries.entity.data.WebSiteClickHistoryDO;
import com.kuretru.web.aries.entity.transfer.WebSiteClickHistoryDTO;
import org.mapstruct.Mapper;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Mapper(componentModel = "spring")
public interface WebSiteClickHistoryEntityMapper extends BaseHistoryEntityMapper<WebSiteClickHistoryDO, WebSiteClickHistoryDTO> {

}
