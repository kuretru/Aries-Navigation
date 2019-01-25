package com.kuretru.web.navigation.entity.view;

import lombok.Data;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Data
public class WebTagDataVO {

    private String name;

    private List<WebCategoryDataVO> categories;

}
