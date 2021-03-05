package com.kuretru.web.aries.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuretru.api.common.service.impl.BaseServiceImpl;
import com.kuretru.web.aries.entity.data.WebCategoryDO;
import com.kuretru.web.aries.entity.transfer.WebCategoryDTO;
import com.kuretru.web.aries.mapper.WebCategoryMapper;
import com.kuretru.web.aries.service.WebCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Service
public class WebCategoryServiceImpl extends BaseServiceImpl<WebCategoryMapper, WebCategoryDO, WebCategoryDTO> implements WebCategoryService {

    @Autowired
    public WebCategoryServiceImpl(WebCategoryMapper mapper) {
        super(mapper, WebCategoryDO.class, WebCategoryDTO.class);
    }

    @Override
    public List<WebCategoryDTO> list(UUID tagId) {
        QueryWrapper<WebCategoryDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_id", tagId);
        queryWrapper.orderByAsc("sequence");
        List<WebCategoryDO> records = mapper.selectList(queryWrapper);
        return doToDto(records);
    }

}
