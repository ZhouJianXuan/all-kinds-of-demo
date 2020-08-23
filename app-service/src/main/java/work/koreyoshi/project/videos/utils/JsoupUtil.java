package work.koreyoshi.project.videos.utils;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author zhoujx
 */
@Slf4j
public class JsoupUtil {

    public static Document get(String url) {
        Document document = null;
        int retry = 3;
        do {
            try {
                document = org.jsoup.Jsoup.connect(url)
                        .timeout(20000)
                        .ignoreContentType(true)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                        .get();
            } catch (IOException e) {
                log.info("超时重试");
            } finally {
                retry--;
            }
        } while (document == null || retry > 0);
        return document;
    }


}
