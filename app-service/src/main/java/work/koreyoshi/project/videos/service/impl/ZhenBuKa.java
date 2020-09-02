package work.koreyoshi.project.videos.service.impl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import work.koreyoshi.project.videos.dto.ContentItem;
import work.koreyoshi.project.videos.dto.VideoContentDTO;
import work.koreyoshi.project.videos.dto.VideoSearchDTO;
import work.koreyoshi.project.videos.service.VideorReptileService;
import work.koreyoshi.project.videos.utils.JsoupUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoujx
 */
public class ZhenBuKa implements VideorReptileService {

    private final static String BASE_PATH = "https://www.zhenbuka.com";

    @Override
    public VideoSearchDTO search(String url) {
        Document document = JsoupUtil.get(url);
        Element element = document.selectFirst(".stui-pannel_bd.clearfix");
        Element link = element.selectFirst(".v-thumb.stui-vodlist__thumb.lazyload");
        return VideoSearchDTO.builder()
                .name(link.attr("title"))
                .url(link.attr("href"))
                .imgUrl(link.attr("data-original"))
                .isEnd(isEnd(link.text())).build();
    }

    @Override
    public VideoContentDTO content(String url) {
        Document document = JsoupUtil.get(BASE_PATH + url);
        Element element = document.selectFirst(".stui-content__playlist.clearfix");
        Elements children = element.children();
        List<ContentItem> list = new ArrayList<>(children.size());
        for (Element child : children) {
            list.add(ContentItem.builder()
                    .name(child.text())
                    .url(child.selectFirst("a").attr("href"))
                    .build());
        }
        return VideoContentDTO.builder().items(list).build();
    }


    public static void main(String[] args) {
        String url = BASE_PATH + "/vodsearch/-------------/?wd=%E7%90%89%E7%92%83&submit=";
        ZhenBuKa zhenBuKa = new ZhenBuKa();
        VideoSearchDTO search = zhenBuKa.search(url);
        System.out.println(search.toString());
        Document document = JsoupUtil.get(BASE_PATH + search.getUrl());
        Element element = document.selectFirst(".stui-content__playlist.clearfix");
        Elements children = element.children();
        for (Element child : children) {
            System.out.println(child.selectFirst("a").attr("href"));
            System.out.println(child.attr("href") + child.text());
        }
    }
}
