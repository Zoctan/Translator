package com.zoctan.api;

import java.util.Map;

/**
 * @author Zoctan
 * @date 2018/06/29
 */
public abstract class AbstractApi {
    public String translate(final String query) {
        final String response = this.request(query);
        System.out.println(response);
        return this.getResult(response);
    }

    abstract protected String getResult(String response);

    abstract protected String request(final String query);

    abstract protected Map<String, String> buildParams(String query);
}
