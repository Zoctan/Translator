package bean.youdao;


import java.util.List;

public class BasicBean {
    // 默认音标，默认是英式音标，英文查词成功，一定存在
    private String phonetic;
    // 美式音标，英文查词成功，一定存在
    // private String us-phonetic;
    // 英式音标，英文查词成功，一定存在
    // private String uk-phonetic;
    // 英式发音，英文查词成功，一定存在
    // private String uk-speech;
    // 美式发音，英文查词成功，一定存在
    //private String us-speech;
    // 基本释义
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
