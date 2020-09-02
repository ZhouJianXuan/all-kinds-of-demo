package work.koreyoshi.project.videos.service;

import work.koreyoshi.project.videos.dto.VideoContentDTO;
import work.koreyoshi.project.videos.dto.VideoSearchDTO;

public interface VideorReptileService {

    default boolean isEnd(String str) {
        return str.contains("全") || str.contains("完结");
    }

    VideoSearchDTO search(String url);

    VideoContentDTO content(String url);

}
