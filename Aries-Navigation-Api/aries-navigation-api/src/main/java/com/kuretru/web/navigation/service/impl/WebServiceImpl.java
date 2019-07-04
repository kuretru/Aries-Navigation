package com.kuretru.web.navigation.service.impl;

import com.kuretru.api.common.configuration.CommonProperties;
import com.kuretru.api.common.util.StringUtils;
import com.kuretru.web.navigation.configuration.SystemConstants;
import com.kuretru.web.navigation.entity.view.WebCategoryVO;
import com.kuretru.web.navigation.entity.view.WebSiteVO;
import com.kuretru.web.navigation.entity.view.WebTagVO;
import com.kuretru.web.navigation.mapper.WebMapper;
import com.kuretru.web.navigation.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Service
public class WebServiceImpl implements WebService {

    private static final String CACHE_KEY = "ARIES" + SystemConstants.INDEX_DATA_CACHE_KEY;

    private final WebMapper mapper;
    private final CommonProperties commonProperties;

    @Autowired
    public WebServiceImpl(WebMapper mapper, CommonProperties commonProperties) {
        this.mapper = mapper;
        this.commonProperties = commonProperties;
    }

    @Cacheable(value = CACHE_KEY, key = "#root.targetClass", condition = "#useCache != false")
    @Override
    public List<WebTagVO> list(boolean useCache) {
        List<WebTagVO> result = mapper.listTags();
        for (WebTagVO webTagVO : result) {
            List<WebCategoryVO> webCategories = webTagVO.getCategories();
            for (WebCategoryVO webCategoryVO : webCategories) {
                List<WebSiteVO> webSites = webCategoryVO.getSites();
                for (WebSiteVO webSiteVO : webSites) {
                    fixCdnUrl(webSiteVO);
                }
            }
        }
        return result;
    }

    @CachePut(value = CACHE_KEY, key = "#root.targetClass")
    @Override
    public List<WebTagVO> updateCache() {
        return list(false);
    }

    @CacheEvict(value = CACHE_KEY, key = "#root.targetClass")
    @Override
    public void removeCache() {

    }

    private void fixCdnUrl(WebSiteVO record) {
        if (!StringUtils.isNullOrEmpty(record.getImageUrl())) {
            String imageUrl = commonProperties.getFileCdnPrefix() + record.getImageUrl() + commonProperties.getFileCdnSuffix();
            record.setImageUrl(imageUrl);
        }
    }

}
