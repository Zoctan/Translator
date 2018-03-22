package bean.youdao;

import java.util.List;

public class WebBean {
    private List<String> value;
    private String key;

    public List<String> getValue() {
        return value;
    }

    public void setValue(final List<String> value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }
}
