package com.kuretru.web.navigation.service.impl;

import com.kuretru.api.common.service.impl.BaseServiceImpl;
import com.kuretru.web.navigation.entity.data.WebTagDO;
import com.kuretru.web.navigation.entity.transfer.WebTagDTO;
import com.kuretru.web.navigation.entity.view.WebTagVO;
import com.kuretru.web.navigation.mapper.WebTagMapper;
import com.kuretru.web.navigation.service.WebTagService;
import org.springframework.stereotype.Service;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Service
public class WebTagServiceImpl extends BaseServiceImpl<WebTagMapper, WebTagDO, WebTagVO, WebTagDTO> implements WebTagService {

}
