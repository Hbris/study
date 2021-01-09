package learn;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jjw
 * @Company: waiqin365
 * @Description:
 * @create 2020-12-21 16:49
 */
@ToString
@Getter
public enum Language {
    zh_CN("zh_CN", "中文(简体)"),
    zh_TW("zh_TW", "中文(繁體)"),
    en_US("en_US", "English(USA)"),
    ja_JP("ja_JP", "日本語"), // 日语
    ko_KR("ko_KR", "한국어."), // 韩语
    vi("vi", "Tiếng Việt"), // 越南语
    in("in", "Bahasa Indonesia"), // 印尼语
    ms("ms", "Bahasa Melayu"), // 马来语
    th("th", "ภาษาไทย") // 泰国语
    ;
    private String langCode ;
    private String langName ;

    Language(String langCode, String langName) {
        this.langCode = langCode;
        this.langName = langName;
    }

    static List<String> getKeys(){
        return Arrays.stream(Language.values()).map(Language::getLangCode).collect(Collectors.toList());

    }

    public static void main(String[] args) {
        System.out.println(getKeys());
    }
}
