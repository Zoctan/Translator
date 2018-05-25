package com.zoctan.translator.utils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具
 *
 * @author Zoctan
 * @date 2018/5/25
 */
public class MD5Utils {
    /**
     * 字符数组，存放16进制字符
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
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 获取文件的MD5值
     *
     * @param file 文件
     * @return 输入文件的MD5值
     */
    public static String md5(final File file) {
        try {
            if (!file.isFile()) {
                System.err.println("文件" + file.getAbsolutePath() + "不存在或者不是文件");
                return null;
            }

            final FileInputStream in = new FileInputStream(file);
            final String result = md5(in);
            in.close();

            return result;
        } catch (final IOException e) {
            return null;
        }
    }

    /**
     * 获得一个字符串的MD5值
     *
     * @param in 输入
     * @return 输入字符串的MD5值
     */
    public static String md5(final InputStream in) {
        try {
            final MessageDigest messagedigest = MessageDigest.getInstance("MD5");

            final byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                messagedigest.update(buffer, 0, read);
            }

            in.close();

            return byteArrayToHex(messagedigest.digest());
        } catch (final NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }

        return null;
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
