package com.zoctan.translator.utils;

import java.util.regex.Pattern;

/**
 * 正则工具
 *
 * @author Zoctan
 * @date 2018/5/25
 */
public class RegexUtils {
    private static Pattern CHINESE_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");

    public static boolean containsChinese(final String str) {
        return CHINESE_PATTERN.matcher(str).find();
    }
}
