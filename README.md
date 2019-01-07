# Translator

Java 实现的翻译器，支持 OCR 屏幕取词

![1](https://github.com/Zoctan/Translator/blob/master/README/1.png)

![2](https://github.com/Zoctan/Translator/blob/master/README/2.png)

## 说明

> 在 IDE 中可以正常运行 OCR，但打包成 jar 后 OCR 出现问题，暂时未找到解决方法，欢迎在 issue 中提出解决方案 :)

**运行前提**

OCR 功能需要安装：

```
tesseract

tesseract-data-chi_sim

tesseract-data-eng
```

> 关于 Tesseract 请参考 Github：https://github.com/tesseract-ocr/tesseract/wiki

**取词功能实现**

1. 鼠标截取屏幕区域图片
2. OCR 识别图片
3. 翻译获取的图片文字

> 鼠标左键拖拽取词，右键退出

**API 扩展**

所有 API 均扩展自 AbstractApi。

目前只有谷歌的接口支持段落翻译，其他的只能单词翻译，而且有道翻译需要申请 key。

**将工程转换为 Maven 工程**

在 pom.xml 文件上右键，选择 "Add as Maven Project"：

![maven](https://github.com/Zoctan/Translator/blob/master/README/maven.png)

## 更新日志

2019.01.02 OCR 识别完成。

2018.05.25 增加注释、日志模块。

2018.03.24 基本功能已完成。
