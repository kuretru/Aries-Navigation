package com.kuretru.web.navigation.service;

import com.kuretru.api.common.service.BaseService;
import com.kuretru.web.navigation.entity.transfer.WebCategoryDTO;
import com.kuretru.web.navigation.entity.view.WebCategoryDataVO;
import com.kuretru.web.navigation.entity.view.WebCategoryVO;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
public interface WebCategoryService extends BaseService<WebCategoryVO, WebCategoryDTO> {

    List<WebCategoryDataVO> listByTagId(long tagId);

}
