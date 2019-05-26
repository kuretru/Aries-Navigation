package com.kuretru.web.navigation.manager;

import com.kuretru.api.common.exception.ApiException;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
public interface FaviconManager {

    /**
     * 若URL前缀没有带http://或https://自动修复
     *
     * @param url 原始url
     * @return 修复过的url
     */
    String fixUrlPrefix(String url);

    /**
     * 给定一个网站URL，获取其网站图标的路径
     *
     * @param url 网站URL
     * @return 网站图标的路径
     */
    String getFaviconUrl(String url);

    /**
     * 下载网站图标
     *
     * @param url 网站图标路径
     * @return 本地存储的文件名
     * @throws ApiException 业务异常
     */
    String downloadFavicon(String url) throws ApiException;

    /**
     * 确认网站图标，将图标从临时目录移至正式目录
     *
     * @param fileName 网站图标文件名
     * @return 网站图标正式路径
     * @throws ApiException 业务异常
     */
    String confirmFavicon(String fileName) throws ApiException;

}
