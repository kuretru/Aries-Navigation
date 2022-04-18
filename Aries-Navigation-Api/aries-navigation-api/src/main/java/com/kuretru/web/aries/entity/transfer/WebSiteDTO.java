package com.kuretru.web.aries.entity.transfer;

import com.kuretru.microservices.web.entity.transfer.BaseDTO;
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
@Schema(description = "站点数据-数据传输实体")
public class WebSiteDTO extends BaseDTO {

    @NotNull
    @Schema(description = "所属分类ID")
    private UUID categoryId;

    @NotNull
    @Size(max = 16)
    @Schema(description = "站点名称")
    private String name;

    @NotNull
    @Size(max = 250)
    @Schema(description = "站点图标URL")
    private String imageUrl;

    @NotNull
    @Size(max = 200)
    @Schema(description = "站点超链接URL")
    private String siteUrl;

    @NotNull
    @Size(max = 50)
    @Schema(description = "站点描述")
    private String description;

}
