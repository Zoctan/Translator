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
  /**
   * 布局
   */
  private JFrame frame;
  /**
   * API工厂
   */
  private final ApiFactory apiFactory;
  /**
   * API名称
   */
  private String[] apis;
  /**
   * 当前使用的API
   */
  private AbstractApi currentApi;

  public AbstractController(final JFrame f) {
    this.setFrame(f);
    this.apiFactory = new ApiFactory();
    this.setApis();
    // 默认为第一个API
    this.currentApi = this.apiFactory.get(this.apis[0]);
  }

  /**
   * 翻译
   *
   * @param query 待查词
   * @return 译后词
   */
  abstract public String translate(String query);

  public JFrame getFrame() {
    return this.frame;
  }

  private void setFrame(final JFrame f) {
    this.frame = f;
  }

  private void setApis() {
    this.apis = new String[this.apiFactory.getApiMap().size()];
    this.apiFactory.getApiMap().keySet().toArray(this.apis);
  }

  public String[] getApis() {
    return this.apis;
  }

  protected AbstractApi getCurrentApi() {
    return this.currentApi;
  }

  public void setCurrentApi(final String api) {
    this.currentApi = this.apiFactory.get(api);
  }
}
