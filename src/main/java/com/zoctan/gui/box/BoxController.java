package com.zoctan.gui.box;

import com.zoctan.api.impl.GoogleApi;
import com.zoctan.gui.AbstractController;

import javax.swing.*;

/**
 * 箱布局控制器
 *
 * @author Zoctan
 * @date 2018/06/29
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
        // 谷歌支持段落翻译
        if (!(this.getDefaultApi() instanceof GoogleApi)) {
            query = query.replaceAll("[\t\r\n]", "");
        }
        return this.getDefaultApi().translate(query);
    }
}
