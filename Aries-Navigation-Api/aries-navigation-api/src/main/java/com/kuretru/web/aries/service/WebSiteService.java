package com.kuretru.web.aries.service;

import com.kuretru.api.common.service.BaseService;
import com.kuretru.web.aries.entity.transfer.WebSiteDTO;

import java.util.List;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
public interface WebSiteService extends BaseService<WebSiteDTO> {

    /**
     * 查询指定CategoryID下的所有记录
     *
     * @param categoryId 指定CategoryID
     * @return 指定CategoryID下的所有记录
     */
    List<WebSiteDTO> list(UUID categoryId);

}
