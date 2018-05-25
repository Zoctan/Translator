package com.zoctan.translator.bean;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 金山翻译结果
 *
 * @author Zoctan
 * @date 2018/5/25
 */
public class KingSoftBean {
    /**
     * 状态
     */
    private int status;
    /**
     * 主体内容
     */
    private Content content;

    @Override
    public String toString() {
        return this.getContent().getWord_mean().stream()
                .map(s -> s + "\n")
                .collect(Collectors.joining());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(final Content content) {
        this.content = content;
    }

    public static class Content {
        /**
         * 英式音标
         */
        private String ph_en;
        /**
         * 美式音标
         */
        private String ph_am;
        /**
         * 英式发音
         */
        private String ph_en_mp3;
        /**
         * 美式发音
         */
        private String ph_am_mp3;
        /**
         * 基本释义
         */
        private String ph_tts_mp3;
        /**
         * 基本释义
         */
        private List<String> word_mean;

        public String getPh_en() {
            return ph_en;
        }

        public void setPh_en(final String ph_en) {
            this.ph_en = ph_en;
        }

        public String getPh_am() {
            return ph_am;
        }

        public void setPh_am(final String ph_am) {
            this.ph_am = ph_am;
        }

        public String getPh_en_mp3() {
            return ph_en_mp3;
        }

        public void setPh_en_mp3(final String ph_en_mp3) {
            this.ph_en_mp3 = ph_en_mp3;
        }

        public String getPh_am_mp3() {
            return ph_am_mp3;
        }

        public void setPh_am_mp3(final String ph_am_mp3) {
            this.ph_am_mp3 = ph_am_mp3;
        }

        public String getPh_tts_mp3() {
            return ph_tts_mp3;
        }

        public void setPh_tts_mp3(final String ph_tts_mp3) {
            this.ph_tts_mp3 = ph_tts_mp3;
        }

        public List<String> getWord_mean() {
            return word_mean;
        }

        public void setWord_mean(final List<String> word_mean) {
            this.word_mean = word_mean;
        }
    }
}
