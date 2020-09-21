package learn;

import com.jiang.advances.peace.entity.TranslateResult;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Testasd {
    @Test
    public void a() throws IOException {
        String line = "<spring:message code = ' ' text = '序号'/>";
        String newLine = line;
        int start = line.indexOf("<spring:message");
        int end = line.indexOf("/>", start);
        TranslateResult translate = new TranslateResult();

        translate.setTextLine(line);

        if (end > -1) {
            String target = line.substring(start, end + 2);

            target = target.replace(" ", "");

            //system.out.println("target:"+target);

            if (target.indexOf("text=") == -1) {

                return;
            }
            int textS = target.indexOf("text=") + 6;

            int textE  = target.indexOf(target.charAt(textS- 1), textS);
            String text = target.substring(textS, textE);
            translate.setText(text);
            //System.out.println("text:"+text);

            int codeStart = target.indexOf("code=") + 6;

            char codeSplit = target.charAt(codeStart- 1);
            int codeEnd = target.indexOf(codeSplit, codeStart);
            //System.out.println(target);

            String code = target.substring(codeStart, codeEnd);

            translate.setPreCode(code);

            boolean flag = true;
            if (code.isBlank()) {


                int nc = newLine.indexOf("code", start + 1);

                //newLine.char


                flag = false;
            }
            System.out.println("code:" + code);
            newLine = newLine.replace(code, "aass11111");
            System.out.println("newLine:" + newLine);

        }


    }

    @org.junit.Test
    public void b() throws IOException  {
        String pro = "E:\\文档\\20200911\\后端\\gjh\\java\\messages_zh_CN.properties";
        Properties translate =
                new Properties();
        InputStream is = new FileInputStream(pro);
        translate.load(is);
        Map<String, String> resMap = new HashMap<>();
        //translate.forEach( (k, v) -> resMap.put(v.toString(), k.toString()));

        System.out.println(translate);
    }

    @Test
    public void e()   {
        String s = "a2h3k4b6m4z5n6b7";
        int nums = 3;
    }

    public static void main(String[] args) {
        Testasd t = new Testasd();
        t.e();
    }




    @Test
    public void v()   {
        String a = "dd";

        byte[] bs = Base64.getEncoder().encode(a.getBytes());

        System.out.println(new String(bs));



    }



    @Test
    public void r() throws IOException  {

        String a = "LirnrKxcU3sxLDEwfeeroC4q";
        byte[] bs = Base64.getDecoder().decode(a.getBytes());
        String matche = new String(bs);
        String path = new String(Base64.getDecoder().decode("RTpc5paH5qGjXOivpuaDhS50eHQ=".getBytes()));
        File file = FileUtils.getFile(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));

        String line = null;

        List<String> texts = new ArrayList<>();
        List<List<String>> title = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.trim().isEmpty())
                continue;


            if (line.matches(matche)) {
                title.add(texts);
                texts = new ArrayList<>();
            }
            texts.add(line);
        }
        br.close();

        Scanner scanner = new Scanner(System.in);


        System.out.println("缓存成功");

        Timer timer = new Timer();

        BookMake bm = new BookMake();
        timer(bm, timer, title);


        while (true) {

            String input = scanner.nextLine();

            if (input.matches("\\s*")) {
                print(bm,title);
            }
            if (input.matches("[0-9]{1,4}")) {
                bm.titleAdd(Integer.valueOf(input));
                print(bm,title);
            }

            if (input.equals("p")) {
                timer.cancel();
                System.out.println("暂停");
            }
            if (input.equals("s")) {
                timer(bm, timer, title);
            }

            if (input.equals("exit")) {
                System.out.println(bm);
                break;
            }

        }
    }

    public void print(BookMake bm, List<List<String>> title) {
        List<String> outText = title.get(bm.title.get());
        if (bm.mark.get() >= outText.size()) {
            outText = title.get(bm.title.incrementAndGet());
            bm.mark.set(0);
        }
        String str = "";
        try {
            str = outText.get(bm.mark.getAndIncrement());
        }catch (Exception e) {
            str = outText.get(outText.size());
        }

        int nums = 10;
        while (true) {
            if (str.length() < nums) {
                System.out.println(str);
                break;
            }
            String d = str.substring(0,nums);
            str = str.substring(nums);
            System.out.println(d);
        }
    }

    public void timer(BookMake bm, Timer timer,List<List<String>> title) {
        timer.schedule(new TimerTask() {
            public void run() {
                print(bm,title);
            }
        }, 0, 10000);
    }

    @Test
    public void m() throws IOException  {
        AtomicInteger title = new AtomicInteger(0);
        System.out.println(title.get());
        System.out.println(title.incrementAndGet());
        System.out.println(title.incrementAndGet());
        System.out.println(title.incrementAndGet());
        System.out.println(title.getAndIncrement());
        System.out.println(title.getAndIncrement());
        title.set(0);
        System.out.println(title.get());
    }
}

