package com.kuretru.web.aries.entity.view;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@NoArgsConstructor
@Schema(description = "站点标签(一级分类)-显示实体")
public class WebTagVO {

    @Schema(description = "记录ID")
    private UUID id;

    @Schema(description = "标签名称")
    private String name;

    @Schema(description = "包含分类")
    private List<WebCategoryVO> categories;

    public WebTagVO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

}
