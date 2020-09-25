package com.jiang.advances.peace.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.jiang.advances.peace.entity.TranslateResult;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheToCode {
    //D:\project\src\main\webapp\sysapp\org\employee\wework\emp_list.jsp
    private static final String format = "[\u4e00-\u9fa5]+";

    private static final String target = "E:\\c";
    private static final String pro = "E:\\文档\\20200911\\后端\\gjh\\java\\messages_zh_CN.properties";

    private static final String source = "D:\\project\\src\\main\\webapp\\sysapp\\attachment";

    static Map<String, String> resMap = new HashMap<>();

    static  String[] igrno = {
    };
    static List igrnoList = Arrays.asList(igrno);



    public static List<TranslateResult> resultList = null;
    static public List<TranslateResult> merge() {
        resultList = new ArrayList<>();
        System.out.println("程序运行中.....");
        Properties translate =
                new Properties();

        try (
                InputStream is = new FileInputStream(pro)
        ) {
            translate.load(is);

            translate.forEach( (k, v) -> resMap.put(v.toString(), k.toString()));

            //System.out.println(resMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println(resMap.get("人"));;


        //要修改的源文件
        File sourceFile = new File(source);
        create(sourceFile, target);
        System.out.println("程序结束");

        return resultList;
    }

    static void create(File file, String path) {
        if (!file.exists()) {
            return;
        }
        path = path + "\\" + file.getName();
        if (file.isDirectory()) {
            createDir(file, path);
        }else {
            createFile(file, path);
        }

    }

    static void createDir(File file, String path) {
        File target = new File(path);
        target.mkdir();
        for (File f : file.listFiles()) {
            create(f, path);
        }
    }

    static void createFile(File file, String path) {
        if (!file.getName().endsWith(".jsp")) {
            try {
                FileUtils.copyFile(file, new File(path));
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<String> un = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), StandardCharsets.UTF_8))
        ) {
            String line;

            while ((line = br.readLine()) != null) {
                Map<String, String>  envrep = new HashMap<>();
                Pattern pattern = Pattern.compile(format);
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String text = matcher.group();

                    TranslateResult translate = new TranslateResult();
                    resultList.add(translate);
                    translate.setPath(file.getPath());
                    translate.setTextLine(line);
                    translate.setText(text);
                    if (resMap.containsKey(text)) {
                        String replace = "<spring:message code = \'"
                                + resMap.get(text)
                                + "\' text = \'"
                                + text
                                + "\' />";
                        translate.setAftCode(replace);
                        envrep.put(text, replace);
                    }

                }
                for (Map.Entry<String, String> e : envrep.entrySet()) {
                    line = line.replace(e.getKey(), e.getValue());
                }
                bw.write(line);
                bw.newLine(); //添加换行
            }
        } catch (IOException e) {
        }
    }



    public static void excelCreat(List<TranslateResult> list) {
        String[] ss = source.split("\\\\");
        try {
            ExportParams params = new ExportParams();
            Workbook wook = ExcelExportUtil.exportExcel(params,TranslateResult.class, list);
            FileOutputStream fos = new FileOutputStream("E:/c/" + ss[ss.length - 1] +".xls");
            wook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        List<TranslateResult> list =  merge();
        excelCreat(list);
    }
}
