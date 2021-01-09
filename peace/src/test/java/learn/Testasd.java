package learn;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.apache.commons.text.StringSubstitutor;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
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

    @Test
    public  void AS() {
        AtomicBoolean run = new AtomicBoolean(false);

        System.out.println(run.getAndSet(true));
        System.out.println(run.get());
    }

    @Test
    public  void AD() {

        Date d = new Date();


        MessageDigest digest = null;
        if (digest == null)
        {
            try
            {
                digest = MessageDigest.getInstance("MD5");
                digest.update(("7425476795726110364" + d.toString()).getBytes("UTF-8"));
            }
            catch (Exception nsae)
            {
                System.err.println("Failed to load the MD5 MessageDigest. ");
            }
        }
        System.out.println(d.toString());
        System.out.println(byteToHex(digest.digest()));
    }

    public static String byteToHex(byte hash[])
    {
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;
        for (i = 0; i < hash.length; i++)
        {
            if ((hash[i] & 0xff) < 0x10)
            {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    @Test
    public  void AC() {
        System.out.println(System.currentTimeMillis());
    }


    @Test
    public  void AV() {
        Double d = 11.225d;
        Object m = d.toString();
        String str = (String) null;
        System.out.println(str);
        System.out.println(new BigDecimal(str));
    }


    @Test
    public  void AB() {
        String str = "    ";
        if( str == null || str.toString().isEmpty()) {
            System.out.println("true");
        } else {
            System.out.println(false);
        }

    }

    @Test
    public  void AN() {

        String data = "{'id':'1200235510200010120','cm_id':'CM0001','cm_name':'苏果夫子庙店'," +
                "'cm_code':'suguo_fzmd','cm_manager':'王伟,张强,吴为','cm_manager_id':'1200235510200010125,1200235510200010126'," +
                "'cm_mss_province':'江苏省','source_type': '来源线索','cm_mss_city':'南京市','cm_source':'来源线索','cm_trade':'服务业','cm_remarks':''," +
                "'linkmans':[{'cm_linkman_name':'张三','cm_linkman_mobile':'13052125555'}]}";
        String timestamp = System.currentTimeMillis()+ "";

        StringBuffer mdata = new StringBuffer();
        mdata.append(data).append("|").append("6d00HLJqLxMV0J4Scx").append("|").append(timestamp);
        String ndigest = ToolUtil.encodeMd5(mdata.toString());
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/innerapi/addTenantApi.do";
        MultiValueMap<String, String> var4 = new LinkedMultiValueMap<>();
        var4.add("msgId","1233112");
        var4.add("timestamp", timestamp);
        var4.add("digest",ndigest);
        var4.add("data", data);
        /*JSONObject map = restTemplate.postForEntity(url, var4, JSONObject.class).getBody();
        System.out.println(map);*/
    }

    @Test
    public  void AM() {
        String str = "12";
        System.out.println(str.matches("[1-2]"));

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    }
    @Test
    public void F(){
        try {
            try {
                List<String> list = new ArrayList<>();
                String s = null;
                list.add(s);
                list.get(0).split(",");
            } catch (Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
        } catch (Exception e) {
            //s.error(e.getMessage(),e);
        }
    }

    @Test
    public void FS(){
        Map<String, String> map = new HashMap<>();
        map.put("as", "asd");
        Map<String, String> ddd = new HashMap<>();
        ddd.putAll(map);
        ddd.put("222", "33333");
        System.out.println(map);
        System.out.println(ddd);
        System.out.println(Integer.toHexString(map.hashCode()));
        System.out.println(Integer.toHexString(ddd.hashCode()));
    }

    @Test
    public void FG(){
        String s = "'";
        System.out.println(s.replace("'", "\\'"));
    }

    @Test
    public void Fd(){

        String uuid = java.util.UUID.randomUUID().toString();
        System.out.println(uuid);
        System.out.println(uuid.replaceAll("-", ""));
    }

    @Test
    public void Ff(){

        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("animal", "quick brown fox");
        valuesMap.put("target", "lazy dog");
        String templateString = "The ${animal} jumped over the ${target}.";
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        String resolvedString = sub.replace(templateString);

        System.out.println(resolvedString);
    }

    @Test
    public void FJ(){
        Car c = new Car();
        //c.setManufacturer("asd");
        c.setSeatCount(1);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> ss = validator.validate(c, Car.Qublic.class);
        System.out.println(ss);
        for (ConstraintViolation<Object> con : ss) {
            System.out.println(con.getPropertyPath().toString() + con.getMessage());

        }

        //ValidatorImpl v = Validation.byProvider(HibernateValidator.class);
    }

    @Test
    public void FB(){

        //ServiceLoader<User>

        //ValidatorImpl v = Validation.byProvider(HibernateValidator.class);
    }



}

