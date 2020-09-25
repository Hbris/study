package pachong;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AppMain {
    static String  path = "E:\\c\\新建文件夹\\1.gif";
    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "http://wx3.sinaimg.cn/large/006UaUuJly1gj2q3659thg30a007i1l1.gif";
        HttpClient hc = HttpClient.newHttpClient();
        File f = new File(path);
        HttpRequest hr = HttpRequest.newBuilder(URI.create(url)).GET().build();
        HttpResponse.BodyHandler body = HttpResponse.BodyHandlers.ofFile(f.toPath());
        HttpResponse  hp = hc.send(hr,  body);

        if (hp.statusCode() != 200 && f.exists()) {
            f.delete();
        }
        System.out.println(hp.headers().map());
    }
}
