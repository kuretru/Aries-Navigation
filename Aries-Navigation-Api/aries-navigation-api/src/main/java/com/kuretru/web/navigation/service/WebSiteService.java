package com.kuretru.web.navigation.service;

import com.kuretru.api.common.exception.ApiException;
import com.kuretru.api.common.service.BaseService;
import com.kuretru.web.navigation.entity.data.WebSiteDO;
import com.kuretru.web.navigation.entity.transfer.WebFaviconDTO;
import com.kuretru.web.navigation.entity.transfer.WebSiteDTO;
import com.kuretru.web.navigation.mapper.WebSiteMapper;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
public interface WebSiteService extends BaseService<WebSiteMapper, WebSiteDO, WebSiteDTO> {

    /**
     * 查询数据库中指定分类下的最大的排序号
     *
     * @param categoryId CategoryID
     * @return 最大的排序号
     */
    int getMaxSequence(long categoryId);

    /**
     * 获取指定CategoryID下的所有记录
     *
     * @param categoryId CategoryID
     * @return 指定CategoryID下的所有记录DTO
     */
    List<WebSiteDTO> list(long categoryId);

    /**
     * 抓取给定网站的网站图标
     *
     * @param record 网站地址
     * @return 本地存储的图标地址
     * @throws ApiException 业务异常
     */
    WebFaviconDTO fetchFavicon(WebFaviconDTO record) throws ApiException;

}
