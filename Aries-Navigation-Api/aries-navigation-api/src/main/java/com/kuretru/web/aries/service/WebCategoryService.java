package com.kuretru.web.aries.service;

import com.kuretru.microservices.web.service.BaseSequenceService;
import com.kuretru.web.aries.entity.query.WebCategoryQuery;
import com.kuretru.web.aries.entity.transfer.WebCategoryDTO;
import com.kuretru.web.aries.entity.view.WebCategoryVO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
public interface WebCategoryService extends BaseSequenceService<WebCategoryDTO, WebCategoryQuery> {

    /**
     * 根据TagID，组织所有分类
     *
     * @return 所有分类数据
     */
    Map<UUID, List<WebCategoryVO>> listVO();

    /**
     * 查询指定TagID下的记录条数
     *
     * @param tagId 指定TagID
     * @return 记录条数
     */
    int count(UUID tagId);

}
