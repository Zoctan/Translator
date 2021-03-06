package com.zoctan.gui.box;

import com.zoctan.gui.AbstractController;
import com.zoctan.gui.AbstractFrame;
import com.zoctan.utils.ScreenShotUtils;
import com.zoctan.utils.Tess4jUtils;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;

/**
 * 箱布局
 *
 * @author Zoctan
 * @date 2018/06/29
 */
public class BoxView extends AbstractFrame {
  private static final Logger log = LoggerFactory.getLogger("BoxView");
  /**
   * 窗体宽和高
   */
  private static final int WIDTH = 500, HEIGHT = 400;
  /**
   * 存放组件的容器
   */
  private JPanel panelContainer;
  /**
   * 翻译前后的文字输入框
   * [0]翻译前
   * [1]翻译后
   */
  private final JTextArea[] textAreas = {this.newTextArea(), this.newTextArea()};
  /**
   * 系统的剪切板
   */
  private final Clipboard clipboard = this.getToolkit().getSystemClipboard();
  /**
   * 绑定控制器
   */
  private final AbstractController controller = new BoxController(this);

  public BoxView(final String title) {
    // 设置窗体标题
    this.setTitle(title);
    // 设置宽度 高度
    this.setSize(WIDTH, HEIGHT);
    // 关闭并退出的动作
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // 屏幕居中
    this.setLocationRelativeTo(null);
    // 可调整大小
    this.setResizable(true);
    // 设置组件
    this.setContentPane();
    // 设置界面可见
    this.setVisible(true);
  }

  /**
   * 设置包含的组件
   */
  private void setContentPane() {
    this.panelContainer = new JPanel();
    this.panelContainer.setLayout(new GridBagLayout());

    // 创建并添加组件
    // 三栏布局
    // 上栏水平和垂直方向均填充
    this.addPanel(this.createTextAreaPanel(this.textAreas[0]), 0, 0, 1.0, 1.0, GridBagConstraints.BOTH);
    // 中间栏按钮水平填充
    this.addPanel(this.createMiddlePanel(), 0, 1, 1.0, 0, GridBagConstraints.HORIZONTAL);
    // 下栏同上栏布局
    this.addPanel(this.createTextAreaPanel(this.textAreas[1]), 0, 2, 1.0, 1.0, GridBagConstraints.BOTH);

    this.setContentPane(this.panelContainer);
  }

  /**
   * 添加组件在界面上
   *
   * @param panel   组件
   * @param gridx   横坐标
   * @param gridy   纵坐标
   * @param weightx 横向占比
   * @param weighty 纵向占比
   * @param fill    填充方式
   */
  private void addPanel(final JPanel panel, final int gridx, final int gridy, final double weightx, final double weighty, final int fill) {
    final GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = gridx;
    constraints.gridy = gridy;
    constraints.weightx = weightx;
    constraints.weighty = weighty;
    constraints.fill = fill;
    this.panelContainer.add(panel, constraints);
  }

  /**
   * 新的输入框
   */
  private JTextArea newTextArea() {
    final JTextArea textArea = new JTextArea();
    // 默认字体 平滑 大小
    textArea.setFont(new Font("Default", Font.PLAIN, 19));
    // 选择文字时蓝色高亮
    textArea.setSelectedTextColor(Color.BLUE);
    // 自动换行
    textArea.setLineWrap(true);
    // 断行不断字
    textArea.setWrapStyleWord(true);
    return textArea;
  }

  /**
   * 创建输入框组件
   */
  private JPanel createTextAreaPanel(final JTextArea textArea) {
    final JPanel textAreaPanel = new JPanel();
    textAreaPanel.setLayout(new BoxLayout(textAreaPanel, BoxLayout.Y_AXIS));

    // 为输入框添加纵向滚动条
    final JScrollPane scrollPane = new JScrollPane(textArea);
    // 当纵向内容过多时才出现滚动条
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    // 上下空间距离
    textAreaPanel.add(Box.createVerticalStrut(10));
    textAreaPanel.add(scrollPane);
    textAreaPanel.add(Box.createVerticalStrut(10));
    return textAreaPanel;
  }

