package com.zoctan.bean.kingsoft;

import java.util.stream.Collectors;

public class KingSoftBean {
    private int status;
    private ContentBean content;

    @Override
    public String toString() {
        final String wordMean = this.getContent().getWord_mean().stream()
                .map(s -> s + "\n")
                .collect(Collectors.joining());
        return wordMean;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(final ContentBean content) {
        this.content = content;
    }
}
