package utils;

import bean.youdao.YouDaoBean;
import com.alibaba.fastjson.JSON;
import net.dongliu.requests.Requests;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class YouDaoUtils {
    private static final String API_URL = "https://openapi.youdao.com/api";
    private static final String APP_KEY = "5c9ff6cd28a58498";
    private static final String APP_SEC = "0WOi4190sDWm0bfbGXSdBCMmQ6z2kVZt";

    public static void main(final String[] args) {
        final String query = "Hello";
        final String response = request(query);
        final YouDaoBean youdaoBean = JSON.parseObject(response, YouDaoBean.class);
        System.out.println(youdaoBean.getBasic().getExplains());
    }

    public static String request(final String query) {
        //String from = "EN";
        //String to = "zh-CHS";
        final String from = "auto";
        final String to = "auto";
        final String salt = String.valueOf(System.currentTimeMillis());
        final String sign = md5(APP_KEY + query + salt + APP_SEC);

        final Map<String, Object> params = new HashMap<>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("salt", salt);
        params.put("sign", sign);
        params.put("appKey", APP_KEY);

        return Requests.post(API_URL).forms(params).send().readToText();
    }

    /**
     * 生成32位MD5摘要
     *
     * @param string string
     * @return MD5
     */
    public static String md5(final String string) {
        if (string == null) {
            return null;
        }
        final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            final byte[] btInput = string.getBytes("utf-8");
            //获得MD5摘要算法的 MessageDigest 对象
            final MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获得密文 */
            final byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            final int j = md.length;
            final char[] str = new char[j * 2];
            int k = 0;
            for (final byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return null;
        }
    }
}
