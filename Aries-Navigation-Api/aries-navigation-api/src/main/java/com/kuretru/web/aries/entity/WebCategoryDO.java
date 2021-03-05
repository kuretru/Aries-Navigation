package com.kuretru.web.aries.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kuretru.common.entity.data.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 站点分类数据对象
 *
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("web_category")
public class WebCategoryDO extends BaseDO {

    /** 所属标签ID */
    private Long tagId;

    /** 分类名称 */
    private String name;

    /** 排序依据 */
    private Short sequence;

}
