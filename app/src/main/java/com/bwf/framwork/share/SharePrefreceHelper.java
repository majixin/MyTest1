package com.bwf.framwork.share;

import android.content.Context;

/**
 * Created by Lizhangfeng on 2016/7/13 0013.
 * Description:
 */
public class SharePrefreceHelper extends PrefrenceWrapper {

    private static SharePrefreceHelper sharePrefreceHelper;

    private SharePrefreceHelper(Context context) {
        super(context);
    }

    public static SharePrefreceHelper getInstence(Context context) {
        if (sharePrefreceHelper == null)
            sharePrefreceHelper = new SharePrefreceHelper(context);
        return sharePrefreceHelper;
    }

    public void setUserName(String userName){
        putString("userName",userName);
    }

    public String getUserName(){
        return getString("userName");
    }

    public void setIsFirst(boolean isFirst){
        setBoolean("isFirst",isFirst);
    }

    public boolean isFirst(){
        return getBoolean("isFirst",true);
    }

}
