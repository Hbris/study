package com.jiang.advances.peace.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class TranslateResult {
    @Excel(name = "文件", width = 50)
    public  String path;
    @Excel(name = "翻译文本", width = 50)
    public  String text;
    @Excel(name = "翻译文本行内容", width = 50)
    public  String textLine;
    @Excel(name = "替换前code", width = 30)
    public  String preCode;
    @Excel(name = "替换后code", width = 20)
    public  String aftCode;

    @Excel(name = "失败原因", width = 20)
    public  String fail;

    public  char codeSplit;
}
