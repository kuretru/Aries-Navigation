package com.kuretru.web.aries.entity.data;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kuretru.api.common.entity.data.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 站点标签数据对象
 *
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("web_tag")
public class WebTagDO extends BaseDO {

    /** 标签名称 */
    private String name;

    /** 排序依据 */
    private Short sequence;

}
