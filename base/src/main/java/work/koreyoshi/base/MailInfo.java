package work.koreyoshi.base;

import lombok.Data;

import java.util.List;

/**
 * @author zhoujx
 */
@Data
public class MailInfo {

    private List<String> list;

    private String subject;

    private String content;
}
