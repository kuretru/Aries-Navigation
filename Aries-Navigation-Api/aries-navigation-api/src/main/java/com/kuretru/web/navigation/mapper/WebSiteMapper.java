package com.kuretru.web.navigation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuretru.web.navigation.entity.data.WebSiteDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Mapper
@Repository
public interface WebSiteMapper extends BaseMapper<WebSiteDO> {

    /**
     * 查询数据库中指定分类下的最大的排序号
     *
     * @param categoryId CategoryID
     * @return 最大的排序号
     */
    Integer getMaxSequence(long categoryId);

    /**
     * 根据ID批量更新数据
     *
     * @param records 要更新的数据
     * @return 受影响的行数
     */
    Integer updateSequenceByIds(List<WebSiteDO> records);

}
