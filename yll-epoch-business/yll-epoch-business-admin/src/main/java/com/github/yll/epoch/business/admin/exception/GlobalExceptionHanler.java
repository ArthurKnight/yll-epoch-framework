package com.github.yll.epoch.business.admin.exception;

import com.github.yll.epoch.core.commons.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通用异常捕获处理器
 *
 * @author luliang_yu
 * @date 2018-11-21
 */
@ControllerAdvice
public class GlobalExceptionHanler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHanler.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result javaExceptioHanler(Exception ex) {
        logger.error("捕获到Exception异常",ex);
        return Result.createErrorResult().setData(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = SelfException.class)
    public Result selfExceptioHanler(SelfException ex) {
        logger.error("捕获到SelfException异常",ex);
        return Result.createErrorResult().setData(ex.getMessage());
    }
}
