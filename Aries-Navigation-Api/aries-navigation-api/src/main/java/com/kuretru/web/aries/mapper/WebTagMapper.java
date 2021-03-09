package com.kuretru.web.aries.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuretru.web.aries.entity.data.WebTagDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Mapper
@Repository
public interface WebTagMapper extends BaseMapper<WebTagDO> {

    /**
     * 查询当前最大的序列编号
     *
     * @return 最大的序列编号
     */
    Short getMaxSequence();

}
