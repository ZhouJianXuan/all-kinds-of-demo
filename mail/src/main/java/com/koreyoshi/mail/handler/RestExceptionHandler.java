package com.koreyoshi.mail.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import work.koreyoshi.base.exception.handler.BaseExceptionHandler;

/**
 * @author zhoujx
 */
@RestControllerAdvice(annotations = RestController.class)
@Slf4j
@ResponseBody
public class RestExceptionHandler extends BaseExceptionHandler {

}
