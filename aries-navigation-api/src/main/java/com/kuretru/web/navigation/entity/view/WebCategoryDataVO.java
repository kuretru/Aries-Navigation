package com.kuretru.web.navigation.entity.view;

import lombok.Data;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Data
public class WebCategoryDataVO {

    private String name;

    private List<WebSiteDataVO> sites;

}
