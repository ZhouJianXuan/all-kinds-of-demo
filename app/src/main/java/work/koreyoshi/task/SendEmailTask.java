package work.koreyoshi.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import work.koreyoshi.project.mail.annotation.SendMail;

/**
 * @author zhoujx
 */
@Slf4j
@Component
public class SendEmailTask {

    @SendMail(msg = "#0", title = "#1", tos = "#2")
    public void sendUpdateEmail(String msg, String title, String tos) {
        log.info("发送Email，msg:{}, title:{}, tos:{}", msg, title, tos);
    }

}
