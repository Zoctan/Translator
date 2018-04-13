package com.zoctan.api.impl;

import com.alibaba.fastjson.JSON;
import com.zoctan.api.AbstractApi;
import com.zoctan.api.annotation.ApiComponent;
import com.zoctan.bean.kingsoft.KingSoftBean;
import com.zoctan.utils.RegexUtils;
import net.dongliu.requests.Requests;

import java.util.HashMap;
import java.util.Map;

@ApiComponent(name = "KingSoft")
public class KingSoftApi extends AbstractApi {
    private static final String API_URL = "http://fy.iciba.com/ajax.php?a=fy";

    @Override
    protected String getResult(final String response) {
        final KingSoftBean kingSoftBean = JSON.parseObject(response, KingSoftBean.class);
        return kingSoftBean.toString();
    }

    @Override
    protected String request(final String query) {
        final Map<String, String> params = this.buildParams(query);
        return Requests.post(API_URL).forms(params).send().readToText();
    }

    @Override
    protected Map<String, String> buildParams(final String query) {
        String from = "en";
        String to = "zh";
        if (RegexUtils.containsChinese(query)) {
            from = "zh";
            to = "en";
        }

        final Map<String, String> params = new HashMap<>();
        params.put("f", from);
        params.put("t", to);
        params.put("w", query);
        return params;
    }
}
