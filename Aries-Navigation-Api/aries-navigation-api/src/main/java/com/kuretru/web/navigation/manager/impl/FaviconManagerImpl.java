package com.kuretru.web.navigation.manager.impl;

import com.kuretru.api.common.util.StringUtils;
import com.kuretru.web.navigation.manager.FaviconManager;
import lombok.NonNull;
import org.springframework.stereotype.Service;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Service
public class FaviconManagerImpl implements FaviconManager {

    private static final String HTTP_URL_PREFIX = "http://";
    private static final String HTTPS_URL_PREFIX = "https://";
    private static final String FAVICON = "/favicon.ico";

    @Override
    public String fixUrlPrefix(String url) {
        if (StringUtils.isNullOrEmpty(url)) {
            return url;
        }
        if (!url.startsWith(HTTP_URL_PREFIX) && !url.startsWith(HTTPS_URL_PREFIX)) {
            url = HTTP_URL_PREFIX + url;
        }
        return url;
    }

    @Override
    public String getFaviconUrl(@NonNull String url) {
        if (!url.startsWith(HTTP_URL_PREFIX) && !url.startsWith(HTTPS_URL_PREFIX)) {
            throw new RuntimeException("URL地址不合法");
        }
        int index = url.indexOf("/", 8);
        if (index == -1) {
            index = url.length();
        }
        String hostname = url.substring(0, index);
        return hostname + FAVICON;
    }

}
