package bean.baidu;

import java.util.List;
import java.util.stream.Collectors;

/**
 * http://api.fanyi.baidu.com/api/trans/product/apidoc
 */
public class BaiduBean {
    // 翻译源语言
    private String from;
    // 译文语言
    private String to;
    // 翻译结果
    private List<TransResult> trans_result;
    // 原文
    private String src;
    // 译文
    private String dst;

    @Override
    public String toString() {
        try {
            return this.getTrans_result().stream()
                    .map(TransResult::getDst)
                    .collect(Collectors.joining(" ", "翻译：", ""));
        } catch (final NullPointerException ignored) {
            return "翻译：无法找到该单词哦";
        }
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(final String to) {
        this.to = to;
    }

    public List<TransResult> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(final List<TransResult> trans_result) {
        this.trans_result = trans_result;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(final String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(final String dst) {
        this.dst = dst;
    }
}
