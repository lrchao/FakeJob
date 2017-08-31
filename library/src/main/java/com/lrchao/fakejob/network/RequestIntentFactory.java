package com.lrchao.fakejob.network;

import android.content.Intent;

import com.lrchao.fakejob.constant.BundleKey;

import static com.lrchao.fakejob.network.request.RequestActionKey.ACTION_REGISTER;
import static com.lrchao.fakejob.network.request.RequestActionKey.ACTION_LOGIN;
import static com.lrchao.fakejob.network.request.RequestActionKey.ACTION_GET_JOB_JOIN;
import static com.lrchao.fakejob.network.request.RequestActionKey.ACTION_GET_JOB_CATEGORY_LIST;
import static com.lrchao.fakejob.network.request.RequestActionKey.ACTION_GET_HOME;
import static com.lrchao.fakejob.network.request.RequestActionKey.ACTION_GET_JOB_DETAILS;
import static com.lrchao.fakejob.network.request.RequestActionKey.ACTION_GET_PART_TIME_JOB;
import static com.lrchao.fakejob.service.RequestService.newStartIntent;

/**
 * Description: 请求发起管理类
 * 同意的请求发起
 *
 * @author liuranchao
 * @date 16/3/2 上午9:42
 */
public final class RequestIntentFactory {

    private RequestIntentFactory() {

    }


    private static Intent getRequestIntent(int action) {
        return newStartIntent(action);
    }

    /**
     * 获取师徒数据
     */
    public static Intent getHome() {
        return getRequestIntent(ACTION_GET_HOME);
    }

    public static Intent getPartTimeJob() {
        return getRequestIntent(ACTION_GET_PART_TIME_JOB);
    }

    public static Intent getJobCategoryList(int id) {
        Intent intent = getRequestIntent(ACTION_GET_JOB_CATEGORY_LIST);
        intent.putExtra(BundleKey.INTENT_EXTRA_CATEGORY_ID, id);
        return intent;
    }

    public static Intent getJobDetails(int id) {
        Intent intent = getRequestIntent(ACTION_GET_JOB_DETAILS);
        intent.putExtra(BundleKey.INTENT_EXTRA_JOB_ID, id);
        return intent;
    }

        public static Intent getJobJoin(int id) {
        Intent intent = getRequestIntent(ACTION_GET_JOB_JOIN);
        intent.putExtra(BundleKey.INTENT_EXTRA_JOB_ID, id);
        return intent;
    }

    public static Intent getLogin(String userName, String password) {
        Intent intent = getRequestIntent(ACTION_LOGIN);
        intent.putExtra(BundleKey.INTENT_EXTRA_LOGIN_USERNAME, userName);
        intent.putExtra(BundleKey.INTENT_EXTRA_LOGIN_PASSWORD, password);
        return intent;
    }

    public static Intent getRegister(String phoneNumber, String password) {
        Intent intent = getRequestIntent(ACTION_REGISTER);
        intent.putExtra(BundleKey.INTENT_EXTRA_PHONE_NUMBER, phoneNumber);
        intent.putExtra(BundleKey.INTENT_EXTRA_LOGIN_PASSWORD, password);
        return intent;
    }


}
