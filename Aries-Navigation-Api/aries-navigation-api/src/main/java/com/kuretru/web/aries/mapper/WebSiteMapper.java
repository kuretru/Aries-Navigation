package com.kuretru.web.aries.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuretru.web.aries.entity.data.WebSiteDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Mapper
@Repository
public interface WebSiteMapper extends BaseMapper<WebSiteDO> {

    /**
     * 查询指定CategoryId下最大的序列编号
     *
     * @param categoryId 指定CategoryId
     * @return 最大的序列编号
     */
    Short getMaxSequence(String categoryId);

}
