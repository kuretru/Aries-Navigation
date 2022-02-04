package com.kuretru.web.aries.entity.transfer;

import com.kuretru.api.common.entity.transfer.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WebCategoryDTO extends BaseDTO {

    /** 所属标签ID */
    @NotNull
    private UUID tagId;

    /** 分类名称 */
    @NotNull
    @Size(max = 16)
    private String name;

}
