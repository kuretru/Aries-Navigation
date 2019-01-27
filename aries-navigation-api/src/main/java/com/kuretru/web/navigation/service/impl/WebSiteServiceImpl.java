package com.kuretru.web.navigation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuretru.api.common.service.impl.BaseServiceImpl;
import com.kuretru.api.common.util.PojoUtils;
import com.kuretru.web.navigation.entity.data.WebSiteDO;
import com.kuretru.web.navigation.entity.transfer.WebSiteDTO;
import com.kuretru.web.navigation.entity.view.WebSiteDataVO;
import com.kuretru.web.navigation.entity.view.WebSiteVO;
import com.kuretru.web.navigation.mapper.WebSiteMapper;
import com.kuretru.web.navigation.service.WebSiteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Service
public class WebSiteServiceImpl extends BaseServiceImpl<WebSiteMapper, WebSiteDO, WebSiteVO, WebSiteDTO> implements WebSiteService {

    @Override
    public List<WebSiteDataVO> listBySiteId(long siteId) {
        QueryWrapper<WebSiteDO> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", siteId);
        List<WebSiteDO> records = mapper.selectList(wrapper);
        return PojoUtils.map(records, WebSiteDataVO.class);
    }

}
