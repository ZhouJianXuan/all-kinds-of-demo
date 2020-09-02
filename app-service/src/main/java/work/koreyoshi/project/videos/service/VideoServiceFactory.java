package work.koreyoshi.project.videos.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VideoServiceFactory implements ApplicationContextAware {
 
    private static final Map<String, VideorReptileService> EAT_LUNCH_MAP = new ConcurrentHashMap<String, VideorReptileService>();
 
    public static VideorReptileService getLunch(String week) {
        return EAT_LUNCH_MAP.get(week);
    }
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, VideorReptileService> map = applicationContext.getBeansOfType(VideorReptileService.class);
        map.forEach(EAT_LUNCH_MAP::put);
    }
}