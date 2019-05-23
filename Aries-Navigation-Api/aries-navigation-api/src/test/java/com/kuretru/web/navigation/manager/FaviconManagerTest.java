package com.kuretru.web.navigation.manager;

import com.kuretru.api.common.exception.ApiException;
import com.kuretru.web.navigation.ApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
public class FaviconManagerTest extends ApplicationTests {

    @Autowired
    private FaviconManager faviconManager;

    @Test
    public void fixUrlPrefix() {
        String url1 = "www.google.com";
        String url2 = "https://www.google.com";
        String url3 = "http://www.google.com";
        assertEquals(url3, faviconManager.fixUrlPrefix(url1));
        assertEquals(url2, faviconManager.fixUrlPrefix(url2));
        assertEquals(url3, faviconManager.fixUrlPrefix(url3));
    }

    @Test
    public void getFaviconUrl() {
        String url1 = "https://www.google.com/xxx?keywork=test";
        String url2 = "https://www.google.com";
        String url3 = "https://www.google.com/////";
        String answer = "https://www.google.com/favicon.ico";
        assertEquals(answer, faviconManager.getFaviconUrl(url1));
        assertEquals(answer, faviconManager.getFaviconUrl(url2));
        assertEquals(answer, faviconManager.getFaviconUrl(url3));
    }

    @Test
    public void downloadFavicon() throws ApiException {
        String url = "https://www.baidu.com/xxx?keywork=test";
        String path = faviconManager.downloadFavicon(url);
        System.out.println(path);
    }

}