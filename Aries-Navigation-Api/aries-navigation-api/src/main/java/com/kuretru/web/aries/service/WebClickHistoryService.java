package com.kuretru.web.aries.service;

import com.kuretru.web.aries.entity.transfer.WebClickHistoryDTO;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
public interface WebClickHistoryService {

    /**
     * 保存新记录
     *
     * @param record 新纪录
     */
    void save(WebClickHistoryDTO record);

}
