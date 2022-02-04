package com.kuretru.web.aries.entity.query;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
public class WebTagQuery {

    @Size(max = 16)
    private String name;

}
