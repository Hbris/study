package com.jiang.advances.peace.util;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.jiang.advances.peace.entity.TranslateResult;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Translate {
//D:\project\src\main\webapp\sysapp\org\employee\wework\emp_list.jsp

    private static final String target = "E:\\c";
    private static final String pro = "E:\\文档\\公司文档\\20200911\\gjh\\java\\messages_zh_CN.properties";
    //private static final String source = "D:\\project\\src\\main\\webapp\\sysapp";
    //private static final String source = "D:\\project\\src\\main\\webapp\\sysapp\\org\\employee";

    //private static final String source = "D:\\project\\src\\main\\webapp\\platform\\sm\\menu_ext";
    private static final String source = "D:\\project\\src\\main\\webapp\\sysapp\\org\\employee";
    //private static final String source = "D:\\project\\src\\main\\webapp\\platform\\sm\\oplog";
    //private static final String source = "D:\\project\\src\\main\\webapp\\sysapp\\attachment";
    static Map<String, String> resMap = new HashMap<>();

    static  String[] igrno = {
            };
    static  List igrnoList = Arrays.asList(igrno);

    public static void main(String[] args) {
        List<TranslateResult> list =  merge();
        list.stream().filter(t -> t.getFail() != null).map(e -> e.getText()).distinct().forEach(a -> System.out.println(a));
        excelCreat(list);

    }


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

                //当前行搜索位置
                int spaStart = 0;
                while (line.indexOf("spring:message", spaStart) != -1) {
                    //实体类  导出用
                    TranslateResult translate = new TranslateResult();
                    resultList.add(translate);
                    translate.setPath(file.getPath());
                    translate.setTextLine(line);

                    //替换内容搜索起始位置
                    int start = line.indexOf("<spring:message", spaStart);
                    int end = line.indexOf("/>", start);
                    spaStart = end +1;

                    //当前内容有问题,没有结束符
                    if (end == -1) {

                        un.add(line);
                        break;
                    }

                    String target = line.substring(start, end + 2);
                    target = target.replace(" ", "");
                    //设置文本内容
                    serachText(target, translate);

                    if (translate.getText() == null || translate.getText().isBlank()) {
                        translate.setFail("没有text");
                        un.add(line);
                        continue;
                    }


                    serachCode(target, translate);

                    if (translate.getPreCode() == null) {
                        translate.setFail("没有code");
                        un.add(line);
                        continue;
                    }

                    //和code一起替换
                    int nc = line.indexOf("code", start + 1);
                    int split1 = line.indexOf(translate.getCodeSplit(),nc);
                    int split2 = line.indexOf(translate.getCodeSplit(),split1+1);
                    String p = line.substring(nc,split2 + 1);


                    if (resMap.containsKey(translate.getText())) {
                        translate.setAftCode(resMap.get(translate.getText()));
                        envrep.put(p, "code = " + translate.getCodeSplit() + resMap.get(translate.getText()) + translate.getCodeSplit());

                    } else {
                        translate.setFail("没有中文对应的翻译");
                        un.add(line);
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

    static void serachText(String target, TranslateResult translate) {
        if (target.indexOf("text=") == -1) {
            return;
        }
        int textS = target.indexOf("text=") + 6;

        int textE  = target.indexOf(target.charAt(textS- 1), textS);
        String text = target.substring(textS, textE);
        translate.setText(text);

    }

    static void serachCode(String target, TranslateResult translate) {

        int codeStart = target.indexOf("code=") + 6;
        if (codeStart == 5) {
            return;
        }

        char codeSplit = target.charAt(codeStart- 1);

        int codeEnd = target.indexOf(codeSplit, codeStart);

        String code = target.substring(codeStart, codeEnd);

        translate.setPreCode(code);
        translate.setCodeSplit(codeSplit);
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





}
