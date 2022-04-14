package com.kuretru.web.aries.service;

import com.kuretru.web.aries.entity.view.WebTagVO;

import java.util.List;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
public interface WebIndexService {

    /**
     * 获取所有记录
     *
     * @param useCache 是否使用缓存
     * @return 所有记录
     */
    List<WebTagVO> getRecords(boolean useCache);

}
