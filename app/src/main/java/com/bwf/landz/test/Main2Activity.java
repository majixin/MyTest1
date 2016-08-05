package com.bwf.landz.test;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.http.HttpRequestAsyncTask;
import com.bwf.framwork.share.SharePrefreceHelper;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.framwork.utils.ToastUtil;
import com.bwf.landz.MyApplication;
import com.bwf.landz.R;

public class Main2Activity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_main2;
    }

    @Override
    public void beforeInitView() {
//        AppManager.getInstance().killActivity(MainActivity.class);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 相关测试
     */
    public void test() {

        SharePrefreceHelper.getInstence(this).setUserName("123");
        startService(new Intent(this, MyService.class));

        //application常量测试
        ((MyApplication) getApplication()).getStr();

        //handler测试
        initHandler();

        //回调测试
        MyAdapter adapter = new MyAdapter(this, new MyAdapter.ClickListener() {
            @Override
            public void click(View view) {

            }
        });

//        runOnUiThread();

        //测试Looper
        new Thread(new Runnable() {
            @Override
            public void run() {

                Looper.prepare();//新建一个MessageQueue

                ToastUtil.showToast("2423");

                Looper.loop();//代码结束，后面的代码不会执行

            }
        }).start();

        //获取string，color等
        String app_name = getString(R.string.app_name);
//        getColor(R.color.colorPrimary);

        //测试Util相关
        ToastUtil.showToast("123");
        ToastUtil.showToast(R.string.app_name);
        IntentUtils.openActivity(this, Main2Activity.class);

        //测试网络请求
//        HttpHelper.login(this, new HttpRequestAsyncTask.CallBack() {
//            @Override
//            public void OnSuccess(String result) {
//
//            }
//
//            @Override
//            public void OnFailed(String errMsg) {
//
//            }
//        });
    }

    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                ToastUtil.showToast("123");
                super.handleMessage(msg);
            }
        };
        handler.sendEmptyMessageDelayed(1, 5000);
    }

    private Handler handler;

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);//handler里面所有消息都不会发送了
        super.onDestroy();
    }
}
