package work.koreyoshi.controller;

import lombok.Builder;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import work.koreyoshi.base.result.RestResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoujx
 */
@RestController
public class JsoupController {

    private final static String BASE_PATH = "https://www.nfmovies.com";

    @GetMapping("/videos")
    public RestResult search(String name) {
        String url = BASE_PATH + "/search.php?page=1&searchword=" + name;
        Document document = get(url);
        Elements elements = document.select("li.active.clearfix");
        List<Node> nodes = new ArrayList<>();
        for (Element element : elements) {
            Element title = element.selectFirst(".title");
            Elements content = element.select("p");
            List<String> contents = new ArrayList<>(content.size());
            System.out.println(title.text() + "=>" + BASE_PATH + title.selectFirst("a").attr("href"));
            content.forEach(c -> contents.add(c.text()));
            nodes.add(Node.builder()
                    .title(title.text())
                    .url(BASE_PATH + title.selectFirst("a").attr("href"))
                    .content(contents)
                    .build());
        }
        return RestResult.ok(nodes);
    }

    public static Document get(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .timeout(10000)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}

@Data
@Builder
class Node{
    private String title;

    private String url;

    private List<String> content;
}
