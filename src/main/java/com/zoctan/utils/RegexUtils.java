package com.zoctan.utils;

import java.util.regex.Pattern;

/**
 * 正则相关工具
 *
 * @author Zoctan
 * @date 2018/06/29
 */
public class RegexUtils {
  /**
   * 汉字正则 Unicode
   */
  private static final Pattern CHINESE_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");

  /**
   * 字符串是否包含汉字
   *
   * @param str 字符串
   * @return {boolean}
   */
  public static boolean containsChinese(final String str) {
    return CHINESE_PATTERN.matcher(str).find();
  }
}
