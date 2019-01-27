package com.kuretru.web.navigation.mapper;

import com.kuretru.web.navigation.entity.view.WebTagDataVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Mapper
@Repository
public interface WebDataMapper {

    List<WebTagDataVO> list();

}
