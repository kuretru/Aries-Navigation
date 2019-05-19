package com.kuretru.web.navigation.service.impl;

import com.kuretru.api.common.exception.ApiException;
import com.kuretru.api.common.exception.NotFoundException;
import com.kuretru.api.common.service.impl.BaseServiceImpl;
import com.kuretru.web.navigation.entity.data.WebTagDO;
import com.kuretru.web.navigation.entity.transfer.WebTagDTO;
import com.kuretru.web.navigation.mapper.WebTagMapper;
import com.kuretru.web.navigation.service.WebTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Service
public class WebTagServiceImpl extends BaseServiceImpl<WebTagMapper, WebTagDO, WebTagDTO> implements WebTagService {

    @Autowired
    public WebTagServiceImpl(WebTagMapper mapper) {
        super(mapper, WebTagDO.class, WebTagDTO.class);
    }

    @Override
    public WebTagDTO save(WebTagDTO record) {
        WebTagDO data = dtoToDO(record);
        data.addCrateTime();
        data.setSequence(getMaxSequence() + 1);
        mapper.insert(data);
        return get(data.getId());
    }

    @Override
    public int getMaxSequence() {
        Integer result = mapper.getMaxSequence();
        return result == null ? 0 : result;
    }

    @Override
    public int reorder(List<Long> idList) throws ApiException {
        List<WebTagDO> records = new ArrayList<>(idList.size());
        int sequence = 1;
        for (Long id : idList) {
            WebTagDO record = new WebTagDO();
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

}
