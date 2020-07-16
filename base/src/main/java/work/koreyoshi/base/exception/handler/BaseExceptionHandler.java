package work.koreyoshi.base.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import work.koreyoshi.base.exception.BaseCustomException;
import work.koreyoshi.base.result.RestResult;

/**
 * @author zhoujx
 */
@Slf4j
public abstract class BaseExceptionHandler {

    @ExceptionHandler(value = BaseCustomException.class)
    public RestResult customExceptionHandler(BaseCustomException e) {
        log.info("自定义异常处理: {}", e.getMessage());
        return RestResult.error(e);
    }

}