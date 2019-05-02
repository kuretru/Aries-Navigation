package com.kuretru.web.navigation.entity.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Data
public class WebTagVO implements Serializable {

    @JsonIgnore
    private Long id;

    private String name;

    private List<WebCategoryVO> categories;

}
