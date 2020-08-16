package work.koreyoshi.utils;

import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author zhoujx
 */
public class JsoupUtil {

    public static Document get(String url) throws IOException{
        Document document = org.jsoup.Jsoup.connect(url)
                    .timeout(20000)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                    .get();
        return document;
    }


}
