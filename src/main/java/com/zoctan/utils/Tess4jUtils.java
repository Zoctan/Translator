package com.zoctan.utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

/**
 * OCR识别工具
 * 本地需要安装相应的识别库
 * tesseract
 * tesseract-data-chi_sim
 * tesseract-data-eng
 *
 * @author Zoctan
 * @date 2018/06/29
 */
public class Tess4jUtils {

  public static void main(final String[] args) throws Exception {
    // 遍历image目录下的图片并识别
    final File imageDir = new File("image/");
    final File[] images = imageDir.listFiles();
    for (final File image : Objects.requireNonNull(images)) {
      System.out.println(Tess4jUtils.readChar(image.getAbsolutePath()));
    }
  }

  /**
   * 从图片中提取文字
   *
   * @param image 图片
   * @return 图片中文字
   */
  public static String readChar(final BufferedImage image) throws TesseractException {
    final ITesseract instance = getInstance();
    return instance.doOCR(image);
  }

  /**
   * 从图片中提取文字
   *
   * @param imagePath 图片路径
   * @return 图片中文字
   */
  public static String readChar(final String imagePath) throws TesseractException {
    final ITesseract instance = getInstance();
    final File imageFile = new File(imagePath);
    return instance.doOCR(imageFile);
  }

  private static ITesseract getInstance() {
    final ITesseract instance = new Tesseract();
    // 使用tessdata目录下的训练库
    // 由于jar打包后无法读取路径文件，所以只能放在外面
    final String dataPath = "tessdata";

    final File dataDir = new File(dataPath);
    if (!dataDir.exists()) {
      System.err.println("训练库路径无法读取 -> " + dataDir.getPath());
      System.exit(1);
    }
    instance.setDatapath(dataPath);
    return instance;
  }
}