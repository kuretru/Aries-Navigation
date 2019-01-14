package com.kuretru.web.navigation.entity.view;

import com.kuretru.web.navigation.entity.data.CategoryDO;
import lombok.Data;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Data
public class CategoryVO {

    private Long id;

    private String name;

    public CategoryVO() {
        super();
    }

    public CategoryVO(CategoryDO record) {
        this.id = record.getId();
        this.name = record.getName();
    }

}
