# Translator

Java 实现的翻译器，支持 OCR 屏幕取词（暂未实现）

![1](https://github.com/Zoctan/Translator/blob/master/README/1.png)

![2](https://github.com/Zoctan/Translator/blob/master/README/2.png)

![3](https://github.com/Zoctan/Translator/blob/master/README/3.png)

![4](https://github.com/Zoctan/Translator/blob/master/README/4.png)

## 说明

**功能**

OCR 取词暂时不打算填坑。

鼠标取词（简易）：
1. 截整个屏幕图片
2. 获取鼠标的屏幕位置
3. 剪裁鼠标位置左上角图
4. OCR 识别图片，获得最后出现的一个单词

屏幕划词，和上面的鼠标取词类似。

**API 扩展**

所有 API 均扩展自 AbstractApi。

目前只有谷歌的接口支持段落翻译，其他的只能单词翻译，而且有道翻译需要申请 key。

**将工程转换为 maven 工程**

在 pom.xml 文件上右键，选择 "Add as Maven Project"：

![maven](https://github.com/Zoctan/Translator/blob/master/README/maven.png)

**其他**

由于 jar 打包后有点大，所以暂时不提供 jar 文件，需自行打包。

## 更新日志

2018.3.24 基本功能已完成。