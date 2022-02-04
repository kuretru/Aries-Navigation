package com.kuretru.web.aries.entity.query;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
public class WebSiteQuery {

    private UUID categoryId;

    @Size(max = 16)
    private String name;

}
