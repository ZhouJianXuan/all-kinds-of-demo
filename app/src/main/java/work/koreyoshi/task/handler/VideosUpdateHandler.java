package work.koreyoshi.task.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.event.Subscribe;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import work.koreyoshi.constant.VideosUrl;
import work.koreyoshi.project.common.model.Videos;
import work.koreyoshi.project.common.model.base.BaseVideos;
import work.koreyoshi.project.videos.VideosService;
import work.koreyoshi.task.SendEmailTask;
import work.koreyoshi.utils.JsoupUtil;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

/**
 * @author zhoujx
 */
@Component
@Slf4j
public class VideosUpdateHandler {

    @Autowired
    private VideosService videosService;
    @Autowired
    private SendEmailTask sendEmailTask;

    @Async
    public void searchVideoUpdate() {
        List<Videos> videos = videosService.findAll();
        log.info("需要更新的videos:{}", videos);
        videos.stream().filter(Videos::getSubscribe).forEach(
                v -> {
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
                    Element element = document.selectFirst("#playlist3");
                    Elements li = element.select("li");
                    if (v.getCurrent() < li.size()) {
                        Element target = li.get(li.size() - 1);
                        String title = target.selectFirst("a").attr("title");
                        String url = target.selectFirst("a").attr("href");
                        v.setCurrent(li.size());
                        sendEmailTask.sendUpdateEmail(VideosUrl.BASE_PATH + url, v.getName()  + "更新" + title , "1104135769@qq.com");
                        videosService.update(v);
                    }
                }
        );
    }

    private String searchVideos(String name) {
        String url = VideosUrl.BASE_PATH + VideosUrl.SEARCH + name;
        Document document = JsoupUtil.get(url);

        Elements elements = document.select("li.active.clearfix");
        if (elements.hasText()) {
            Element element = elements.get(0);
            Element title = element.selectFirst(".title");
            return VideosUrl.BASE_PATH + title.selectFirst("a").attr("href");
        }
        return null;
    }

    public static void main(String[] args) {
        Document document = JsoupUtil.get("https://www.nfmovies.com/detail/?54310.html");
        Element element = document.selectFirst("#playlist3");
        System.out.println(element);
        Elements l1 = element.select("li");
        System.out.println(l1.get(l1.size() - 1).selectFirst("a").attr("href"));
    }
}
