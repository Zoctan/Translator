package com.zoctan.bean.omi;

import java.util.List;
import java.util.stream.Collectors;

public class OmiBean {
    private List<List<String>> wordResults;
    private List<List<String>> sentExample;

    @Override
    public String toString() {
        final String wordResult = this.getWordResults().stream()
                .map(strings -> strings.get(0) + " " + strings.get(1) + "\n")
                .collect(Collectors.joining("", "词义：\n", ""));
        final String sentExample = this.getSentExample().stream()
                .map(strings -> strings.get(0) + "\n[" + strings.get(3) + "]\n")
                .collect(Collectors.joining("", "例句：\n", ""));
        return wordResult + sentExample;
    }

    public List<List<String>> getWordResults() {
        return wordResults;
    }

    public void setWordResults(final List<List<String>> wordResults) {
        this.wordResults = wordResults;
    }

    public List<List<String>> getSentExample() {
        return sentExample;
    }

    public void setSentExample(final List<List<String>> sentExample) {
        this.sentExample = sentExample;
    }
}
