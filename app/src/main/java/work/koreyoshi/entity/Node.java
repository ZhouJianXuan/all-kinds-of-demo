package work.koreyoshi.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author zhoujx
 */
@Data
@Builder
public class Node{
    private String title;

    private String url;

    private List<String> content;
}