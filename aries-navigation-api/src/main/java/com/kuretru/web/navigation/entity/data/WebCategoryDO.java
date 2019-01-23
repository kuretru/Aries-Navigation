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
@TableName("web_cateogry")
public class WebCategoryDO extends BaseDO {

    private Integer tagId;

    private String name;

    private Integer sequence;

}
