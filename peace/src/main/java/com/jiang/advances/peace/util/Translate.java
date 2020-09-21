package com.jiang.advances.peace.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.jiang.advances.peace.entity.TranslateResult;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Translate {
//D:\project\src\main\webapp\sysapp\org\employee\wework\emp_list.jsp

    private static final String target = "E:\\c";
    private static final String pro = "E:\\文档\\20200911\\后端\\gjh\\java\\messages_zh_CN.properties";
    private static final String source = "D:\\project\\src\\main\\webapp\\sysapp";
    //private static final String source = "D:\\project\\src\\main\\webapp\\sysapp\\org\\employee";
    //private static final String source = "D:\\project\\src\\main\\webapp\\sysapp\\attachment";
    static Map<String, String> resMap = new HashMap<>();

    static Map<String, List<String>> waitMap = new HashMap<>();
    static  String[] igrno = {
            };
    static  List igrnoList = Arrays.asList(igrno);



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

        //未翻译的数据
        waitMap.forEach( (k, v) -> {
            System.out.println("页面：" + k);
            v.forEach(System.out::println);
            System.out.println("页面：" + k + "结束");
        });
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
                String newLine = line;
                if (line.contains("spring:message")) {
                    int start = line.indexOf("<spring:message");
                    int end = line.indexOf("/>", start);
                    TranslateResult translate = new TranslateResult();
                    translate.setPath(file.getPath());

                    translate.setTextLine(line);

                    if (end > -1) {
                        String target = line.substring(start, end + 2);

                        target = target.replace(" ", "");

                        //system.out.println("target:"+target);

                        if (target.indexOf("text=") == -1) {

                            un.add(line);
                            bw.write(newLine);
                            bw.newLine(); //添加换行
                            continue;
                        }
                        int textS = target.indexOf("text=") + 6;

                        int textE  = target.indexOf(target.charAt(textS- 1), textS);
                        String text = target.substring(textS, textE);
                        translate.setText(text);
                        //System.out.println("text:"+text);

                        int codeStart = target.indexOf("code=") + 6;

                        char codeSplit = target.charAt(codeStart- 1);
                        int codeEnd = target.indexOf(target.charAt(codeStart- 1), codeStart);
                        //System.out.println(target);

                        String code = target.substring(codeStart, codeEnd);

                        translate.setPreCode(code);

                        int nc = newLine.indexOf("code", start + 1);

                        int split1 = newLine.indexOf(codeSplit,nc);
                        int split2 = newLine.indexOf(codeSplit,split1+1);

                        String p = newLine.substring(nc,split2 + 1);
                        System.out.println();

                        if (resMap.containsKey(text)) {
                            translate.setAftCode(resMap.get(text));
                            newLine = newLine.replace(p, "code = " + codeSplit + resMap.get(text) + codeSplit);
                        } else {
                            un.add(line);
                        }
                    } else {
                        un.add(line);
                    }
                    resultList.add(translate);

                }
                //System.out.println(newLine);
                bw.write(newLine);
                bw.newLine(); //添加换行
            }
        } catch (IOException e) {
        }
        if (!un.isEmpty()  && !igrnoList.contains(file.getName())) {
            if (waitMap.get(file.getPath()) != null) {
                waitMap.put(file.getName(), un);
            }else {
                waitMap.put(file.getPath(), un);
            }
        }
    }





    public static void excelCreat(List<TranslateResult> list) {
        String[] ss = source.split("\\\\");
        try {
            ExportParams params = new ExportParams();
            long start = new Date().getTime();
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
