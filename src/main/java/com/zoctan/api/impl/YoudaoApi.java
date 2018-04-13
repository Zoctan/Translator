package com.zoctan.api.impl;

import com.alibaba.fastjson.JSON;
import com.zoctan.api.AbstractApi;
import com.zoctan.api.annotation.ApiComponent;
import com.zoctan.bean.youdao.YoudaoBean;
import com.zoctan.utils.MD5Utils;
import net.dongliu.requests.Requests;

import java.util.HashMap;
import java.util.Map;

/**
 * http://ai.youdao.com/docs/doc-trans-api.s#p02
 */
@ApiComponent(name = "Youdao")
public class YoudaoApi extends AbstractApi {
    private static final String API_URL = "http://openapi.youdao.com/api";
    private static final String APP_KEY = "5c9ff6cd28a58498";
    private static final String APP_SEC = "0WOi4190sDWm0bfbGXSdBCMmQ6z2kVZt";

    @Override
    protected String request(final String query) {
        final Map<String, String> params = this.buildParams(query);
        return Requests.post(API_URL).forms(params).send().readToText();
    }

    @Override
    protected String getResult(final String response) {
        final YoudaoBean youdao = JSON.parseObject(response, YoudaoBean.class);
        return youdao.toString();
    }

    @Override
    protected Map<String, String> buildParams(final String query) {
        //String from = "EN";
        //String to = "zh-CHS";
        final String from = "auto";
        final String to = "auto";
        final String salt = String.valueOf(System.currentTimeMillis());
        final String sign = MD5Utils.md5(APP_KEY + query + salt + APP_SEC);

        final Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("salt", salt);
        params.put("sign", sign);
        params.put("appKey", APP_KEY);
        return params;
    }
}