package com.kuretru.web.navigation.service;

import com.kuretru.api.common.service.BaseService;
import com.kuretru.web.navigation.entity.data.WebCategoryDO;
import com.kuretru.web.navigation.entity.transfer.WebCategoryDTO;
import com.kuretru.web.navigation.mapper.WebCategoryMapper;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
public interface WebCategoryService extends BaseService<WebCategoryMapper, WebCategoryDO, WebCategoryDTO> {

    /**
     * 获取指定TagID下的所有记录
     *
     * @param tagId TagID
     * @return 指定TagID下的所有记录DTO
     */
    List<WebCategoryDTO> list(long tagId);

}
