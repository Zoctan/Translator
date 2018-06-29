package com.zoctan.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 有道翻译响应实体
 * http://ai.youdao.com/docs/doc-trans-api.s#p03
 *
 * @author Zoctan
 * @date 2018/06/29
 */
public class YoudaoBean {
    /**
     * 错误返回码	一定存在
     */
    private Integer errorCode;
    /**
     * 源语言 查询正确时一定存在
     */
    private String query;
    /**
     * 翻译结果 查询正确时一定存在
     */
    private List<String> translation;
    /**
     * 词义 基本词典,查词时才有
     */
    private BasicBean basic;
    /**
     * 翻译结果发音地址 翻译成功一定存在
     */
    private String tSpeakUrl;
    /**
     * 源语言发音地址 翻译成功一定存在
     */
    private String speakUrl;

    @Override
    public String toString() {
        try {
            final String translation = this.getTranslation().stream()
                    .collect(Collectors.joining(" ", "翻译：", "\n"));

            final String phonetic = Stream.of(this.getBasic().getPhonetic())
                    .filter(Objects::nonNull)
                    .collect(Collectors.joining(" ", "音标：", "\n"));

            final String explains = this.getBasic().getExplains().stream()
                    .collect(Collectors.joining("\n", "词义：\n", ""));
            return translation + phonetic + explains;
        } catch (final NullPointerException ignored) {
            return "无法找到该单词哦";
        }
    }

    public static class BasicBean {
        /**
         * 默认音标，默认是英式音标，英文查词成功，一定存在
         */
        private String phonetic;
        /**
         * 美式音标，英文查词成功，一定存在
         */
        @JSONField(name = "us-phonetic")
        private String usPhonetic;
        /**
         * 英式音标，英文查词成功，一定存在
         */
        @JSONField(name = "uk-phonetic")
        private String ukPhonetic;
        /**
         * 英式发音，英文查词成功，一定存在
         */
        @JSONField(name = "uk-speech")
        private String ukSpeech;
        /**
         * 美式发音，英文查词成功，一定存在
         */
        @JSONField(name = "us-speech")
        private String usSpeech;
        /**
         * 基本释义
         */
        private List<String> explains;

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(final String phonetic) {
            this.phonetic = phonetic;
        }

        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(final List<String> explains) {
            this.explains = explains;
        }

        public String getUsPhonetic() {
            return usPhonetic;
        }

        public void setUsPhonetic(String usPhonetic) {
            this.usPhonetic = usPhonetic;
        }

        public String getUkPhonetic() {
            return ukPhonetic;
        }

        public void setUkPhonetic(String ukPhonetic) {
            this.ukPhonetic = ukPhonetic;
        }

        public String getUkSpeech() {
            return ukSpeech;
        }

        public void setUkSpeech(String ukSpeech) {
            this.ukSpeech = ukSpeech;
        }

        public String getUsSpeech() {
            return usSpeech;
        }

        public void setUsSpeech(String usSpeech) {
            this.usSpeech = usSpeech;
        }
    }


    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(final String query) {
        this.query = query;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(final List<String> translation) {
        this.translation = translation;
    }

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(final BasicBean basic) {
        this.basic = basic;
    }

    public String gettSpeakUrl() {
        return tSpeakUrl;
    }

    public void settSpeakUrl(final String tSpeakUrl) {
        this.tSpeakUrl = tSpeakUrl;
    }

    public String getSpeakUrl() {
        return speakUrl;
    }

    public void setSpeakUrl(final String speakUrl) {
        this.speakUrl = speakUrl;
    }
}