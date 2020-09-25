package pachong;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Calendar;

public class Asd22 {


    @Test
    public void a() throws Exception {
        String url = "http://jandan.net/pic";
        HttpClient hc = HttpClient.newHttpClient();
        HttpRequest.Builder build = HttpRequest.newBuilder(URI.create(url)).GET();
        build.header("Accept", "text/html,application/xhtml+xml," +
                "application/xml;q=0.9,image/avif,image/webp," +
                "image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        build.header("Accept-Encoding", "deflate");
        build.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0;Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        HttpRequest hr = build.build();
        //HttpResponse.BodyHandler body = HttpResponse.BodyHandlers.ofString(Charset.forName("UTF-8"));
        HttpResponse.BodyHandler body = HttpResponse.BodyHandlers.ofString(Charset.forName("UTF-8"));
        HttpResponse<String>  hp = hc.send(hr,  body);


        //HttpResponse.BodySubscriber<String> s = body.apply(hp);
        String s = hp.body();
        System.out.println(s);
        System.out.println(hp.headers().map());
    }
}
