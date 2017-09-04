package com.lrchao.fakejob.constant;


import com.lrchao.fakejob.manager.shared_preference.PreferenceOperator;

import java.util.HashSet;

/**
 * Description: SharedPreference的key声明类
 *
 * @author liuranchao
 * @date 16/3/29 上午9:56
 */
public enum SharedPreferenceKey {

    //==========================================================================
    //   Common
    //==========================================================================

    PREF_CURRENT_LOCATION("pref.current.location", String.class, "北京"),


    /**
     * ACCESS TOKEN
     */
    PREF_ACCESS_TOKEN("pref.access.token", String.class),


    /**
     * 当前登陆的用户ID
     */
    PREF_USER_ID("pref.user.id", Integer.class),
    PREF_USER_PHONE_NUMBER("pref.user.phone.number", String.class),

    /**
     * 是否为首次启动（包括第一次安装或者更新版本）
     */
    PREF_IS_FIRST_LAUNCH("pref.is.first.launch", Boolean.class),

    /**
     * 是否为首次安装
     */
    PREF_IS_FIRST_INSTALL("pref.is.first.install", Boolean.class),


    /**
     * 当前的version code
     */
    PREF_CURRENT_VERSION_CODE("pref.current.version.code", Integer.class),

    /**
     * 是否已经登陆 boolean
     */
    PREF_IS_LOGIN("pref.is.login", Boolean.class),


    PREF_BALANCE("pref.balance", Integer.class),

    /**
     * 邀请码
     */
    PREF_INVITATION_CODE("pref.invitation.code", String.class),

    /**
     * 配置URL
     */
    PREF_LOTTERY_URL("pref.lottery.url", String.class), // 每日抽奖

    PREF_RULE_URL("pref.rule.url", String.class), // 规则说明

    PREF_FAQ_URL("pref.faq.url", String.class), // 帮助说明

    PREF_ASO_TASK_NEED_REFRESH("pref.aso.task.need.refresh", Boolean.class), // 帮助说明

    /**
     * 分享
     */
    PREF_SHARE_URL("pref.share.url", String.class),
    PREF_SHARE_TITLE("pref.share.title", String.class),
    PREF_SHARE_DESC("pref.share.desc", String.class),
    PREF_SHARE_IMG("pref.share.img", String.class),


    //==========================================================================
    //   Account
    //==========================================================================


    //=====================================================================
    // Fake
    //=====================================================================

    /**
     * 是否已经登陆 boolean
     */
    PREF_FAKE_IS_LOGIN("pref.fake.is.login", Boolean.class),

    PREF_FAKE_LOGIN_USER_NAME("pref.fake.login.user.name", String.class),

    /**
     * 发现红点的检查
     */
    PREF_DISCOVER_RED_CHECK("pref.discover.requirements.red.check", Boolean.class);


    //================================================================================================

    /**
     * 字符串的key
     */
    private String mKey;

    /**
     * 默认值
     */
    private Object mDefaultValue;

    /**
     * 对应的类型
     */
    private Class mClazz;

    SharedPreferenceKey(String key, Class typeClazz, Object defaultValue) {
        mKey = key;
        mClazz = typeClazz;
        mDefaultValue = defaultValue;
    }

    SharedPreferenceKey(String key, Class typeClazz) {
        mKey = key;
        mClazz = typeClazz;
    }

    public String getKey() {
        return mKey;
    }

    public Class getClazz() {
        return mClazz;
    }

    /**
     * 获取默认值
     */
    public Object getDefaultValue() {

        if (mDefaultValue != null) {
            return mDefaultValue;
        }

        if (getClazz() == Boolean.class) {
            mDefaultValue = PreferenceOperator.DEFAULT_BOOLEAN;

        } else if (getClazz() == Integer.class) {
            mDefaultValue = PreferenceOperator.DEFAULT_INTEGER;

        } else if (getClazz() == String.class) {
            mDefaultValue = PreferenceOperator.DEFAULT_STRING;

        } else if (getClazz() == Long.class) {
            mDefaultValue = PreferenceOperator.DEFAULT_LONG;

        } else if (getClazz() == Float.class) {
            mDefaultValue = PreferenceOperator.DEFAULT_FLOAT;

        } else if (getClazz() == HashSet.class) {
            mDefaultValue = PreferenceOperator.DEFAULT_SET;
        }

        return mDefaultValue;
    }

}
