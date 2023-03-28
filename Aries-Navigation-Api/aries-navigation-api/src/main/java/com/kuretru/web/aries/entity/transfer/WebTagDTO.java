package com.kuretru.web.aries.entity.transfer;

import com.kuretru.microservices.web.entity.transfer.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "站点标签(一级分类)-数据传输实体")
public class WebTagDTO extends BaseDTO {

    @NotNull
    @Size(max = 16)
    @Schema(description = "标签名称")
    private String name;

    @NotNull
    @Schema(description = "是否为隐藏标签")
    private Boolean hidden;

}
