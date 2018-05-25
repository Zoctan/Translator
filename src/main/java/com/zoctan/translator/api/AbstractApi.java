package com.zoctan.translator.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 抽象API，所有翻译API均实现该抽象
 *
 * @author Zoctan
 * @date 2018/5/25
 */
public abstract class AbstractApi {
    private final static Logger log = LoggerFactory.getLogger(AbstractApi.class);

    /*********************对外接口********************/
    /**
     * @param query 请求翻译的原文
     * @return String 翻译结果
     */
    public String translate(final String query) {
        final String response = this.request(query);
        log.info("翻译结果：{}", response);
        return this.getResult(response);
    }

    /*********************对内实现********************/
    /**
     * 获得翻译结果
     *
     * @param response 请求后的响应
     * @return String 翻译结果
     */
    abstract protected String getResult(String response);

    /**
     * 像API网址发出请求
     *
     * @param query 请求翻译的原文
     * @return String 响应
     */
    abstract protected String request(final String query);

    /**
     * 构建请求参数
     *
     * @param query 请求翻译的原文
     * @return Map 参数键值对
     */
    abstract protected Map<String, String> buildParams(String query);
}
