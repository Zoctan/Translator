package com.zoctan.gui.boxView;

import com.zoctan.api.impl.GoogleApi;
import com.zoctan.gui.AbstractController;

import javax.swing.*;

class BoxController extends AbstractController {
    BoxController(final JFrame frame) {
        super(frame);
    }

    @Override
    public String translate(String query) {
        if (query == null) {
            return null;
        }
        if (!(this.getDefaultApi() instanceof GoogleApi)) {
            query = query.replaceAll("[\t\r\n]", "");
        }
        return this.getDefaultApi().translate(query);
    }
}
