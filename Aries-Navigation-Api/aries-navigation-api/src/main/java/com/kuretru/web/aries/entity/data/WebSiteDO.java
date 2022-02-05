package com.kuretru.web.aries.entity.data;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kuretru.api.common.entity.data.BaseSequenceDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 站点数据对象
 * 站点实际数据
 *
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName("web_site")
public class WebSiteDO extends BaseSequenceDO {

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

    /** 访问次数 */
    private Integer visitCount;

}
