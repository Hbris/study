package learn;

/**
 * @author ceshi
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/1414:40
 */
public class Maissss {

    public static void main(String[] args) {
        ThreadLocal<String> t = new ThreadLocal<>();
        t.set("1aaa");
        jsad();
    }

    private static void jsad(){
        ThreadLocal<String> t = new ThreadLocal<>();
        System.out.println(t.get());
    }
}
