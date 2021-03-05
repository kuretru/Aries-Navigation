package com.kuretru.web.aries.entity.data;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kuretru.api.common.entity.data.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 站点数据对象
 *
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("web_site")
public class WebSiteDO extends BaseDO {

    /** 所属分类ID */
    private String categoryId;

    /** 站点名称 */
    private String name;

    /** 站点图标URL */
    private String imageUrl;

    /** 站点超链接URL */
    private String siteUrl;

    /** 站点描述 */
    private String description;

    /** 排序依据 */
    private Short sequence;

}
