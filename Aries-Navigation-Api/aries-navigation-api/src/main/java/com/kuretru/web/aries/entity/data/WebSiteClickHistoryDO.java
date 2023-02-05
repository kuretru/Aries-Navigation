package com.kuretru.web.aries.entity.data;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kuretru.microservices.web.entity.data.BaseHistoryDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 站点点击历史数据对象
 * 站点访问历史
 *
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName("web_site_click_history")
public class WebSiteClickHistoryDO extends BaseHistoryDO {

    /** 站点ID */
    private String siteId;

}
