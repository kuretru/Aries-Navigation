package com.kuretru.web.aries.service.impl;

import com.kuretru.api.common.constant.code.UserErrorCodes;
import com.kuretru.api.common.exception.ServiceException;
import com.kuretru.api.common.service.impl.BaseSequenceServiceImpl;
import com.kuretru.web.aries.entity.data.WebTagDO;
import com.kuretru.web.aries.entity.query.WebTagQuery;
import com.kuretru.web.aries.entity.transfer.WebTagDTO;
import com.kuretru.web.aries.mapper.WebTagMapper;
import com.kuretru.web.aries.service.WebCategoryService;
import com.kuretru.web.aries.service.WebTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Service
public class WebTagServiceImpl extends BaseSequenceServiceImpl<WebTagMapper, WebTagDO, WebTagDTO, WebTagQuery> implements WebTagService {

    private final WebCategoryService webCategoryService;

    @Autowired
    public WebTagServiceImpl(WebTagMapper mapper, @Lazy WebCategoryService webCategoryService) {
        super(mapper, WebTagDO.class, WebTagDTO.class);
        this.webCategoryService = webCategoryService;
    }

    @Override
    public synchronized WebTagDTO save(WebTagDTO record) {
        WebTagDO data = dtoToDo(record);
        data.setUuid(UUID.randomUUID().toString());
        Instant now = Instant.now();
        data.setCreateTime(now);
        data.setUpdateTime(now);
        data.setSequence(getMaxSequence() + 1);
        mapper.insert(data);
        return get(data.getId());
    }

    @Override
    public void remove(UUID uuid) throws ServiceException {
        int childrenCount = webCategoryService.count(uuid);
        if (childrenCount > 0) {
            throw new ServiceException.BadRequest(UserErrorCodes.REQUEST_PARAMETER_ERROR,
                    "标签下存在未删除的分类，请先删除所有分类记录");
        }
        super.remove(uuid);
    }

}
