package com.kuretru.web.aries.service;

import com.kuretru.microservices.web.service.BaseSequenceService;
import com.kuretru.web.aries.entity.query.WebTagQuery;
import com.kuretru.web.aries.entity.transfer.WebTagDTO;
import com.kuretru.web.aries.entity.view.WebTagVO;

import java.util.List;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
public interface WebTagService extends BaseSequenceService<WebTagDTO, WebTagQuery> {

    /**
     * 列出所有标签数据
     *
     * @return 所有标签数据
     */
    List<WebTagVO> listVO();

}
