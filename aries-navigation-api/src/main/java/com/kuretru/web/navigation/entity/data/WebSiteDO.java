package com.kuretru.web.navigation.entity.data;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kuretru.api.common.entity.data.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("web_site")
public class SiteDO extends BaseDO {

    private Integer categoryId;

    private String name;

    private String imageUrl;

    private String siteUrl;

    private Integer sequence;

}
