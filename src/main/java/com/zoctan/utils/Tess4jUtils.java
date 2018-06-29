package com.zoctan.utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

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

    public static void main(final String[] args) throws NullPointerException {
        Tess4jUtils tess4jUtils = new Tess4jUtils();
        // 遍历/image/目录下的图片并识别
        File imageDir = new File(Tess4jUtils.class.getResource("/image/").getPath());
        File[] images = imageDir.listFiles();
        for (File image : Objects.requireNonNull(images)) {
            System.out.println(tess4jUtils.readChar(image.getAbsolutePath()));
        }
    }

    /**
     * 从图片中提取文字
     *
     * @param path 图片路径
     * @return 图片中文字
     */
    public String readChar(final String path) {
        final ITesseract instance = new Tesseract();
        final File imageFile = new File(path);
        return getOCRText(instance, imageFile);
    }

    /**
     * 从图片中提取文字
     *
     * @param path     图片路径
     * @param dataPath 训练库路径
     * @param language 语言字库
     * @return 图片中文字
     */
    public String readChar(final String path, final String dataPath, final String language) {
        final File imageFile = new File(path);
        final ITesseract instance = new Tesseract();
        instance.setDatapath(dataPath);
        instance.setLanguage(language);
        return getOCRText(instance, imageFile);
    }

    /**
     * 识别图片文件中的文字
     *
     * @param instance  Tesseract实例
     * @param imageFile 图片文件
     * @return 图片中文字
     */
    private String getOCRText(final ITesseract instance, final File imageFile) {
        String result = null;
        // 使用/tessdata目录下的训练库
        instance.setDatapath(this.getClass().getResource("/tessdata").getPath());
        try {
            result = instance.doOCR(imageFile);
        } catch (final TesseractException e) {
            e.printStackTrace();
        }
        return result;
    }
}
