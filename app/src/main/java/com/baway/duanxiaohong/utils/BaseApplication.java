package com.baway.duanxiaohong.utils;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * 作者: 段晓红
 * 时间:2017/8/14
 * 描述:
 */

public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
