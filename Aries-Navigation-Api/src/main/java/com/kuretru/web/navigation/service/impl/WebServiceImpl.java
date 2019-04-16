package com.kuretru.web.navigation.service.impl;

import com.kuretru.web.navigation.entity.view.WebTagVO;
import com.kuretru.web.navigation.mapper.WebMapper;
import com.kuretru.web.navigation.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Service
public class WebServiceImpl implements WebService {

    private WebMapper mapper;

    @Autowired
    public WebServiceImpl(WebMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<WebTagVO> list() {
        return mapper.listTags();
    }

}
