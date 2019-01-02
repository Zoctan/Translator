package com.zoctan.utils;

import java.io.File;

/**
 * 文件工具
 *
 * @author Zoctan
 * @date 2019/01/02
 */
public class FileUtils {
  public static void delete(final String path) {
    final File file = new File(path);
    file.deleteOnExit();
  }

}
