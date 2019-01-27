package com.kuretru.web.navigation.service.impl;

import com.kuretru.api.common.service.impl.BaseServiceImpl;
import com.kuretru.web.navigation.entity.data.WebCategoryDO;
import com.kuretru.web.navigation.entity.transfer.WebCategoryDTO;
import com.kuretru.web.navigation.entity.view.WebCategoryDataVO;
import com.kuretru.web.navigation.entity.view.WebCategoryVO;
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
public class WebCateogryServiceImpl extends BaseServiceImpl<WebCategoryMapper, WebCategoryDO, WebCategoryVO, WebCategoryDTO> implements WebCategoryService {

    private WebSiteService siteService;

    @Autowired
    public WebCateogryServiceImpl(WebSiteService siteService) {
        this.siteService = siteService;
    }

    @Override
    public List<WebCategoryDataVO> listData(long tagId) {
        return null;
    }

}
