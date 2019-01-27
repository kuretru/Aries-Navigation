package com.kuretru.web.navigation.entity.transfer;

import lombok.Data;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Data
public class WebSiteDTO {

    private Long id;

    private Long categoryId;

    private String name;

    private String imageUrl;

    private String siteUrl;

    private Integer sequence;

}
