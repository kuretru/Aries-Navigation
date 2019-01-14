package com.kuretru.web.navigation.entity.data;

import com.kuretru.api.common.entity.data.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TagDO extends BaseDO {

    private String name;

    private Integer sequence;

}
