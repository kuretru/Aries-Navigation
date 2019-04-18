package com.kuretru.web.navigation.service;

import com.kuretru.web.navigation.entity.view.WebTagVO;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
public interface WebService {

    /**
     * 列出首页收藏的站点信息
     *
     * @return 所有站点信息
     */
    List<WebTagVO> list();

    /**
     * 强制更新缓存
     *
     * @return 所有站点信息
     */
    List<WebTagVO> updateCache();

    /**
     * 强制删除缓存
     */
    void removeCache();

}
