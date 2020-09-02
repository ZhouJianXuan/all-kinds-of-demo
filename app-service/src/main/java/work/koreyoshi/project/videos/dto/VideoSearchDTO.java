package work.koreyoshi.project.videos.dto;

import lombok.*;

/**
 * @author zhoujx
 */
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoSearchDTO {

    private String name;

    private String url;

    private Boolean isEnd;

    private String imgUrl;
}
