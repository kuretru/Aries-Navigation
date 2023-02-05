package com.kuretru.web.aries.entity.transfer;

import com.kuretru.microservices.web.entity.transfer.BaseHistoryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WebSiteClickHistoryDTO extends BaseHistoryDTO {

    /** 站点ID */
    private UUID siteId;

}
