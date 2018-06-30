package com.zoctan.api.impl;

import com.alibaba.fastjson.JSON;
import com.zoctan.api.AbstractApi;
import com.zoctan.api.annotation.ApiComponent;
import com.zoctan.bean.OmiBean;
import com.zoctan.utils.RegexUtils;
import net.dongliu.requests.Requests;

import java.util.HashMap;
import java.util.Map;

/**
 * 欧米API
 *
 * @author Zoctan
 * @date 2018/06/29
 */
@ApiComponent(name = "Omi")
public class OmiApi extends AbstractApi {
    private static final String API_URL = "http://www.alifanyi1688.com/transSents.do";

    @Override
    protected String request(final String query) {
        final Map<String, String> params = this.buildParams(query);
        return Requests.post(API_URL).body(params).send().readToText();
    }

    @Override
    protected String getResult(final String response) {
        return JSON.parseObject(response, OmiBean.class).toString();
    }

    @Override
    protected Map<String, String> buildParams(final String query) {
        String from = "e";
        String to = "c";
        if (RegexUtils.containsChinese(query)) {
            from = "c";
            to = "e";
        }

        final Map<String, String> params = new HashMap<>(3);
        params.put("languageType", from + "2" + to);
        params.put("userDbName", "");
        params.put("sentsToTrans", query);
        return params;
    }
}
