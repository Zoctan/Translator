package com.zoctan.translator.bean;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 有道翻译结果
 * http://ai.youdao.com/docs/doc-trans-api.s#p03
 *
 * @author Zoctan
 * @date 2018/5/25
 */
public class YoudaoBean {
    /**
     * 错误返回码
     * 一定存在
     */
    private Integer errorCode;
    /**
     * 源语言
     * 查询正确时一定存在
     */
    private String query;
    /**
     * 翻译结果
     * 查询正确时一定存在
     */
    private List<String> translation;
    /**
     * 词义 基本词典
     * 查词时才有
     */
    private Basic basic;
    /**
     * 翻译结果发音地址
     * 翻译成功一定存在
     */
    private String tSpeakUrl;
    /**
     * 源语言发音地址
     * 翻译成功一定存在
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

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(final Basic basic) {
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

    public static class Basic {
        /**
         * 默认音标，默认是英式音标
         * 查词成功，一定存在
         */
        private String phonetic;
        /**
         * 美式音标
         * 查词成功，一定存在
         */
        private String us_phonetic;
        /**
         * 英式音标
         * 查词成功，一定存在
         */
        private String uk_phonetic;
        /**
         * 英式发音
         * 查词成功，一定存在
         */
        private String uk_speech;
        /**
         * 美式发音
         * 查词成功，一定存在
         */
        private String us_speech;
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
    }
}