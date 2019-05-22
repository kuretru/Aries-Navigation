package com.kuretru.web.navigation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuretru.api.common.service.impl.BaseServiceImpl;
import com.kuretru.web.navigation.entity.data.WebSiteDO;
import com.kuretru.web.navigation.entity.transfer.WebSiteDTO;
import com.kuretru.web.navigation.mapper.WebSiteMapper;
import com.kuretru.web.navigation.service.WebSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Service
public class WebSiteServiceImpl extends BaseServiceImpl<WebSiteMapper, WebSiteDO, WebSiteDTO> implements WebSiteService {

    @Autowired
    public WebSiteServiceImpl(WebSiteMapper mapper) {
        super(mapper, WebSiteDO.class, WebSiteDTO.class);
    }

    @Override
    public List<WebSiteDTO> list(long categoryId) {
        QueryWrapper<WebSiteDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.orderByAsc("sequence");
        List<WebSiteDO> records = mapper.selectList(queryWrapper);
        return doToDTO(records);
    }

}
