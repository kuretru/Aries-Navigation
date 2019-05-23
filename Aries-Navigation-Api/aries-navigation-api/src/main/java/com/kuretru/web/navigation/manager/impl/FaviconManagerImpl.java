package com.kuretru.web.navigation.manager.impl;

import com.kuretru.api.common.configuration.CommonProperties;
import com.kuretru.api.common.exception.ApiException;
import com.kuretru.api.common.exception.InvalidParametersException;
import com.kuretru.api.common.util.StringUtils;
import com.kuretru.web.navigation.manager.FaviconManager;
import lombok.Cleanup;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Service
public class FaviconManagerImpl implements FaviconManager {

    private static final String HTTP_URL_PREFIX = "http://";
    private static final String HTTPS_URL_PREFIX = "https://";
    private static final String FAVICON = "/favicon.ico";
    private final String TEMP_UPLOAD_PATH;

    @Autowired
    public FaviconManagerImpl(CommonProperties commonProperties) {
        this.TEMP_UPLOAD_PATH = commonProperties.getFileUploadRoot() + "temp" + File.separator;
    }

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
            throw new RuntimeException("给定URL地址不合法！");
        }
        int index = url.indexOf("/", 8);
        if (index == -1) {
            index = url.length();
        }
        String hostname = url.substring(0, index);
        return hostname + FAVICON;
    }

    @Override
    public String downloadFavicon(String urlString) throws ApiException {
        urlString = fixUrlPrefix(urlString);
        urlString = getFaviconUrl(urlString);
        String imagePath = TEMP_UPLOAD_PATH + UUID.randomUUID().toString() + ".ico";
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(2000);
            @Cleanup InputStream inputStream = connection.getInputStream();
            @Cleanup OutputStream outputStream = new FileOutputStream(imagePath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
        } catch (MalformedURLException e) {
            throw new InvalidParametersException("给定URL地址不合法！");
        } catch (IOException e) {
            throw new ApiException("下载网站图标失败！");
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
        return imagePath;
    }

}
