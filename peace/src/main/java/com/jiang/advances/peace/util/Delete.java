package com.jiang.advances.peace.util;

import java.io.File;

/**
 * Company: waiqin365
 * Description:
 * author:jjw
 * create 2021-01-25 16:07
 */
public class Delete {
    public static void main(String[] args) {
        //
        File file = new File("E:\\文档\\公司文档\\20200911\\generate\\employee");
        delete(file);
    }

    static void delete(File file) {

        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                delete(f);
                if (f.list().length == 0) {
                    f.delete();
                }
            } else {
                System.out.println(f.getName());
            }
        }

    }
}
