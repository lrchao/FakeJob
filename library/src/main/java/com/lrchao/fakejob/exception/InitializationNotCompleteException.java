package com.lrchao.fakejob.exception;

/**
 * Description: 初始化 没有完成异常
 * 指 某些值 在初始化时 没有设置
 *
 * @author liuranchao
 * @date 16/1/8 上午10:11
 */
public class InitializationNotCompleteException extends RuntimeException {

    public InitializationNotCompleteException(String detailMessage) {
        super(detailMessage);
    }
}
