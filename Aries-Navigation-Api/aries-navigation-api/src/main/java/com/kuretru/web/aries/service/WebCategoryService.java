package com.kuretru.web.aries.service;

import com.kuretru.microservices.web.service.BaseSequenceService;
import com.kuretru.web.aries.entity.query.WebCategoryQuery;
import com.kuretru.web.aries.entity.transfer.WebCategoryDTO;

import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
public interface WebCategoryService extends BaseSequenceService<WebCategoryDTO, WebCategoryQuery> {

    /**
     * 查询指定TagID下的记录条数
     *
     * @param tagId 指定TagID
     * @return 记录条数
     */
    int count(UUID tagId);

}
