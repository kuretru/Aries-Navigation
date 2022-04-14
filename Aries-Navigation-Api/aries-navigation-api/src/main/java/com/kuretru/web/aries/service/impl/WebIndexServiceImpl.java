package com.kuretru.web.aries.service.impl;

import com.kuretru.web.aries.entity.view.WebCategoryVO;
import com.kuretru.web.aries.entity.view.WebSiteVO;
import com.kuretru.web.aries.entity.view.WebTagVO;
import com.kuretru.web.aries.service.WebCategoryService;
import com.kuretru.web.aries.service.WebIndexService;
import com.kuretru.web.aries.service.WebSiteService;
import com.kuretru.web.aries.service.WebTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Service
public class WebIndexServiceImpl implements WebIndexService {

    private final WebTagService webTagService;
    private final WebCategoryService webCategoryService;
    private final WebSiteService webSiteService;

    @Autowired
    public WebIndexServiceImpl(WebTagService webTagService, WebCategoryService webCategoryService,
                               WebSiteService webSiteService) {
        this.webTagService = webTagService;
        this.webCategoryService = webCategoryService;
        this.webSiteService = webSiteService;
    }

    @Override
    public List<WebTagVO> getRecords(boolean useCache) {
        List<WebTagVO> result = webTagService.listVO();
        Map<UUID, List<WebCategoryVO>> categoryMap = webCategoryService.listVO();
        Map<UUID, List<WebSiteVO>> siteMap = webSiteService.listVO();
        for (WebTagVO tag : result) {
            if (categoryMap.containsKey(tag.getId())) {
                List<WebCategoryVO> categories = categoryMap.get(tag.getId());
                for (WebCategoryVO category : categories) {
                    if (siteMap.containsKey(category.getId())) {
                        category.setSites(siteMap.get(category.getId()));
                    }
                }
                tag.setCategories(categories);
            }
        }
        return result;
    }

}
