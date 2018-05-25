package com.zoctan.translator;

import com.zoctan.translator.api.AbstractApi;
import com.zoctan.translator.gui.boxView.BoxView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 翻译器
 *
 * @author Zoctan
 * @date 2018/5/25
 */
public class App {
    private final static Logger log = LoggerFactory.getLogger(AbstractApi.class);
    public static void main(final String[] args) {
        log.info("启动翻译器");
        new BoxView("Translator");
    }
}