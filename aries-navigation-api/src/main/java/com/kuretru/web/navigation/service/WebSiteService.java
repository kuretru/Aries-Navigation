package com.kuretru.web.navigation.service;

import com.kuretru.api.common.service.BaseService;
import com.kuretru.web.navigation.entity.transfer.WebSiteDTO;
import com.kuretru.web.navigation.entity.view.WebSiteDataVO;
import com.kuretru.web.navigation.entity.view.WebSiteVO;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
public interface WebSiteService extends BaseService<WebSiteVO, WebSiteDTO> {

    List<WebSiteDataVO> listBySiteId(long siteId);

}
