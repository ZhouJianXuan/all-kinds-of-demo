package work.koreyoshi.project.videos.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author zhoujx
 */
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentItem {

    private String name;

    private String url;

}
