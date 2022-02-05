package com.kuretru.web.aries.entity.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@Schema(description = "站点分类(二级分类)-查询条件")
public class WebCategoryQuery {

    @Schema(description = "所属标签ID")
    private UUID tagId;

    @Size(max = 16)
    @Schema(description = "分类名称")
    private String name;

}
