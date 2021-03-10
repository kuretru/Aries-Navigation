package com.kuretru.web.aries.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuretru.web.aries.entity.data.WebCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Mapper
@Repository
public interface WebCategoryMapper extends BaseMapper<WebCategoryDO> {

    /**
     * 查询指定TagId下最大的序列编号
     *
     * @param tagId 指定TagId
     * @return 最大的序列编号
     */
    Short getMaxSequence(String tagId);

}
