# Translator

Java 实现的翻译器，支持 OCR 屏幕取词

![1](https://github.com/Zoctan/Translator/blob/master/README/1.png)

![2](https://github.com/Zoctan/Translator/blob/master/README/2.png)

## 说明

**运行前提**

OCR 功能需要安装：
tesseract
tesseract-data-chi_sim
tesseract-data-eng

> 作者运行环境为 Linux，其他环境尚未测试

**取词功能实现**

1. 鼠标截取屏幕区域图片
2. OCR 识别图片
3. 翻译获取的图片文字

> 鼠标左键拖拽取词，右键退出

**API 扩展**

所有 API 均扩展自 AbstractApi。

目前只有谷歌的接口支持段落翻译，其他的只能单词翻译，而且有道翻译需要申请 key。

**将工程转换为 maven 工程**

在 pom.xml 文件上右键，选择 "Add as Maven Project"：

![maven](https://github.com/Zoctan/Translator/blob/master/README/maven.png)

**其他**

由于 jar 打包后有点大，所以暂时不提供 jar 文件，需自行打包。

## 更新日志

2019.01.02 OCR 识别完成。

2018.05.25 增加注释、日志模块。

2018.03.24 基本功能已完成。
