package com.zoctan.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 金山翻译响应实体
 *
 * @author Zoctan
 * @date 2018/06/29
 */
public class KingSoftBean {
    /**
     * 状态
     */
    private int status;
    /**
     * 内容
     */
    private ContentBean content;

    @Override
    public String toString() {
        return this.getContent().getWordMean().stream()
                .map(s -> s + "\n")
                .collect(Collectors.joining());
    }

    public static class ContentBean {
        /**
         * 英式音标
         */
        @JSONField(name = "ph_en")
        private String phEn;
        /**
         * 美式音标
         */
        @JSONField(name = "ph_am")
        private String phAm;
        /**
         * 英式发音
         */
        @JSONField(name = "ph_en_mp3")
        private String phEnMp3;
        /**
         * 美式发音
         */
        @JSONField(name = "ph_am_mp3")
        private String phAmMp3;
        /**
         * TTS发音
         */
        @JSONField(name = "ph_tts_mp3")
        private String phTtsMp3;
        /**
         * 基本释义
         */
        @JSONField(name = "word_mean")
        private List<String> wordMean;

        public String getPhEn() {
            return this.phEn;
        }

        public void setPhEn(final String phEn) {
            this.phEn = phEn;
        }

        public String getPhAm() {
            return this.phAm;
        }

        public void setPhAm(final String phAm) {
            this.phAm = phAm;
        }

        public String getPhEnMp3() {
            return this.phEnMp3;
        }

        public void setPhEnMp3(final String phEnMp3) {
            this.phEnMp3 = phEnMp3;
        }

        public String getPhAmMp3() {
            return this.phAmMp3;
        }

        public void setPhAmMp3(final String phAmMp3) {
            this.phAmMp3 = phAmMp3;
        }

        public String getPhTtsMp3() {
            return this.phTtsMp3;
        }

        public void setPhTtsMp3(final String phTtsMp3) {
            this.phTtsMp3 = phTtsMp3;
        }

        public List<String> getWordMean() {
            return this.wordMean;
        }

        public void setWordMean(final List<String> wordMean) {
            this.wordMean = wordMean;
        }
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public ContentBean getContent() {
        return this.content;
    }

    public void setContent(final ContentBean content) {
        this.content = content;
    }
}
