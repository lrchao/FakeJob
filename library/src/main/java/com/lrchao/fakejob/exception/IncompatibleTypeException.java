package com.lrchao.fakejob.exception;

/**
 * Description: 类型不匹配异常
 *
 * @author liuranchao
 * @date 16/3/15 下午3:41
 */
public class IncompatibleTypeException extends RuntimeException {

    public IncompatibleTypeException(String detailMessage) {
        super(detailMessage);
    }
}
