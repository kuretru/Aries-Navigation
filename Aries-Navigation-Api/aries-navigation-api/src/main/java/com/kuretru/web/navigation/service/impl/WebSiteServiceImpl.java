package com.kuretru.web.navigation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuretru.api.common.configuration.CommonProperties;
import com.kuretru.api.common.exception.ApiException;
import com.kuretru.api.common.exception.NotFoundException;
import com.kuretru.api.common.service.impl.BaseServiceImpl;
import com.kuretru.api.common.util.StringUtils;
import com.kuretru.web.navigation.configuration.SystemConstants;
import com.kuretru.web.navigation.entity.data.WebSiteDO;
import com.kuretru.web.navigation.entity.transfer.WebFaviconDTO;
import com.kuretru.web.navigation.entity.transfer.WebSiteDTO;
import com.kuretru.web.navigation.manager.FaviconManager;
import com.kuretru.web.navigation.mapper.WebSiteMapper;
import com.kuretru.web.navigation.service.WebSiteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public int getMaxSequence(long categoryId) {
        Integer result = mapper.getMaxSequence(categoryId);
        return result == null ? 0 : result;
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
    public WebSiteDTO save(WebSiteDTO record) throws ApiException {
        record.setImageUrl(handleImageUrl(record.getImageUrl()));
        WebSiteDO data = dtoToDO(record);
        data.addCrateTime();
        data.setSequence(getMaxSequence(record.getCategoryId()) + 1);
        mapper.insert(data);
        return get(data.getId());
    }

    @Override
    public WebSiteDTO update(WebSiteDTO record) throws ApiException {
        record.setImageUrl(handleImageUrl(record.getImageUrl()));
        return super.update(record);
    }

    @Override
    public int reorder(List<Long> idList) throws ApiException {
        List<WebSiteDO> records = new ArrayList<>(idList.size());
        int sequence = 1;
        for (Long id : idList) {
            WebSiteDO record = new WebSiteDO();
            record.setId(id);
            record.setSequence(sequence++);
            records.add(record);
        }
        Integer result = mapper.updateSequenceByIds(records);
        if (result == null || result != idList.size()) {
            throw new NotFoundException("部分记录不存在！");
        }
        return result;
    }

    @Override
    public WebFaviconDTO fetchFavicon(WebFaviconDTO record) throws ApiException {
        String path = faviconManager.downloadFavicon(record.getUrl());
        path = commonProperties.getFileCdnPrefix() + SystemConstants.TEMPORARY_DIRECTORY + "/" + path;
        return new WebFaviconDTO(path);
    }

    @Override
    public WebSiteDTO doToDTO(WebSiteDO record) {
        if (record == null) {
            return null;
        }
        WebSiteDTO result = new WebSiteDTO();
        BeanUtils.copyProperties(record, result);
        String imageUrl = commonProperties.getFileCdnPrefix() + record.getImageUrl();
        result.setImageUrl(imageUrl);
        return result;
    }

    private String handleImageUrl(String imageUrl) throws ApiException {
        if (StringUtils.isNullOrEmpty(imageUrl)) {
            return "";
        }
        int index = imageUrl.lastIndexOf("/");
        if (index == -1) {
            return imageUrl;
        }
        boolean temporary = imageUrl.contains(SystemConstants.TEMPORARY_DIRECTORY);
        imageUrl = imageUrl.substring(index + 1);
        if (temporary) {
            imageUrl = faviconManager.confirmFavicon(imageUrl);
        }
        return imageUrl;
    }

}
