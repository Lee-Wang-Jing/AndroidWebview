package com.wangjing.qfwebview;

import android.content.Context;

import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;

import java.util.HashMap;

public class InitX5Utils {

    public static void initX5(Context mContext, QbSdk.PreInitCallback preInitCallback) {
        // 在调用TBS初始化、创建WebView之前进行如下配置
        HashMap map = new HashMap();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(map);
//        QbSdk.reset();
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.initX5Environment(mContext, preInitCallback);
    }
}
