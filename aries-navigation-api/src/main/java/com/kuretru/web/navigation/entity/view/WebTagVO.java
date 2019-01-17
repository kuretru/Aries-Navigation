package com.kuretru.web.navigation.entity.view;

import com.kuretru.web.navigation.entity.data.WebTagDO;
import lombok.Data;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Data
public class TagVO {

    private Long id;

    private String name;

    private List<SiteVO> sites;

    public TagVO() {
        super();
    }

    public TagVO(WebTagDO record) {
        this.id = record.getId();
        this.name = record.getName();
    }

}
