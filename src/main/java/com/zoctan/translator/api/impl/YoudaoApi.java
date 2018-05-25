package com.zoctan.translator.api.impl;

import com.alibaba.fastjson.JSON;
import com.zoctan.translator.api.AbstractApi;
import com.zoctan.translator.api.annotation.ApiComponent;
import com.zoctan.translator.bean.YoudaoBean;
import com.zoctan.translator.utils.MD5Utils;
import net.dongliu.requests.Requests;

import java.util.HashMap;
import java.util.Map;

/**
 * 有道 API：http://ai.youdao.com/docs/doc-trans-api.s#p02
 * 需要申请使用秘钥
 * 只能翻译单词
 *
 * @author Zoctan
 * @date 2018/5/25
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

        final Map<String, String> params = new HashMap<>(6);
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("salt", salt);
        params.put("sign", sign);
        params.put("appKey", APP_KEY);
        return params;
    }
}
