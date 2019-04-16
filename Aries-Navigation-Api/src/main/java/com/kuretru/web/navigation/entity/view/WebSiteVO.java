package com.kuretru.web.navigation.entity.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Data
public class WebSiteVO {

    @JsonIgnore
    private Long id;

    private String name;

    private String imageUrl;

    private String siteUrl;

}
