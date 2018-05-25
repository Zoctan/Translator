package com.zoctan.translator.gui.boxView;

import com.zoctan.translator.api.impl.GoogleApi;
import com.zoctan.translator.gui.AbstractController;

import javax.swing.*;

/**
 * BOX布局控制器
 *
 * @author Zoctan
 * @date 2018/5/25
 */
class BoxController extends AbstractController {
    BoxController(final JFrame frame) {
        super(frame);
    }

    @Override
    public String translate(String query) {
        if (query == null) {
            return null;
        }
        // 如果设置的API不是谷歌的（只有谷歌能翻译段落）
        // 要清除空格|换行|制表符等，避免翻译失败
        if (!(this.getDefaultApi() instanceof GoogleApi)) {
            query = query.replaceAll("[\t\r\n]", "");
        }
        return this.getDefaultApi().translate(query);
    }
}
