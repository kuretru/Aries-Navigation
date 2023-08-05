package com.kuretru.web.aries.service.impl;

import com.kuretru.microservices.web.constant.code.UserErrorCodes;
import com.kuretru.microservices.web.exception.ServiceException;
import com.kuretru.microservices.web.service.impl.BaseHistoryServiceImpl;
import com.kuretru.web.aries.entity.data.WebSiteClickHistoryDO;
import com.kuretru.web.aries.entity.mapper.WebSiteClickHistoryEntityMapper;
import com.kuretru.web.aries.entity.transfer.WebSiteClickHistoryDTO;
import com.kuretru.web.aries.entity.transfer.WebSiteDTO;
import com.kuretru.web.aries.mapper.WebSiteClickHistoryMapper;
import com.kuretru.web.aries.service.WebSiteClickHistoryService;
import com.kuretru.web.aries.service.WebSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Service
public class WebSiteClickHistoryServiceImpl extends BaseHistoryServiceImpl<WebSiteClickHistoryMapper, WebSiteClickHistoryDO, WebSiteClickHistoryDTO> implements WebSiteClickHistoryService {

    private final WebSiteService webSiteService;

    @Autowired

    public WebSiteClickHistoryServiceImpl(WebSiteClickHistoryMapper mapper, WebSiteClickHistoryEntityMapper entityMapper, WebSiteService webSiteService) {
        super(mapper, entityMapper);
        this.webSiteService = webSiteService;
    }

    @Override
    protected void verifyDTO(WebSiteClickHistoryDTO record) throws ServiceException {
        WebSiteDTO webSite = webSiteService.get(record.getSiteId());
        if (webSite == null) {
            throw new ServiceException(UserErrorCodes.REQUEST_PARAMETER_ERROR, "指定站点不存在");
        }
    }

}
