package com.kuretru.web.aries.entity.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@Schema(description = "站点数据-查询条件")
public class WebSiteQuery {

    @NotNull
    @Schema(description = "所属分类ID")
    private UUID categoryId;

    @Size(max = 16)
    @Schema(description = "站点名称")
    private String name;

}
