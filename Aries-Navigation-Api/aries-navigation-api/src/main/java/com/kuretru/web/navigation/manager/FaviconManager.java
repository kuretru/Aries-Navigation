package com.kuretru.web.navigation.manager;

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

}
