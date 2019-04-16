package com.kuretru.web.navigation.mapper;

import com.kuretru.web.navigation.entity.view.WebCategoryVO;
import com.kuretru.web.navigation.entity.view.WebSiteVO;
import com.kuretru.web.navigation.entity.view.WebTagVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Mapper
@Repository
public interface WebMapper {

    /**
     * 列出所有标签
     *
     * @return 所有标签
     */
    List<WebTagVO> listTags();

    /**
     * 列出指定标签下的所有分类
     *
     * @param tagId 标签ID
     * @return 所有分类
     */
    List<WebCategoryVO> listCategories(Long tagId);

    /**
     * 列出指定分类下的所有站点
     *
     * @param categoryId 分类ID
     * @return 所有站点
     */
    List<WebSiteVO> listSites(Long categoryId);

}
