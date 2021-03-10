package com.kuretru.web.aries.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuretru.api.common.constant.code.UserErrorCodes;
import com.kuretru.api.common.exception.ServiceException;
import com.kuretru.api.common.service.impl.BaseServiceImpl;
import com.kuretru.web.aries.entity.data.WebSiteDO;
import com.kuretru.web.aries.entity.transfer.WebCategoryDTO;
import com.kuretru.web.aries.entity.transfer.WebSiteDTO;
import com.kuretru.web.aries.mapper.WebSiteMapper;
import com.kuretru.web.aries.service.WebCategoryService;
import com.kuretru.web.aries.service.WebSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Service
public class WebSiteServiceImpl extends BaseServiceImpl<WebSiteMapper, WebSiteDO, WebSiteDTO> implements WebSiteService {

    private final WebCategoryService webCategoryService;

    @Autowired
    public WebSiteServiceImpl(WebSiteMapper mapper, WebCategoryService webCategoryService) {
        super(mapper, WebSiteDO.class, WebSiteDTO.class);
        this.webCategoryService = webCategoryService;
    }

    @Override
    public List<WebSiteDTO> list(UUID categoryId) {
        QueryWrapper<WebSiteDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId.toString());
        queryWrapper.orderByAsc("sequence");
        List<WebSiteDO> records = mapper.selectList(queryWrapper);
        return doToDto(records);
    }

    @Override
    public int count(UUID categoryId) {
        QueryWrapper<WebSiteDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId.toString());
        return mapper.selectCount(queryWrapper);
    }

    @Override
    public WebSiteDTO save(WebSiteDTO record) throws ServiceException {
        WebCategoryDTO webCategoryDTO = webCategoryService.get(record.getCategoryId());
        if (webCategoryDTO == null) {
            throw new ServiceException.BadRequest(UserErrorCodes.REQUEST_PARAMETER_ERROR,
                    "指定分类ID不存在，无法在此分类下新增站点");
        }

        WebSiteDO data = dtoToDo(record);
        data.setUuid(UUID.randomUUID().toString());
        Instant now = Instant.now();
        data.setCreateTime(now);
        data.setUpdateTime(now);
        data.setSequence((short)(getMaxSequence(record.getCategoryId()) + 1));
        mapper.insert(data);
        return get(data.getId());
    }

    @Override
    public WebSiteDTO update(WebSiteDTO record) throws ServiceException {
        WebCategoryDTO webCategoryDTO = webCategoryService.get(record.getCategoryId());
        if (webCategoryDTO == null) {
            throw new ServiceException.BadRequest(UserErrorCodes.REQUEST_PARAMETER_ERROR,
                    "指定分类ID不存在，无法移动分类到此分类下");
        }
        return super.update(record);
    }

    @Override
    public short getMaxSequence(UUID categoryId) {
        Short result = mapper.getMaxSequence(categoryId.toString());
        return result == null ? 0 : result;
    }

    @Override
    protected WebSiteDTO doToDto(WebSiteDO record) {
        WebSiteDTO result = super.doToDto(record);
        if (result != null) {
            result.setCategoryId(UUID.fromString(record.getCategoryId()));
        }
        return result;
    }

    @Override
    protected WebSiteDO dtoToDo(WebSiteDTO record) {
        WebSiteDO result = super.dtoToDo(record);
        if (result != null) {
            result.setCategoryId(record.getCategoryId().toString());
        }
        return result;
    }

}
