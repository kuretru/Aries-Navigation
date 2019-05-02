package com.kuretru.web.navigation.service;

import com.kuretru.api.common.service.BaseService;
import com.kuretru.web.navigation.entity.data.WebTagDO;
import com.kuretru.web.navigation.entity.transfer.WebTagDTO;
import com.kuretru.web.navigation.mapper.WebTagMapper;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
public interface WebTagService extends BaseService<WebTagMapper, WebTagDO, WebTagDTO> {

    /**
     * 查询数据库中当前最大的排序号
     *
     * @return 最大的排序号
     */
    int getMaxSequence();

}
