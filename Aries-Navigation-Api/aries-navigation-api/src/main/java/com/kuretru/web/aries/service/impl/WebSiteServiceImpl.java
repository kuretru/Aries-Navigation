package com.kuretru.web.aries.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuretru.microservices.web.constant.code.UserErrorCodes;
import com.kuretru.microservices.web.exception.ServiceException;
import com.kuretru.microservices.web.service.impl.BaseSequenceServiceImpl;
import com.kuretru.web.aries.entity.data.WebSiteDO;
import com.kuretru.web.aries.entity.mapper.WebSiteEntityMapper;
import com.kuretru.web.aries.entity.query.WebSiteQuery;
import com.kuretru.web.aries.entity.transfer.WebCategoryDTO;
import com.kuretru.web.aries.entity.transfer.WebSiteDTO;
import com.kuretru.web.aries.entity.view.WebSiteVO;
import com.kuretru.web.aries.mapper.WebSiteMapper;
import com.kuretru.web.aries.service.WebCategoryService;
import com.kuretru.web.aries.service.WebSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Service
public class WebSiteServiceImpl extends BaseSequenceServiceImpl<WebSiteMapper, WebSiteDO, WebSiteDTO, WebSiteQuery> implements WebSiteService {

    private final WebCategoryService webCategoryService;

    @Autowired
    public WebSiteServiceImpl(WebSiteMapper mapper, WebSiteEntityMapper entityMapper, WebCategoryService webCategoryService) {
        super(mapper, entityMapper);
        this.webCategoryService = webCategoryService;
    }

    @Override
    public Map<UUID, List<WebSiteVO>> listVO() {
        List<WebSiteDTO> records = list();
        Map<UUID, List<WebSiteVO>> result = new HashMap<>(16);
        for (WebSiteDTO record : records) {
            List<WebSiteVO> vos = result.getOrDefault(record.getCategoryId(), new ArrayList<>());
            result.put(record.getCategoryId(), vos);
            vos.add(new WebSiteVO(record.getId(), record.getName(), record.getImageUrl(), record.getSiteUrl(), record.getDescription()));
        }
        return result;
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
    protected void addDefaultOrderBy(QueryWrapper<WebSiteDO> queryWrapper) {
        queryWrapper.orderByAsc("category_id");
        super.addDefaultOrderBy(queryWrapper);
    }

}
