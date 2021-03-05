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
public class WebCategoryDTO extends BaseDTO {

    /** 所属标签ID */
    private UUID tagId;

    /** 分类名称 */
    private String name;

}
