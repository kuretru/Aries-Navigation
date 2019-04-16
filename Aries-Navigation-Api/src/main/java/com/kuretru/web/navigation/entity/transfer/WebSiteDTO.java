package com.kuretru.web.navigation.entity.transfer;

import com.kuretru.api.common.entity.transfer.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WebSiteDTO extends BaseDTO {

    private Long categoryId;

    private String name;

    private String imageUrl;

    private String siteUrl;

}
