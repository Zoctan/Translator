package utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

import java.io.File;

public class Tess4jUtils {
    /**
     * 从图片中提取文字
     *
     * @param path 图片路径
     * @return 图片中文字
     */
    public static String readChar(String path) {
        ITesseract instance = new Tesseract();
        File imageFile = new File(path);
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
    public static String readChar(String path, String dataPath, String language) {
        File imageFile = new File(path);
        ITesseract instance = new Tesseract();
        instance.setDatapath(dataPath);
        instance.setLanguage(language);
        return getOCRText(instance, imageFile);
    }

    /**
     * 识别图片文件中的文字
     *
     * @param instance  Tesseract
     * @param imageFile 图片文件
     * @return 图片中文字
     */
    private static String getOCRText(ITesseract instance, File imageFile) {
        String result = null;
        // 使用classpath目录下的训练库
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        instance.setDatapath(tessDataFolder.getAbsolutePath());
        try {
            result = instance.doOCR(imageFile);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String ch = "src/resources/image/demo1.png";
        System.out.println(readChar(ch));
        ch = "src/resources/image/demo2.png";
        System.out.println(readChar(ch));
    }
}
