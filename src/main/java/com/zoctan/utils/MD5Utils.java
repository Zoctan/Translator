package com.zoctan.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5相关工具
 *
 * @author Zoctan
 * @date 2018/06/29
 */
public class MD5Utils {
  /**
   * 16进制字符
   */
  private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

  /**
   * 获得一个字符串的MD5值
   *
   * @param input 输入的字符串
   * @return 输入字符串的MD5值
   */
  public static String md5(final String input) {
    if (input == null) {
      return null;
    }

    try {
      // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
      final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      // 输入的字符串转换成字节数组
      final byte[] inputByteArray = input.getBytes("utf-8");
      // inputByteArray是输入字符串转换得到的字节数组
      messageDigest.update(inputByteArray);
      // 转换并返回结果，也是字节数组，包含16个元素
      final byte[] resultByteArray = messageDigest.digest();
      // 字符数组转换成字符串返回
      return byteArrayToHex(resultByteArray);
    } catch (final NoSuchAlgorithmException | UnsupportedEncodingException e) {
      return null;
    }
  }

  private static String byteArrayToHex(final byte[] byteArray) {
    // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
    final char[] resultCharArray = new char[byteArray.length * 2];
    // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
    int index = 0;
    for (final byte b : byteArray) {
      resultCharArray[index++] = HEX_DIGITS[b >>> 4 & 0xf];
      resultCharArray[index++] = HEX_DIGITS[b & 0xf];
    }
    // 字符数组组合成字符串返回
    return new String(resultCharArray);
  }
}
