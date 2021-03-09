package com.kuretru.web.aries.service.impl;

import com.kuretru.api.common.service.impl.BaseServiceImpl;
import com.kuretru.web.aries.entity.data.WebTagDO;
import com.kuretru.web.aries.entity.transfer.WebTagDTO;
import com.kuretru.web.aries.mapper.WebTagMapper;
import com.kuretru.web.aries.service.WebTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Service
public class WebTagServiceImpl extends BaseServiceImpl<WebTagMapper, WebTagDO, WebTagDTO> implements WebTagService {

    @Autowired
    public WebTagServiceImpl(WebTagMapper mapper) {
        super(mapper, WebTagDO.class, WebTagDTO.class);
    }

    @Override
    public WebTagDTO save(WebTagDTO record) {
        WebTagDO data = dtoToDo(record);
        data.setUuid(UUID.randomUUID().toString());
        Instant now = Instant.now();
        data.setCreateTime(now);
        data.setUpdateTime(now);
        data.setSequence((short)(getMaxSequence() + 1));
        mapper.insert(data);
        return get(data.getId());
    }

    @Override
    public short getMaxSequence() {
        Short result = mapper.getMaxSequence();
        return result == null ? 0 : result;
    }

}
