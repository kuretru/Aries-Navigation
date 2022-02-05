package com.kuretru.web.aries.entity.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@Schema(description = "站点标签(一级分类)-查询条件")
public class WebTagQuery {

    @Size(max = 16)
    @Schema(description = "标签名称")
    private String name;

}
