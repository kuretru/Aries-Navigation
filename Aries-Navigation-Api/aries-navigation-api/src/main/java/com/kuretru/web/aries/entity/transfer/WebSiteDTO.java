package com.kuretru.web.aries.entity.transfer;

import com.kuretru.api.common.entity.transfer.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WebSiteDTO extends BaseDTO {

    /** 所属分类ID */
    private UUID categoryId;

    /** 站点名称 */
    private String name;

    /** 站点图标URL */
    private String imageUrl;

    /** 站点超链接URL */
    private String siteUrl;

    /** 站点描述 */
    private String description;

}
