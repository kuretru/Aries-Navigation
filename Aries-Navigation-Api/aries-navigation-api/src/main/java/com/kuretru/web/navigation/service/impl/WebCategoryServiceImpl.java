package com.kuretru.web.navigation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuretru.api.common.exception.ApiException;
import com.kuretru.api.common.exception.NotFoundException;
import com.kuretru.api.common.service.impl.BaseServiceImpl;
import com.kuretru.web.navigation.entity.data.WebCategoryDO;
import com.kuretru.web.navigation.entity.data.WebTagDO;
import com.kuretru.web.navigation.entity.transfer.WebCategoryDTO;
import com.kuretru.web.navigation.mapper.WebCategoryMapper;
import com.kuretru.web.navigation.service.WebCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Service
public class WebCategoryServiceImpl extends BaseServiceImpl<WebCategoryMapper, WebCategoryDO, WebCategoryDTO> implements WebCategoryService {

    @Autowired
    public WebCategoryServiceImpl(WebCategoryMapper mapper) {
        super(mapper, WebCategoryDO.class, WebCategoryDTO.class);
    }

    @Override
    public int getMaxSequence(long tagId) {
        Integer result = mapper.getMaxSequence(tagId);
        return result == null ? 0 : result;
    }

    @Override
    public List<WebCategoryDTO> list(long tagId) {
        QueryWrapper<WebCategoryDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_id", tagId);
        queryWrapper.orderByAsc("sequence");
        List<WebCategoryDO> records = mapper.selectList(queryWrapper);
        return doToDTO(records);
    }

    @Override
    public int reorder(List<Long> idList) throws ApiException {
        List<WebCategoryDO> records = new ArrayList<>(idList.size());
        int sequence = 1;
        for (Long id : idList) {
            WebCategoryDO record = new WebCategoryDO();
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

    @Override
    public WebCategoryDTO save(WebCategoryDTO record) {
        WebCategoryDO data = dtoToDO(record);
        data.addCrateTime();
        data.setSequence(getMaxSequence(record.getTagId()) + 1);
        mapper.insert(data);
        return get(data.getId());
    }

}
