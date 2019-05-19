package com.kuretru.web.navigation.service;

import com.kuretru.api.common.exception.ApiException;
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
     * 查询数据库中指定标签下的最大的排序号
     *
     * @param tagId TagID
     * @return 最大的排序号
     */
    int getMaxSequence(long tagId);

    /**
     * 获取指定TagID下的所有记录
     *
     * @param tagId TagID
     * @return 指定TagID下的所有记录DTO
     */
    List<WebCategoryDTO> list(long tagId);

    /**
     * 将给定的ID按升序重新排序
     *
     * @param idList 要重新排序的ID列表
     * @return 受影响的行数
     * @throws ApiException 业务异常
     */
    int reorder(List<Long> idList) throws ApiException;

}
