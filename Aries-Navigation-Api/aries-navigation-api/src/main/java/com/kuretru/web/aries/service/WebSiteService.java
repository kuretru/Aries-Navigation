package com.kuretru.web.aries.service;

import com.kuretru.microservices.web.service.BaseSequenceService;
import com.kuretru.web.aries.entity.query.WebSiteQuery;
import com.kuretru.web.aries.entity.transfer.WebSiteDTO;
import com.kuretru.web.aries.entity.view.WebSiteVO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
public interface WebSiteService extends BaseSequenceService<WebSiteDTO, WebSiteQuery> {


    /**
     * 根据CategoryID，组织所有站点
     *
     * @return 所有站点数据
     */
    Map<UUID, List<WebSiteVO>> listVO();

    /**
     * 查询指定CategoryID下的记录条数
     *
     * @param categoryId 指定CategoryID
     * @return 记录条数
     */
    int count(UUID categoryId);

}
