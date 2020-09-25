package learn;

import com.jiang.advances.peace.entity.TranslateResult;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Testasd {
    @Test
    public void a() throws IOException {

        String line = "            {field: 'name', name: '<spring:message code=\"sysapp.org.employee.name\" text=\"名称\"/>', type: 'text', placeholder: '<spring:message code=\"sysapp.org.employee.EnterNameToSearch\" />'},";
        String newLine = new String(line);
        int spaStart = 0;
        Map<String, String>  envrep = new HashMap<>();
        while (line.indexOf("spring:message", spaStart) != -1) {
            int start = line.indexOf("<spring:message", spaStart);
            int end = line.indexOf("/>", start);

            spaStart = end +1;
            if (end > -1) {
                String target = line.substring(start, end + 2);
                target = target.replace(" ", "");
                //system.out.println("target:"+target);
                if (target.indexOf("text=") == -1) {
                    System.out.println("fai--text--:" + line);
                    continue;
                }
                int textS = target.indexOf("text=") + 6;

                int textE  = target.indexOf(target.charAt(textS- 1), textS);
                String text = target.substring(textS, textE);

                System.out.println("text:"+text);

                int codeStart = target.indexOf("code=") + 6;

                char codeSplit = target.charAt(codeStart- 1);

                int codeEnd = target.indexOf(codeSplit, codeStart);

                String code = target.substring(codeStart, codeEnd);

                int nc = newLine.indexOf("code", start + 1);
//System.out.println(target);
                if (nc == -1) {
                    System.out.println(newLine.substring(start));
                    System.out.println("fai-- code--:" + line);
                    continue;
                }
                int split1 = newLine.indexOf(codeSplit,nc);
                int split2 = newLine.indexOf(codeSplit,split1+1);

                String p = newLine.substring(nc,split2 + 1);

                envrep.put(p, "code = " + codeSplit + "******" + codeSplit);

            } else {

                System.out.println("fai--end--:" + line);
                break;
            }

        }
        for (Map.Entry<String, String> e : envrep.entrySet()) {
            newLine = newLine.replace(e.getKey(), e.getValue());
        }
        System.out.println("result:" + newLine);

    }

    @Test
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

/*


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
*/

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

    @Test
    public  void checkChinese() {
        String sequence = "   2340一3j撒ldjgf二2对阿斯顿ff";
        final String format = "[\u4e00-\u9fa5]+";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(sequence);


        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println("成功");
        }

    }
}

