package work.koreyoshi.project.videos.dto;

import lombok.*;

import java.util.List;

/**
 * @author zhoujx
 */
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoContentDTO {

    private List<ContentItem> items;

}
