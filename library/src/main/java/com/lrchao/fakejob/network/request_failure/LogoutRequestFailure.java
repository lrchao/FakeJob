package com.lrchao.fakejob.network.request_failure;


import com.lrchao.fakejob.R;
import com.lrchao.utils.ResourceUtils;

/**
 * Description: 登出的错误
 *
 * @author liuranchao
 * @date 16/3/30 下午4:24
 */
public class LogoutRequestFailure extends RequestFailure {

    @Override
    public String getFriendlyMsg() {
        return ResourceUtils.getString(R.string.job_error_logout);
    }
}
