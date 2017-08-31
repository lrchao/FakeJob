package com.lrchao.fakejob.exception;

/**
 * Description: 参数非法异常
 *
 * @author liuranchao
 * @date 15/12/21 下午2:52
 */
public class IllegalParamException extends RuntimeException {

    public IllegalParamException(String detailMessage) {
        super(detailMessage);
    }


}
