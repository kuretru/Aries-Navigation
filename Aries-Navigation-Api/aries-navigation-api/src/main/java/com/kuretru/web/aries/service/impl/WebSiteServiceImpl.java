package com.kuretru.web.aries.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuretru.microservices.web.constant.code.UserErrorCodes;
import com.kuretru.microservices.web.exception.ServiceException;
import com.kuretru.microservices.web.service.impl.BaseSequenceServiceImpl;
import com.kuretru.web.aries.entity.data.WebSiteDO;
import com.kuretru.web.aries.entity.query.WebSiteQuery;
import com.kuretru.web.aries.entity.transfer.WebCategoryDTO;
import com.kuretru.web.aries.entity.transfer.WebSiteDTO;
import com.kuretru.web.aries.mapper.WebSiteMapper;
import com.kuretru.web.aries.service.WebCategoryService;
import com.kuretru.web.aries.service.WebSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Service
public class WebSiteServiceImpl extends BaseSequenceServiceImpl<WebSiteMapper, WebSiteDO, WebSiteDTO, WebSiteQuery> implements WebSiteService {

    private final WebCategoryService webCategoryService;

    @Autowired
    public WebSiteServiceImpl(WebSiteMapper mapper, WebCategoryService webCategoryService) {
        super(mapper, WebSiteDO.class, WebSiteDTO.class);
        this.webCategoryService = webCategoryService;
    }

    @Override
    public int count(UUID categoryId) {
        QueryWrapper<WebSiteDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId.toString());
        return Math.toIntExact(mapper.selectCount(queryWrapper));
    }

    @Override
    protected void verifyDTO(WebSiteDTO record) throws ServiceException {
        WebCategoryDTO webCategoryDTO = webCategoryService.get(record.getCategoryId());
        if (webCategoryDTO == null) {
            throw new ServiceException(UserErrorCodes.REQUEST_PARAMETER_ERROR, "指定分类ID不存在，无法设置站点到此分类下");
        }
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
