package com.zoctan.gui;

import com.zoctan.api.AbstractApi;
import com.zoctan.api.ApiFactory;

import javax.swing.*;

/**
 * 抽象控制器
 *
 * @author Zoctan
 * @date 2018/06/29
 */
public abstract class AbstractController {
    private JFrame frame;
    private final ApiFactory apiFactory;
    private String[] apis;
    private AbstractApi defaultApi;

    public AbstractController(final JFrame f) {
        this.setFrame(f);
        apiFactory = new ApiFactory();
        this.setApis();
        defaultApi = apiFactory.get(apis[0]);
    }

    abstract public String translate(String query);

    public JFrame getFrame() {
        return frame;
    }

    private void setFrame(final JFrame f) {
        frame = f;
    }

    private void setApis() {
        apis = new String[apiFactory.getApiMap().size()];
        apiFactory.getApiMap().keySet().toArray(apis);
    }

    public String[] getApis() {
        return apis;
    }

    protected AbstractApi getDefaultApi() {
        return defaultApi;
    }

    public void setDefaultApi(final String api) {
        this.defaultApi = apiFactory.get(api);
    }
}
