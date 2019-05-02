package com.kuretru.web.navigation.entity.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Data
public class WebSiteVO implements Serializable {

    @JsonIgnore
    private Long id;

    private String name;

    private String imageUrl;

    private String siteUrl;

}
