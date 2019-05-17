package com.kuretru.web.navigation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuretru.api.common.service.impl.BaseServiceImpl;
import com.kuretru.web.navigation.entity.data.WebCategoryDO;
import com.kuretru.web.navigation.entity.transfer.WebCategoryDTO;
import com.kuretru.web.navigation.mapper.WebCategoryMapper;
import com.kuretru.web.navigation.service.WebCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<WebCategoryDTO> list(long tagId) {
        QueryWrapper<WebCategoryDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_id", tagId);
        queryWrapper.orderByAsc("sequence");
        List<WebCategoryDO> records = mapper.selectList(queryWrapper);
        return doToDTO(records);
    }

}
