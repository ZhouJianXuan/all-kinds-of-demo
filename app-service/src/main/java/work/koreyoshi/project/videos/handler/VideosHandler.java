package work.koreyoshi.project.videos.handler;

import cn.hutool.extra.mail.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import work.koreyoshi.project.common.model.Videos;
import work.koreyoshi.base.annotation.SendMail;
import work.koreyoshi.project.videos.constant.VideosUrl;
import work.koreyoshi.project.videos.utils.JsoupUtil;
import work.koreyoshi.project.videos.VideosService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author zhoujx
 */
@Slf4j
@Component
public class VideosHandler {

    private final Map<String, Consumer<Videos>> initMap = new HashMap<String, Consumer<Videos>>(16);

    @Resource
    private VideosService videosService;

    @PostConstruct
    private void init() {
        initMap.put("naiFei_update", naiFeiUpdate);
    }

    public void apply(Videos videos, String behavior) {
        if (!videos.getIsEnd()) {
            log.info("执行任务{}：{}", behavior, videos.getName());
            Consumer<Videos> objectConsumer = initMap.get(videos.getBelongTo() + "_" + behavior);
            objectConsumer.accept(videos);
        } else {
            log.info("{}已完结", videos.getName());
        }
    }

    private final Consumer<Videos> naiFeiUpdate = v -> {
        if (StringUtils.isEmpty(v.getAddress())) {
            String address = searchVideos(v.getName());
            if (address == null) {
                log.info("搜索失败");
                return;
            }
            v.setAddress(address);
            videosService.update(v);
        }
        Document document = JsoupUtil.get(v.getAddress());
        if (document == null) {
            return;
        }
        if ("完结".equals(document.selectFirst("h1.title.text-fff").selectFirst("font").text())) {
            log.info("已完结");
            v.setIsEnd(true);
        }
        Element element = document.selectFirst("#playlist3");
        Elements li = element.select("li");
        if (v.getCurrent() < li.size()) {
            Element target = li.get(li.size() - 1);
            String title = target.selectFirst("a").attr("title");
            String url = target.selectFirst("a").attr("href");
            v.setCurrent(li.size());
            MailUtil.sendText(v.getTos(), v.getName()+ "更新" + title, VideosUrl.BASE_NAIFEI_PATH + url);
        }
        videosService.update(v);
    };

    private String searchVideos(String name) {
        String url = VideosUrl.BASE_NAIFEI_PATH + VideosUrl.NAIFEI_SEARCH + name;
        Document document = JsoupUtil.get(url);

        Elements elements = document.select("li.active.clearfix");
        if (elements.hasText()) {
            Element element = elements.get(0);
            Element title = element.selectFirst(".title");
            return VideosUrl.BASE_NAIFEI_PATH + title.selectFirst("a").attr("href");
        }
        return null;
    }

    @SendMail(msg = "#0", title = "#1", tos = "#2")
    public void sendUpdateEmail(String msg, String title, String tos) {
        log.info("发送Email，msg:{}, title:{}, tos:{}", msg, title, tos);
    }
}