  @Override
  public void screenShotCallback(final BufferedImage image) {
    try {
      final String query = Tess4jUtils.readChar(image);
      final String response = controller.translate(query);
      textAreas[0].setText(query);
      textAreas[1].setText(response);
    } catch (final TesseractException e) {
      e.printStackTrace();
      log.error(e.getLocalizedMessage());
      JOptionPane.showMessageDialog(null, e.toString(), "Error Message", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * 创建按钮组件
   */
  private JPanel createMiddlePanel() {
    final JPanel middlePanel = new JPanel();
    middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));

    // 下拉框
    // 显示选项为API的名称
    final JComboBox<String> comboBox = new JComboBox<>(this.controller.getApis());
    comboBox.setBackground(Color.WHITE);
    // 切换选项时更改API
    comboBox.addItemListener(itemEvent -> {
      final String selectedItem = itemEvent.getItem().toString();
      this.controller.setCurrentApi(selectedItem);
    });

    // OCR选项
    final JButton ocrButton = new JButton("OCR");
    ocrButton.setBackground(Color.WHITE);
    ocrButton.addActionListener(event -> {
      // 截屏
      final ScreenShotUtils shotUtils = new ScreenShotUtils(this);
      shotUtils.shot();
    });

    // 翻译按钮
    final String translate = "translate";
    final String waiting = "waiting...";
    final JButton translateButton = new JButton(translate);
    translateButton.setBackground(Color.WHITE);
    translateButton.addActionListener(event -> {
      final SwingWorker syncTranslate = new SwingWorker<Void, Integer>() {
        @Override
        protected Void doInBackground() {
          translateButton.setText(waiting);
          final String query = textAreas[0].getText();
          final String response = controller.translate(query);
          textAreas[1].setText(response);
          return null;
        }

        @Override
        protected void done() {
          translateButton.setText(translate);
        }
      };
      syncTranslate.execute();
    });

    // 粘贴按钮
    final JButton pasteButton = new JButton("↑paste");
    pasteButton.setBackground(Color.WHITE);
    // 将剪切板内容复制到待翻译输入框
    pasteButton.addActionListener(event -> this.textAreas[0].paste());

    // 复制按钮
    final JButton copyButton = new JButton("↓copy");
    copyButton.setBackground(Color.WHITE);
    // 将翻译后的内容复制到剪切板
    copyButton.addActionListener(event -> {
      final String tempText = this.textAreas[1].getText();
      final StringSelection editText = new StringSelection(tempText);
      this.clipboard.setContents(editText, null);
    });

    // 清除按钮
    final JButton clearButton = new JButton("↑↓clear");
    clearButton.setBackground(Color.WHITE);
    // 将输入框内容都清空
    clearButton.addActionListener(event -> {
      for (final JTextArea textArea : this.textAreas) {
        textArea.setText(null);
      }
    });

    // 按钮横向布局
    final JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    // 添加水平胶水组件（不可见）
    // 控制按钮组件间的位置，让它们之间有固定宽度缝隙
    buttonPanel.add(Box.createHorizontalGlue());
    buttonPanel.add(comboBox);
    buttonPanel.add(Box.createHorizontalGlue());
    buttonPanel.add(ocrButton);
    buttonPanel.add(Box.createHorizontalGlue());
    buttonPanel.add(translateButton);
    buttonPanel.add(Box.createHorizontalGlue());
    buttonPanel.add(pasteButton);
    buttonPanel.add(Box.createHorizontalGlue());
    buttonPanel.add(copyButton);
    buttonPanel.add(Box.createHorizontalGlue());
    buttonPanel.add(clearButton);
    buttonPanel.add(Box.createHorizontalGlue());

    middlePanel.add(buttonPanel);
    return middlePanel;
  }
}