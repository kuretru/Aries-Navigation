package com.kuretru.web.navigation.entity.view;

import com.kuretru.web.navigation.entity.data.WebSiteDO;
import lombok.Data;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Data
public class SiteVO {

    private Long id;

    private String name;

    private String imageUrl;

    private String siteUrl;

    public SiteVO() {
        super();
    }

    public SiteVO(WebSiteDO record) {
        this.id = record.getId();
        this.name = record.getName();
        this.imageUrl = record.getImageUrl();
        this.siteUrl = record.getSiteUrl();
    }

}
