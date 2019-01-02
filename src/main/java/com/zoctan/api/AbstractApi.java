package com.zoctan.api;

import java.util.Map;

/**
 * API抽象
 *
 * @author Zoctan
 * @date 2018/06/29
 */
public abstract class AbstractApi {
  /**
   * 翻译
   *
   * @param query 待翻译词
   * @return 翻译结果
   */
  public String translate(final String query) {
    final String response = this.request(query);
    System.out.println(response);
    return this.getResult(response);
  }

  /**
   * 从响应中获得翻译结果
   *
   * @param response 响应
   * @return 翻译结果
   */
  abstract protected String getResult(String response);

  /**
   * 翻译请求
   *
   * @param query 待翻译词
   * @return 响应
   */
  abstract protected String request(final String query);

  /**
   * 建立翻译请求参数
   *
   * @param query 待翻译词
   * @return 请求参数
   */
  abstract protected Map<String, String> buildParams(String query);
}
