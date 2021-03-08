package com.kuretru.web.aries.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 存放RESTful API文档的常量类
 *
 * @author 呉真(kuretru) <kuretru@gmail.com>
 */
public class DocumentConstants {

    /** /api */
    public static Map<String, String> INDEX;

    static {
        INDEX = new HashMap<>(16);
        INDEX.put("ping_url", "/api/ping");
    }

    private DocumentConstants() {

    }

}
