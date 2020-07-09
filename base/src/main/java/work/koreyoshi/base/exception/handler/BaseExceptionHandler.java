package work.koreyoshi.base.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import work.koreyoshi.base.exception.BaseCustomException;
import work.koreyoshi.base.result.RestResult;

@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public abstract class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public RestResult exceptionHandler(Exception e) {
        log.info("异常处理");
        return RestResult.error();
    }

    @ExceptionHandler(value = BaseCustomException.class)
    public RestResult customExceptionHandler(BaseCustomException e) {
        log.info("自定义异常处理");
        return RestResult.error(e);
    }
}