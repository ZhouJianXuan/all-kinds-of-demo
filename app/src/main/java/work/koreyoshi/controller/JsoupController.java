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
import work.koreyoshi.entity.Node;
import work.koreyoshi.utils.JsoupUtil;

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
    public RestResult search(String name) throws IOException {
        String url = BASE_PATH + "/search.php?page=1&searchword=" + name;
        Document document = JsoupUtil.get(url);
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
}
