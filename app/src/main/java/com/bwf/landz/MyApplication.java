package com.bwf.landz;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.bwf.landz.entity.ImgUrlArrBean;
import com.bwf.landz.entity.OnlineTypeBean;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/7/13 0013.
 * Description: 自定义Application 。
 *   1.可以存全局变量或者对象（整个app）
 *   2.app一启动最先创建application
 *   3.一个app只有一个application,但是每多加一个进程都会初始化一次Application
 *   4.如果像初始化一次的话，可以只在一个进程中初始化(实际用的时候说)
 */
public class MyApplication extends Application {

    private String str;

    private static MyApplication app;

    private OnlineTypeBean onlineTypeBean;

    public List<ImgUrlArrBean> imgUrlArr;//详情展示图

    @Override
    public void onCreate() {
        super.onCreate();
        str = "123";
        app = this;

    }

    public static MyApplication getApplication(){
        return app;
    }

    public static Context getAppContext(){
        return app.getApplicationContext();
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }


    public OnlineTypeBean getOnlineTypeBean() {
        return onlineTypeBean;
    }

    public void setOnlineTypeBean(OnlineTypeBean onlineTypeBean) {
        this.onlineTypeBean = onlineTypeBean;
    }

    public List<ImgUrlArrBean> getImgUrlArr() {
        return imgUrlArr;
    }

    public void setImgUrlArr(List<ImgUrlArrBean> imgUrlArr) {
        this.imgUrlArr = imgUrlArr;
    }
}
