package work.koreyoshi.task;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import work.koreyoshi.project.videos.VideosService;
import work.koreyoshi.project.videos.constant.Behavior;
import work.koreyoshi.project.videos.handler.VideosHandler;
import work.koreyoshi.task.handler.VideosUpdateHandler;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * @author zhoujx
 */
@Component
@Slf4j
public class InspectionUpdateTask {

    @Resource
    private VideosService videosService;
    @Resource
    private VideosHandler videosHandler;

    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNamePrefix("videos-pool").build();
    ExecutorService singleThreadPool = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    @Scheduled(cron = "0 0/1 * * * ?")
    public void task() {
        videosService.findAll().forEach(
                v -> singleThreadPool.submit(
                        () -> videosHandler.apply(v, Behavior.update.getBehavior()))
        );
    }

}
