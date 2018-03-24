package api.impl;

import api.AbstractApi;
import api.annotation.ApiComponent;
import bean.baidu.BaiduBean;
import com.alibaba.fastjson.JSON;
import net.dongliu.requests.Requests;
import utils.MD5Utils;
import utils.RegexUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * http://api.fanyi.baidu.com/api/trans/product/apidoc
 */
@ApiComponent(name = "Baidu")
public class BaiduApi extends AbstractApi {
    private static final String API_URL = "http://api.fanyi.baidu.com/api/trans/vip/translate";
    private static final String APP_KEY = "20180323000139275";
    private static final String APP_SEC = "cQetrnErSwQXzeZqswA7";

    @Override
    protected String getResult(final String response) {
        final BaiduBean baidu = JSON.parseObject(response, BaiduBean.class);
        return baidu.toString();
    }

    @Override
    protected String request(final String query) {
        final Map<String, String> params = this.buildParams(query);
        return Requests.post(API_URL).forms(params).send().readToText();
    }

    @Override
    protected Map<String, String> buildParams(final String query) {
        // 源语言语种不确定时可设置为 auto，目标语言语种不可设置为 auto
        final String from = "auto";
        final String to = RegexUtils.isContainChinese(query) ? "en" : "zh";
        final String salt = String.valueOf(System.currentTimeMillis());
        final String sign = MD5Utils.md5(APP_KEY + query + salt + APP_SEC);

        final Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("appid", APP_KEY);
        params.put("salt", salt);
        params.put("sign", sign);
        return params;
    }
}
