package com.kuretru.web.aries.service.impl;

import com.kuretru.web.aries.entity.data.WebClickHistoryDO;
import com.kuretru.web.aries.entity.transfer.WebClickHistoryDTO;
import com.kuretru.web.aries.mapper.WebClickHistoryMapper;
import com.kuretru.web.aries.service.WebClickHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Service
public class WebClickHistoryServiceImpl implements WebClickHistoryService {

    private final WebClickHistoryMapper mapper;

    @Autowired
    public WebClickHistoryServiceImpl(WebClickHistoryMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(WebClickHistoryDTO record) {
        WebClickHistoryDO entry = new WebClickHistoryDO();
        entry.setCreateTime(Instant.now());
        entry.setSiteId(record.getSiteId());
        mapper.insert(entry);
    }

}
