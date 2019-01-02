package com.zoctan.gui;

import javax.swing.*;

/**
 * 抽象布局
 *
 * @author Zoctan
 * @date 2019/01/02
 */
public abstract class AbstractFrame extends JFrame {
  /**
   * 截屏回调
   */
  abstract public void screenShotCallback();
}
