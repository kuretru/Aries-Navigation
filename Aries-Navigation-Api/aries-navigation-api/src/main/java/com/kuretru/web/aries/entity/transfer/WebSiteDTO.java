package com.kuretru.web.aries.entity.transfer;

import com.kuretru.api.common.entity.transfer.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WebSiteDTO extends BaseDTO {

    /** 所属分类ID */
    @NotNull
    private UUID categoryId;

    /** 站点名称 */
    @NotNull
    @Size(max = 16)
    private String name;

    /** 站点图标URL */
    @NotNull
    @Size(max = 50)
    private String imageUrl;

    /** 站点超链接URL */
    @NotNull
    @Size(max = 50)
    private String siteUrl;

    /** 站点描述 */
    @NotNull
    @Size(max = 50)
    private String description;

    /** 访问次数 */
    @NotNull
    @Min(0)
    private Integer visitCount;

}
