package work.koreyoshi.task;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import work.koreyoshi.task.handler.VideosUpdateHandler;

import java.util.concurrent.*;

/**
 * @author zhoujx
 */
@Component
@Slf4j
public class InspectionUpdateTask {

    @Autowired
    private VideosUpdateHandler videosUpdateHandler;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void task() {
        log.info("开始更新");
        videosUpdateHandler.searchVideoUpdate();
    }

}
