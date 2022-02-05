package com.kuretru.web.aries.entity.transfer;

import com.kuretru.api.common.entity.transfer.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "站点分类(二级分类)-数据传输实体")
public class WebCategoryDTO extends BaseDTO {

    @NotNull
    @Schema(description = "所属标签ID")
    private UUID tagId;

    @NotNull
    @Size(max = 16)
    @Schema(description = "分类名称")
    private String name;

}
