package com.lrchao.fakejob.manager.shared_preference;

import android.content.SharedPreferences;

import com.lrchao.utils.LogUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Description: SharedPreference操作帮助类
 *
 * @author liuranchao
 * @date 15/11/12 下午3:58
 */
public class PreferenceOperator {

    /**
     * 默认值, 正常的情况 和 异常的情况
     */
    public static final String DEFAULT_STRING = "";
    public static final boolean DEFAULT_BOOLEAN = false;
    public static final int DEFAULT_INTEGER = 0;
    public static final long DEFAULT_LONG = 0L;
    public static final float DEFAULT_FLOAT = 0f;
    public static final HashSet DEFAULT_SET = null;
    private static final String TAG = "PreferenceOperator";
    /**
     * 传入不同的SharedPreferences对象
     */
    private SharedPreferences mSharedPreferences;

    private SharedPreferences.Editor mEditor;

    public PreferenceOperator(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
        mEditor = mSharedPreferences.edit();
    }

    /**
     * 保存数据
     *
     * @param key   sharedPreference key
     * @param value Obj
     */
    public void setValue(String key, Object value) {
        if (value instanceof Boolean) {

            mEditor.putBoolean(key, (Boolean) value);

        } else if (value instanceof Integer) {

            mEditor.putInt(key, (Integer) value);

        } else if (value instanceof String) {

            mEditor.putString(key, (String) value);

        } else if (value instanceof Long) {

            mEditor.putLong(key, (Long) value);

        } else if (value instanceof Float) {

            mEditor.putFloat(key, (Float) value);

        } else if (value instanceof HashSet<?>) {

            mEditor.putStringSet(key, (HashSet<String>) value);
        }
        boolean result = commit();
        LogUtils.d(TAG, "setValue: key=" + key + " value=" + value + " result=" + result);

    }

    /**
     * @param c   Class 类型
     * @param key key
     * @param <T> 类型
     * @return value
     * @throws ClassCastException
     */
    public <T> T getValue(Class<T> c, String key, Object defaultValue) throws ClassCastException {
        T t = null;
        if (c == String.class) {
            t = (T) mSharedPreferences.getString(key, (String) defaultValue);
        } else if (c == Boolean.class) {
            Boolean temp = mSharedPreferences.getBoolean(key, (boolean) defaultValue);
            t = (T) temp;
        } else if (c == Integer.class) {
            Integer temp = mSharedPreferences.getInt(key, (int) defaultValue);
            t = (T) temp;
        } else if (c == Long.class) {
            Long temp = mSharedPreferences.getLong(key, (long) defaultValue);
            t = (T) temp;
        } else if (c == Float.class) {
            Float temp = mSharedPreferences.getFloat(key, (float) defaultValue);
            t = (T) temp;
        } else if (c == HashSet.class) {
            HashSet temp = (HashSet) mSharedPreferences.getStringSet(key, (HashSet) defaultValue);
            t = (T) temp;
        }
        return t;
    }

    /**
     * 首选项设置生效
     */
    private boolean commit() {
        return mEditor.commit();
    }

    /**
     * 清空所有缓存
     * 1.设置所有缓存为默认值，为了让监听器相应
     * 2.清空key-value
     */
    public void clear() {

        HashMap<String, Object> map = (HashMap<String, Object>) mSharedPreferences.getAll();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof Boolean) {
                    setValue(key, DEFAULT_BOOLEAN);
                } else if (value instanceof Integer) {
                    setValue(key, DEFAULT_INTEGER);
                } else if (value instanceof String) {
                    setValue(key, DEFAULT_STRING);
                } else if (value instanceof Long) {
                    setValue(key, DEFAULT_LONG);
                } else if (value instanceof Float) {
                    setValue(key, DEFAULT_FLOAT);
                } else if (value instanceof HashSet<?>) {
                    setValue(key, DEFAULT_SET);
                }
            }
        }
        mEditor.clear();
        commit();
    }


}
