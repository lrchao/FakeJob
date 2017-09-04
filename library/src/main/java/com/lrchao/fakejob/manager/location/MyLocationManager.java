package com.lrchao.fakejob.manager.location;

import com.lrchao.fakejob.manager.shared_preference.CommonSharedPreference;

import static com.lrchao.fakejob.constant.SharedPreferenceKey.PREF_CURRENT_LOCATION;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/9/3 下午3:58
 */

public class MyLocationManager {

    private static MyLocationManager sInstance;

    private MyLocationManager() {

    }

    public static MyLocationManager getInstance() {
        synchronized (MyLocationManager.class) {
            if (sInstance == null) {
                sInstance = new MyLocationManager();
            }
        }
        return sInstance;
    }

    public String getCurrentLocation() {
        return CommonSharedPreference.getsInstance().getStringValue(PREF_CURRENT_LOCATION);
    }

    public void setCurrentLocation(String currentLocation) {
        CommonSharedPreference.getsInstance().setValue(PREF_CURRENT_LOCATION, currentLocation);
    }
}
