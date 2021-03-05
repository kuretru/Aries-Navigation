package com.kuretru.web.aries.service;

import com.kuretru.api.common.service.BaseService;
import com.kuretru.web.aries.entity.transfer.WebCategoryDTO;

import java.util.List;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
public interface WebCategoryService extends BaseService<WebCategoryDTO> {

    /**
     * 查询指定TagID下的所有记录
     *
     * @param tagId 指定TagID
     * @return 指定TagID下的所有记录
     */
    List<WebCategoryDTO> list(UUID tagId);

}
