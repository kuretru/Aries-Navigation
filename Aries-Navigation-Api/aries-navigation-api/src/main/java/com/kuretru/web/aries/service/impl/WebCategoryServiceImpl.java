package com.kuretru.web.aries.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuretru.microservices.web.constant.code.UserErrorCodes;
import com.kuretru.microservices.web.exception.ServiceException;
import com.kuretru.microservices.web.service.impl.BaseSequenceServiceImpl;
import com.kuretru.web.aries.entity.data.WebCategoryDO;
import com.kuretru.web.aries.entity.query.WebCategoryQuery;
import com.kuretru.web.aries.entity.transfer.WebCategoryDTO;
import com.kuretru.web.aries.entity.transfer.WebTagDTO;
import com.kuretru.web.aries.mapper.WebCategoryMapper;
import com.kuretru.web.aries.service.WebCategoryService;
import com.kuretru.web.aries.service.WebSiteService;
import com.kuretru.web.aries.service.WebTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Service
public class WebCategoryServiceImpl extends BaseSequenceServiceImpl<WebCategoryMapper, WebCategoryDO, WebCategoryDTO, WebCategoryQuery> implements WebCategoryService {

    private final WebTagService webTagService;
    private final WebSiteService webSiteService;

    @Autowired
    public WebCategoryServiceImpl(WebCategoryMapper mapper, WebTagService webTagService, @Lazy WebSiteService webSiteService) {
        super(mapper, WebCategoryDO.class, WebCategoryDTO.class);
        this.webTagService = webTagService;
        this.webSiteService = webSiteService;
    }

    @Override
    public int count(UUID tagId) {
        QueryWrapper<WebCategoryDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_id", tagId.toString());
        return Math.toIntExact(mapper.selectCount(queryWrapper));
    }

    @Override
    public void remove(UUID uuid) throws ServiceException {
        int childrenCount = webSiteService.count(uuid);
        if (childrenCount > 0) {
            throw new ServiceException(UserErrorCodes.REQUEST_PARAMETER_ERROR, "分类下存在未删除的站点，请先删除所有站点记录");
        }
        super.remove(uuid);
    }

    @Override
    protected void verifyDTO(WebCategoryDTO record) throws ServiceException {
        WebTagDTO webTagDTO = webTagService.get(record.getTagId());
        if (webTagDTO == null) {
            throw new ServiceException(UserErrorCodes.REQUEST_PARAMETER_ERROR, "指定标签ID不存在，无法设置分类到此标签下");
        }
    }

    @Override
    protected WebCategoryDTO doToDto(WebCategoryDO record) {
        WebCategoryDTO result = super.doToDto(record);
        if (result != null) {
            result.setTagId(UUID.fromString(record.getTagId()));
        }
        return result;
    }

    @Override
    protected WebCategoryDO dtoToDo(WebCategoryDTO record) {
        WebCategoryDO result = super.dtoToDo(record);
        if (result != null) {
            result.setTagId(record.getTagId().toString());
        }
        return result;
    }

}
