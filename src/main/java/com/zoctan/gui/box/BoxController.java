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
        // 谷歌支持段落翻译，可以将换行符去除
        // 其他API不支持段落翻译，会造成无翻译结果
        if (!(this.getCurrentApi() instanceof GoogleApi)) {
            query = query.replaceAll("[\t\r\n]", "");
        }
        return this.getCurrentApi().translate(query);
    }
}
