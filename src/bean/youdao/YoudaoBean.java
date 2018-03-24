package bean.youdao;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * http://ai.youdao.com/docs/doc-trans-api.s#p03
 */
public class YoudaoBean {
    // 错误返回码	一定存在
    private Integer errorCode;
    // 源语言 查询正确时一定存在
    private String query;
    // 翻译结果 查询正确时一定存在
    private List<String> translation;
    // 词义 基本词典,查词时才有
    private BasicBean basic;
    // 词义 网络释义，该结果不一定存在
    private List<WebBean> web;
    // 源语言和目标语言 一定存在
    private String l;
    // 词典deeplink 查询语种为支持语言时，存在
    private List<DictBean> dict;
    // webdeeplink 查询语种为支持语言时，存在
    private List<DictBean> webdict;
    // 翻译结果发音地址 翻译成功一定存在
    private String tSpeakUrl;
    // 源语言发音地址 翻译成功一定存在
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
            return "翻译：无法找到该单词哦";
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

    public List<WebBean> getWeb() {
        return web;
    }

    public void setWeb(final List<WebBean> web) {
        this.web = web;
    }

    public String getL() {
        return l;
    }

    public void setL(final String l) {
        this.l = l;
    }

    public List<DictBean> getDict() {
        return dict;
    }

    public void setDict(final List<DictBean> dict) {
        this.dict = dict;
    }

    public List<DictBean> getWebdict() {
        return webdict;
    }

    public void setWebdict(final List<DictBean> webdict) {
        this.webdict = webdict;
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