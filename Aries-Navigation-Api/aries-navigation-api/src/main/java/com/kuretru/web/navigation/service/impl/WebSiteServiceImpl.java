package com.kuretru.web.navigation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuretru.api.common.configuration.CommonProperties;
import com.kuretru.api.common.exception.ApiException;
import com.kuretru.api.common.service.impl.BaseServiceImpl;
import com.kuretru.web.navigation.entity.data.WebSiteDO;
import com.kuretru.web.navigation.entity.transfer.WebFaviconDTO;
import com.kuretru.web.navigation.entity.transfer.WebSiteDTO;
import com.kuretru.web.navigation.manager.FaviconManager;
import com.kuretru.web.navigation.mapper.WebSiteMapper;
import com.kuretru.web.navigation.service.WebSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Service
public class WebSiteServiceImpl extends BaseServiceImpl<WebSiteMapper, WebSiteDO, WebSiteDTO> implements WebSiteService {

    private final CommonProperties commonProperties;
    private final FaviconManager faviconManager;

    @Autowired
    public WebSiteServiceImpl(WebSiteMapper mapper, CommonProperties commonProperties,
                              FaviconManager faviconManager) {
        super(mapper, WebSiteDO.class, WebSiteDTO.class);
        this.commonProperties = commonProperties;
        this.faviconManager = faviconManager;
    }

    @Override
    public List<WebSiteDTO> list(long categoryId) {
        QueryWrapper<WebSiteDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.orderByAsc("sequence");
        List<WebSiteDO> records = mapper.selectList(queryWrapper);
        return doToDTO(records);
    }

    @Override
    public WebFaviconDTO fetchFavicon(WebFaviconDTO record) throws ApiException {
        String path = faviconManager.downloadFavicon(record.getUrl());
        path = commonProperties.getFileCdnPrefix() + "temporary/" + path;
        return new WebFaviconDTO(path);
    }

}
