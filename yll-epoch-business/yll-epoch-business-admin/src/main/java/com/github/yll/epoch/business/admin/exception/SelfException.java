package com.github.yll.epoch.business.admin.exception;

/**
 * 自定义异常（通常继承RuntimeException）
 *
 * @author luliang_yu
 * @date 2018-11-21
 */

public class SelfException extends RuntimeException {

    public SelfException(String message, Throwable cause) {
        super(message, cause);
    }




}
