package com.lrchao.fakejob.constant;

/**
 * Description: 定义Intent 传递值的常量key or value
 *
 * @author liuranchao
 * @date 16/3/15 下午2:40
 */
public final class BundleKey {

    //============================================
    // Intent Extra
    //============================================

    /**
     * 通用的
     */
    public static final String INTENT_EXTRA_TOOLBAR_TITLE = "intent.extra.TOOLBAR_TITLE";
    public static final String INTENT_EXTRA_WEBVIEW_URL = "intent.extra.WEBVIEW_URL";
    public static final String INTENT_EXTRA_WEBVIEW_TITLE = "intent.extra.WEBVIEW_TITLE";


    /**
     * 请求的类型
     */
    public static final String INTENT_EXTRA_REQUEST_ACTION = "intent.extra.REQUEST_ACTION";
    public static final String INTENT_EXTRA_REQUEST_START = "intent.extra.REQUEST_START"; // 分页的标示

    /**
     * 登陆相关
     */
    public static final String INTENT_EXTRA_PHONE_NUMBER = "intent.extra.PHONE_NUMBER"; //电话号
    public static final String INTENT_EXTRA_PWD = "intent.extra.PWD"; //密码

    /**
     * Dialog
     */
    public static final String INTENT_EXTRA_DIALOG_TITLE = "intent.extra.DIALOG_TITLE"; //Dialog的title
    public static final String INTENT_EXTRA_DIALOG_MESSAGE = "intent.extra.DIALOG_MESSAGE"; //Dialog的message
    public static final String INTENT_EXTRA_DIALOG_PROGRESS = "intent.extra.DIALOG_PROGRESS"; //Dialog的进度
    public static final String INTENT_EXTRA_DIALOG_ICON = "intent.extra.DIALOG_ICON"; //Dialog的icon
    public static final String INTENT_EXTRA_DIALOG_CONFIRM_BTN = "intent.extra.DIALOG_CONFIRM_BTN"; //Dialog的确认 btn
    public static final String INTENT_EXTRA_DIALOG_CANCEL_BTN = "intent.extra.DIALOG_CANCEL_BTN"; //Dialog的取消 btn
    public static final String INTENT_EXTRA_DIALOG_DIALOG_IS_CANCELABLE = "intent.extra.DIALOG_IS_CANCELABLE"; //Dialog的是否可以cancel
    /**
     * Main Activity
     */
    public static final String INTENT_EXTRA_MAIN_TAB_INDEX = "intent.extra.MAIN_TAB_INDEX"; // main tab index

    public static final String INTENT_EXTRA_CATEGORY_ID = "intent.extra.CATEGORY_ID";

    public static final String INTENT_EXTRA_JOB_ID = "intent.extra.JOB_ID";

    /**
     * 任务
     */
    public static final String INTENT_EXTRA_TASK_ID = "intent.extra.TASK_ID"; // 任务的ID
    public static final String INTENT_EXTRA_TASK_ACTION = "intent.extra.TASK_ACTION"; // 任务的ACTION

    /**
     * 提现相关
     */
    public static final String INTENT_EXTRA_WITHDRAW_ACCOUNT = "intent.extra.WITHDRAW_ACCOUNT"; // 提现账号
    public static final String INTENT_EXTRA_WITHDRAW_REAL_NAME = "intent.extra.WITHDRAW_REAL_NAME"; // 提现收款人姓名
    public static final String INTENT_EXTRA_WITHDRAW_MONEY = "intent.extra.WITHDRAW_MONEY"; // 提现金额
    public static final String INTENT_EXTRA_WITHDRAW_TYPE = "intent.extra.WITHDRAW_TYPE"; // 提现类型

    /**
     * 密码
     */
    public static final String INTENT_EXTRA_PASSWORD_OLD = "intent.extra.PASSWORD_OLD"; //
    public static final String INTENT_EXTRA_PASSWORD_NEW = "intent.extra.PASSWORD_NEW"; //

    /**
     * 邀请相关
     */
    public static final String INTENT_EXTRA_INVITATION_CODE = "intent.extra.INVITATION_CODE"; // 邀请码

    /**
     * Fake
     */
    public static final String INTENT_EXTRA_LOGIN_USERNAME = "intent.extra.LOGIN_USERNAME";
    public static final String INTENT_EXTRA_LOGIN_PASSWORD = "intent.extra.LOGIN_PASSWORD";


    //============================================
    // Intent Action
    //============================================


    /**
     *
     */
    private BundleKey() {

    }

}
