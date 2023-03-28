package com.kuretru.web.aries.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuretru.microservices.web.constant.code.UserErrorCodes;
import com.kuretru.microservices.web.exception.ServiceException;
import com.kuretru.microservices.web.service.impl.BaseSequenceServiceImpl;
import com.kuretru.microservices.web.service.impl.BaseServiceImpl;
import com.kuretru.web.aries.entity.data.WebTagDO;
import com.kuretru.web.aries.entity.query.WebTagQuery;
import com.kuretru.web.aries.entity.transfer.WebTagDTO;
import com.kuretru.web.aries.entity.view.WebTagVO;
import com.kuretru.web.aries.mapper.WebTagMapper;
import com.kuretru.web.aries.service.WebCategoryService;
import com.kuretru.web.aries.service.WebTagService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Service
public class WebTagServiceImpl extends BaseSequenceServiceImpl<WebTagMapper, WebTagDO, WebTagDTO, WebTagQuery> implements WebTagService {

    private final WebCategoryService webCategoryService;

    @Autowired
    public WebTagServiceImpl(WebTagMapper mapper, WebTagEntityMapper entityMapper, @Lazy WebCategoryService webCategoryService) {
        super(mapper, entityMapper);
        this.webCategoryService = webCategoryService;
    }

    @Override
    public List<WebTagVO> listVO() {
        List<WebTagDTO> records = list();
        List<WebTagVO> result = new ArrayList<>(records.size());
        for (WebTagDTO record : records) {
            result.add(new WebTagVO(record.getId(), record.getName(), record.getHidden()));
        }
        return result;
    }

    @Override
    public void remove(UUID uuid) throws ServiceException {
        int childrenCount = webCategoryService.count(uuid);
        if (childrenCount > 0) {
            throw new ServiceException(UserErrorCodes.REQUEST_PARAMETER_ERROR, "标签下存在未删除的分类，请先删除所有分类记录");
        }
        super.remove(uuid);
    }

    @Override
    protected synchronized void verifyDTO(WebTagDTO record) throws ServiceException {
        QueryWrapper<WebTagDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", record.getName());
        WebTagDO webTagDO = mapper.selectOne(queryWrapper);
        if (webTagDO != null && !webTagDO.getUuid().equals(record.getId().toString())) {
            throw new ServiceException(UserErrorCodes.REQUEST_PARAMETER_ERROR, "已存在指定名称的标签");
        }
    }

    @Mapper(componentModel = "spring")
    interface WebTagEntityMapper extends BaseServiceImpl.BaseEntityMapper<WebTagDO, WebTagDTO> {

    }

}
