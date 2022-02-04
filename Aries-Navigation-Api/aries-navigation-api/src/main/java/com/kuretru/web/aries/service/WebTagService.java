package com.kuretru.web.aries.service;

import com.kuretru.api.common.service.BaseService;
import com.kuretru.web.aries.entity.query.WebTagQuery;
import com.kuretru.web.aries.entity.transfer.WebTagDTO;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
public interface WebTagService extends BaseService<WebTagDTO, WebTagQuery> {

    /**
     * 查询当前最大的序列编号
     *
     * @return 最大的序列编号
     */
    short getMaxSequence();

}
