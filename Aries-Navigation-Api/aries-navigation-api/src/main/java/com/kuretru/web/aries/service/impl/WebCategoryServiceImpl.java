package com.kuretru.web.aries.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuretru.microservices.web.constant.code.UserErrorCodes;
import com.kuretru.microservices.web.exception.ServiceException;
import com.kuretru.microservices.web.service.impl.BaseSequenceServiceImpl;
import com.kuretru.web.aries.entity.data.WebCategoryDO;
import com.kuretru.web.aries.entity.mapper.WebCategoryEntityMapper;
import com.kuretru.web.aries.entity.query.WebCategoryQuery;
import com.kuretru.web.aries.entity.transfer.WebCategoryDTO;
import com.kuretru.web.aries.entity.transfer.WebTagDTO;
import com.kuretru.web.aries.entity.view.WebCategoryVO;
import com.kuretru.web.aries.mapper.WebCategoryMapper;
import com.kuretru.web.aries.service.WebCategoryService;
import com.kuretru.web.aries.service.WebSiteService;
import com.kuretru.web.aries.service.WebTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Service
public class WebCategoryServiceImpl extends BaseSequenceServiceImpl<WebCategoryMapper, WebCategoryDO, WebCategoryDTO, WebCategoryQuery> implements WebCategoryService {

    private final WebTagService webTagService;
    private final WebSiteService webSiteService;

    @Autowired
    public WebCategoryServiceImpl(WebCategoryMapper mapper, WebCategoryEntityMapper entityMap, WebTagService webTagService, @Lazy WebSiteService webSiteService) {
        super(mapper, entityMap);
        this.webTagService = webTagService;
        this.webSiteService = webSiteService;
    }

    @Override
    public Map<UUID, List<WebCategoryVO>> listVO() {
        List<WebCategoryDTO> records = list();
        Map<UUID, List<WebCategoryVO>> result = new HashMap<>(16);
        for (WebCategoryDTO record : records) {
            List<WebCategoryVO> vos = result.getOrDefault(record.getTagId(), new ArrayList<>());
            result.put(record.getTagId(), vos);
            vos.add(new WebCategoryVO(record.getId(), record.getName()));
        }
        return result;
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

        // 业务逻辑：
        // 1. 同个Tag(一级分类下)不能有相同名字的二级分类，不同的Tag下可以有相同名字的二级分类
        // 2. 新增的时候遇到同名直接拒绝
        // 3. 更新的时候判断是否为自己
        QueryWrapper<WebCategoryDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_id", record.getTagId().toString());
        queryWrapper.eq("name", record.getName());
        WebCategoryDO webCategoryDO = mapper.selectOne(queryWrapper);
        boolean existInCurrentTag = webCategoryDO != null;
        if (existInCurrentTag) {
            boolean notCreating = record.getId() == null;
            boolean notUpdatingSelf = record.getId() != null
                    && !record.getId().toString().equals(Objects.requireNonNull(webCategoryDO).getUuid());
            if (notCreating || notUpdatingSelf) {
                throw new ServiceException(UserErrorCodes.REQUEST_PARAMETER_ERROR, "已存在指定名称的分类");
            }
        }
    }

    @Override
    protected void addDefaultOrderBy(QueryWrapper<WebCategoryDO> queryWrapper) {
        queryWrapper.orderByAsc("tag_id");
        super.addDefaultOrderBy(queryWrapper);
    }

}
