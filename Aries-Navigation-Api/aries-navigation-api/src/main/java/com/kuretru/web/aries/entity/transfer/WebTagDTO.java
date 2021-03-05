package com.kuretru.web.aries.entity.transfer;

import com.kuretru.api.common.entity.transfer.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WebTagDTO extends BaseDTO {

    /** 标签名称 */
    private String name;

}
