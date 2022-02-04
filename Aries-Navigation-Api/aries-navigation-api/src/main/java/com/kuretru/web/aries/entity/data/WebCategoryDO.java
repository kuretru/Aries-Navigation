package com.kuretru.web.aries.entity.data;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kuretru.api.common.entity.data.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 站点分类数据对象
 * 站点二级分类
 *
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName("web_category")
public class WebCategoryDO extends BaseDO {

    /** 所属标签ID */
    private String tagId;

    /** 分类名称 */
    private String name;

    /** 排序依据 */
    private Short sequence;

}
