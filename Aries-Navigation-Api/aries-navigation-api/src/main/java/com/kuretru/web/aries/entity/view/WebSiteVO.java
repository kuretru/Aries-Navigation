package com.kuretru.web.aries.entity.view;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "站点数据-显示实体")
public class WebSiteVO {

    @Schema(description = "记录ID")
    private UUID id;

    @Schema(description = "站点名称")
    private String name;

    @Schema(description = "站点图标URL")
    private String imageUrl;

    @Schema(description = "站点超链接URL")
    private String siteUrl;

}
