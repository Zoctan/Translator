package com.zoctan.bean.kingsoft;

import java.util.List;

public class ContentBean {
    // 英式音标
    private String ph_en;
    // 美式音标
    private String ph_am;
    // 英式发音
    private String ph_en_mp3;
    // 美式发音
    private String ph_am_mp3;
    private String ph_tts_mp3;
    // 基本释义
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
