package com.zoctan.translator.gui.boxView;

import com.zoctan.translator.gui.AbstractController;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * BOX布局
 *
 * @author Zoctan
 * @date 2018/5/25
 */
public class BoxView extends JFrame {
    private static final int WIDTH = 500, HEIGHT = 400;
    private JPanel panelContainer;
    private JTextArea beforeTranslateTextArea, afterTranslateTextArea;
    private final Clipboard clipboard = this.getToolkit().getSystemClipboard();

    private final AbstractController controller = new BoxController(this);

    public BoxView(final String title) {
        this.setTitle(title);
        // 设置宽度 高度
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 屏幕居中
        this.setLocationRelativeTo(null);
        // 可调整大小
        this.setResizable(true);
        this.setContentPane();
        // 设置界面可见
        this.setVisible(true);
    }

    private void setContentPane() {
        panelContainer = new JPanel();
        panelContainer.setLayout(new GridBagLayout());

        // 创建并添加组件
        this.addPanel(this.createTopPanel(), 0, 0, 1.0, 1.0, GridBagConstraints.BOTH);
        this.addPanel(this.createMiddlePanel(), 0, 1, 1.0, 0, GridBagConstraints.HORIZONTAL);
        this.addPanel(this.createBottomPanel(), 0, 2, 1.0, 1.0, GridBagConstraints.BOTH);

        this.setContentPane(panelContainer);
    }

    private void addPanel(final JPanel panel, final int gridx, final int gridy, final double weightx, final double weighty, final int fill) {
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.fill = fill;
        panelContainer.add(panel, constraints);
    }

    private JPanel createTopPanel() {
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        beforeTranslateTextArea = new JTextArea("Hello");
        beforeTranslateTextArea.setFont(new Font("Default", Font.PLAIN, 19));
        beforeTranslateTextArea.setSelectedTextColor(Color.BLUE);
        // 自动换行
        beforeTranslateTextArea.setLineWrap(true);
        // 断行不断字
        beforeTranslateTextArea.setWrapStyleWord(true);
        // 纵向滚动条
        final JScrollPane scrollPane = new JScrollPane(beforeTranslateTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(scrollPane);
        topPanel.add(Box.createVerticalStrut(10));
        return topPanel;
    }

    private JPanel createMiddlePanel() {
        final JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));

        // 下拉框
        final JComboBox<String> comboBox;
        comboBox = new JComboBox<>(controller.getApis());
        comboBox.addItemListener(itemEvent -> {
            final String selectedItem = itemEvent.getItem().toString();
            // 设置选择翻译的API
            controller.setDefaultApi(selectedItem);
        });

        final JButton ocrButton = new JButton("ocr");
        ocrButton.addActionListener(event -> {
            final String a = beforeTranslateTextArea.getText();
            System.out.println(a);
        });

        final JButton translateButton = new JButton("translate");
        translateButton.addActionListener(event -> {
            final String query = beforeTranslateTextArea.getText();
            // 交由控制器翻译
            final String response = controller.translate(query);
            afterTranslateTextArea.setText(response);
        });

        final JButton pasteButton = new JButton("↑paste");
        pasteButton.addActionListener(event -> beforeTranslateTextArea.paste());

        final JButton copyButton = new JButton("↓copy");
        copyButton.addActionListener(event -> {
            final String tempText = afterTranslateTextArea.getText();
            final StringSelection editText = new StringSelection(tempText);
            clipboard.setContents(editText, null);
        });

        final JButton clearButton = new JButton("↑↓clear");
        clearButton.addActionListener(event -> {
            beforeTranslateTextArea.setText(null);
            afterTranslateTextArea.setText(null);
        });

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
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

    private JPanel createBottomPanel() {
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        afterTranslateTextArea = new JTextArea();
        afterTranslateTextArea.setFont(new Font("Default", Font.PLAIN, 19));
        afterTranslateTextArea.setSelectedTextColor(Color.BLUE);
        afterTranslateTextArea.setLineWrap(true);
        afterTranslateTextArea.setWrapStyleWord(true);
        final JScrollPane scrollPane = new JScrollPane(afterTranslateTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        bottomPanel.add(Box.createVerticalStrut(10));
        bottomPanel.add(scrollPane);
        bottomPanel.add(Box.createVerticalStrut(10));
        return bottomPanel;
    }
}