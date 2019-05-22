package com.kuretru.web.navigation.service.impl;

import com.kuretru.api.common.mapper.SystemOptionMapper;
import com.kuretru.api.common.service.impl.BaseSystemOptionServiceImpl;
import com.kuretru.web.navigation.service.SystemOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Service
public class SystemOptionServiceImpl extends BaseSystemOptionServiceImpl implements SystemOptionService {

    @Autowired
    public SystemOptionServiceImpl(SystemOptionMapper mapper) {
        super(mapper);
    }

}
