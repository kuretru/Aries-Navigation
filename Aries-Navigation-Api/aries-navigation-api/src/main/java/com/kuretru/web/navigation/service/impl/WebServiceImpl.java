package com.kuretru.web.navigation.service.impl;

import com.kuretru.web.navigation.configuration.SystemConstants;
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

    private WebMapper mapper;

    @Autowired
    public WebServiceImpl(WebMapper mapper) {
        this.mapper = mapper;
    }

    @Cacheable(value = CACHE_KEY, key = "#root.targetClass", condition = "#useCache != false")
    @Override
    public List<WebTagVO> list(boolean useCache) {
        return mapper.listTags();
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

}
