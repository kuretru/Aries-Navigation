package com.kuretru.web.aries.entity.view;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@NoArgsConstructor
@Schema(description = "站点分类(二级分类)-显示实体")
public class WebCategoryVO {

    @Schema(description = "记录ID")
    private UUID id;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "包含站点")
    private List<WebSiteVO> sites;

    public WebCategoryVO(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.sites = new ArrayList<>(0);
    }

}
