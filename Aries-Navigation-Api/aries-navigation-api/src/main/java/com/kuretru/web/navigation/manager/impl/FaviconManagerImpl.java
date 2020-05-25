package com.kuretru.web.navigation.manager.impl;

import com.kuretru.api.common.configuration.CommonProperties;
import com.kuretru.api.common.exception.ApiException;
import com.kuretru.api.common.exception.InvalidParametersException;
import com.kuretru.api.common.util.StringUtils;
import com.kuretru.web.navigation.configuration.ProxyProperties;
import com.kuretru.web.navigation.configuration.SystemConstants;
import com.kuretru.web.navigation.manager.FaviconManager;
import com.zaxxer.hikari.pool.ProxyPreparedStatement;
import lombok.Cleanup;
import lombok.NonNull;
import org.springframework.aop.framework.ProxyProcessorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.util.UUID;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Service
public class FaviconManagerImpl implements FaviconManager {

    private static final String PROXY_TYPE_SOCKS = "socks";
    private static final String HTTP_URL_PREFIX = "http://";
    private static final String HTTPS_URL_PREFIX = "https://";
    private static final String FAVICON = "/favicon.ico";
    private final String TEMP_PATH;
    private final String ICON_PATH;

    private final ProxyProperties proxyProperties;

    @Autowired
    public FaviconManagerImpl(CommonProperties commonProperties, ProxyProperties proxyProperties) {
        this.TEMP_PATH = commonProperties.getFileUploadRoot() + SystemConstants.TEMPORARY_DIRECTORY + File.separator;
        this.ICON_PATH = commonProperties.getFileUploadRoot() + SystemConstants.ICON_DIRECTORY + File.separator;
        this.proxyProperties = proxyProperties;
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
    public String downloadFavicon(@NonNull String urlString) throws ApiException {
        urlString = fixUrlPrefix(urlString);
        urlString = getFaviconUrl(urlString);
        String imageName = UUID.randomUUID().toString() + ".ico";
        String imagePath = TEMP_PATH + imageName;
        try {
            tryDownloadFile(urlString, imagePath, false);
        } catch (MalformedURLException e) {
            throw new InvalidParametersException("给定URL地址不合法！");
        } catch (SocketTimeoutException e) {
            if (proxyProperties.getEnable()) {
                try {
                    tryDownloadFile(urlString, imagePath, true);
                } catch (IOException exp) {
                    throw new ApiException(exp.getClass().getSimpleName() + "：" + exp.getMessage());
                }
            }
        } catch (IOException e) {
            throw new ApiException(e.getClass().getSimpleName() + "：" + e.getMessage());
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
        return imageName;
    }

    @Override
    public String confirmFavicon(@NonNull String fileName) throws ApiException {
        String path = TEMP_PATH + fileName;
        String destPath = ICON_PATH + fileName;
        File file = new File(path);
        if (!file.exists()) {
            throw new InvalidParametersException("该图标文件不存在！");
        }
        File dest = new File(destPath);
        file.renameTo(dest);
        return SystemConstants.ICON_DIRECTORY + "/" + fileName;
    }

    private void tryDownloadFile(@NonNull String urlString, @NonNull String savePath, @NonNull Boolean useProxy) throws IOException {
        URL url = new URL(urlString);
        URLConnection connection = null;
        if (useProxy) {
            Proxy.Type type = Proxy.Type.HTTP;
            if (PROXY_TYPE_SOCKS.equalsIgnoreCase(proxyProperties.getType())) {
                type = Proxy.Type.SOCKS;
            }
            Proxy proxy = new Proxy(type, new InetSocketAddress(proxyProperties.getHostname(), proxyProperties.getPort()));
            connection = url.openConnection(proxy);
        } else {
            connection = url.openConnection();
        }
        connection.setConnectTimeout(2000);
        @Cleanup InputStream inputStream = connection.getInputStream();
        @Cleanup OutputStream outputStream = new FileOutputStream(savePath);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
    }

}
