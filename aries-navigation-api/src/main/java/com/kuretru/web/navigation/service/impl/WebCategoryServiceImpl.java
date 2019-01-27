package com.kuretru.web.navigation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuretru.api.common.service.impl.BaseServiceImpl;
import com.kuretru.api.common.util.PojoUtils;
import com.kuretru.web.navigation.entity.data.WebCategoryDO;
import com.kuretru.web.navigation.entity.transfer.WebCategoryDTO;
import com.kuretru.web.navigation.entity.view.WebCategoryDataVO;
import com.kuretru.web.navigation.entity.view.WebCategoryVO;
import com.kuretru.web.navigation.entity.view.WebSiteDataVO;
import com.kuretru.web.navigation.mapper.WebCategoryMapper;
import com.kuretru.web.navigation.service.WebCategoryService;
import com.kuretru.web.navigation.service.WebSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Service
public class WebCategoryServiceImpl extends BaseServiceImpl<WebCategoryMapper, WebCategoryDO, WebCategoryVO, WebCategoryDTO> implements WebCategoryService {

    private WebSiteService siteService;

    @Autowired
    public WebCategoryServiceImpl(WebSiteService siteService) {
        this.siteService = siteService;
    }

    @Override
    public List<WebCategoryDataVO> listByTagId(long tagId) {
        QueryWrapper<WebCategoryDO> wrapper = new QueryWrapper<>();
        wrapper.eq("tag_id", tagId);
        List<WebCategoryDO> records = mapper.selectList(wrapper);

        List<WebCategoryDataVO> result = PojoUtils.map(records, WebCategoryDataVO.class);
        for (int i = 0; i < result.size(); i++) {
            List<WebSiteDataVO> sites = siteService.listBySiteId(records.get(i).getId());
            result.get(i).setSites(sites);
        }
        return result;
    }

}
