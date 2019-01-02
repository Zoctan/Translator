package com.zoctan.utils;

import com.zoctan.gui.AbstractFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

/**
 * 鼠标截屏工具
 *
 * @author Zoctan
 * @date 2019/01/02
 */
public class ScreenShotUtils {
  private final AbstractFrame frame;
  private final String imagePath;

  public ScreenShotUtils(final AbstractFrame frame, final String imagePath) {
    this.frame = frame;
    this.imagePath = imagePath;
  }

  public void shot() {
    try {
      final ScreenShotWindow screenShotWindow = new ScreenShotWindow();
      screenShotWindow.setVisible(true);
    } catch (final AWTException e) {
      e.printStackTrace();
    }
  }

  class ScreenShotWindow extends JWindow {
    private int startX, startY;
    private final BufferedImage image;
    private BufferedImage tempImage = null;
    private BufferedImage saveImage = null;

    ScreenShotWindow() throws AWTException {
      // 获取屏幕尺寸
      final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
      final int screenWidth = dimension.width;
      final int screenHeight = dimension.height;

      this.setBounds(0, 0, screenWidth, screenHeight);

      // 截取屏幕
      this.image = new Robot().createScreenCapture(new Rectangle(0, 0, screenWidth, screenHeight));
      // 初始化鼠标监听事件
      initMouseListener();
    }

    private void exit() {
      setVisible(false);
      dispose();
    }

    private void initMouseListener() {
      this.addMouseListener(new MouseAdapter() {

        @Override
        public void mousePressed(final MouseEvent e) {
          // 右键退出
          if (e.isMetaDown()) {
            exit();
          }
          // 鼠标按下时记录开始点坐标
          startX = e.getX();
          startY = e.getY();
        }

        @Override
        public void mouseReleased(final MouseEvent e) {
          // 鼠标松开时自动保存截图
          try {
            ImageIO.write(saveImage, "jpg", new File(imagePath));
            exit();
            // 鼠标线程和主线程不在一起，需要回调识别图片
            frame.screenShotCallback();
          } catch (final IOException ex) {
            ex.printStackTrace();
          }
        }
      });

      this.addMouseMotionListener(new MouseMotionAdapter() {
        @Override
        public void mouseDragged(final MouseEvent e) {
          // 鼠标拖动时，记录坐标并重绘窗口
          final int endX = e.getX();
          final int endY = e.getY();

          // 临时图像，用于缓冲屏幕区域放置屏幕闪烁
          final Image tempImage2 = createImage(getWidth(), getHeight());
          final Graphics graphics = tempImage2.getGraphics();
          graphics.drawImage(tempImage, 0, 0, null);
          final int x = Math.min(startX, endX);
          final int y = Math.min(startY, endY);
          final int width = Math.abs(endX - startX) + 1;
          final int height = Math.abs(endY - startY) + 1;
          // 加上1防止width或height0
          graphics.setColor(Color.RED);
          graphics.drawRect(x - 1, y - 1, width + 1, height + 1);
          // 减1加1都了防止图片矩形框覆盖掉
          saveImage = image.getSubimage(x, y, width, height);
          graphics.drawImage(saveImage, x, y, null);

          getGraphics().drawImage(tempImage2, 0, 0, ScreenShotWindow.this);
        }
      });
    }

    @Override
    public void paint(final Graphics graphics) {
      final RescaleOp ro = new RescaleOp(1, 0, null);
      this.tempImage = ro.filter(this.image, null);
      graphics.drawImage(this.tempImage, 0, 0, this);
    }
  }
}