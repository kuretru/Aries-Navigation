package com.kuretru.web.navigation.service;

import com.kuretru.api.common.service.BaseService;
import com.kuretru.web.navigation.entity.data.WebSiteDO;
import com.kuretru.web.navigation.entity.transfer.WebSiteDTO;
import com.kuretru.web.navigation.mapper.WebSiteMapper;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
public interface WebSiteService extends BaseService<WebSiteMapper, WebSiteDO, WebSiteDTO> {

    /**
     * 获取指定CategoryID下的所有记录
     *
     * @param categoryId CategoryID
     * @return 指定CategoryID下的所有记录DTO
     */
    List<WebSiteDTO> list(long categoryId);

}
