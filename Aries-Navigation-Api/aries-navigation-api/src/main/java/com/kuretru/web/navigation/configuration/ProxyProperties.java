package com.kuretru.web.navigation.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@EnableConfigurationProperties(ProxyProperties.class)
@ConfigurationProperties(prefix = "kuretru.aries.proxy")
public class ProxyProperties {

    /**
     * 获取网站图标失败时是否尝试使用代理
     */
    @Value("${:false}")
    private Boolean enable;

    /**
     * 代理类型http,socks
     */
    @Value("${:http}")
    private String type;

    /**
     * 代理主机名
     */
    @Value("${:127.0.0.1}")
    private String hostname;

    /**
     * 代理端口
     */
    @Value("${:1080}")
    private Integer port;

}
