package com.bwf.landz.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.utils.AssetUtils;
import com.bwf.framwork.utils.DrawableUtils;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.framwork.utils.ToastUtil;
import com.bwf.landz.R;
import com.bwf.landz.ui.online.DaizuActivity;
import com.bwf.landz.ui.online.FindHouseToMapActivity;
import com.bwf.landz.ui.online.GuWenActivity;
import com.bwf.landz.ui.online.MyHouseActivity;
import com.bwf.landz.ui.online.OnLineHouseActivity;
import com.bwf.landz.ui.online.JianShangActivity;
import com.bwf.landz.ui.online.OnehandActivity;
import com.bwf.landz.ui.online.YanJiuActivity;
import com.bwf.landz.ui.search.SearchActivity;

/**
 * 首页
 */
public class MainActivity extends BaseActivity implements Handler.Callback {

    private TextView tv_main_online;//在线豪宅
    private TextView tv_main_seebuild;//楼盘鉴赏
    private TextView tv_main_wait_rent;//待租豪宅
    private TextView tv_main_onehouse;//一手豪宅
    private TextView tv_main_map;//地图找房
    private TextView tv_main_study;//豪宅研究
    private TextView tv_main_man;//豪宅顾问
    private TextView tv_main_myhouse;//我的豪宅

    private TextView[] textViews;
    private Integer[] normal_ids = new Integer[]{R.mipmap.main_online_normal, R.mipmap.main_seebuild_normal
            , R.mipmap.main_wait_rent_normal, R.mipmap.main_onehouse_normal, R.mipmap.main_map_normal, R.mipmap.main_study_normal
            , R.mipmap.main_man_normal, R.mipmap.main_myhouse_normal};
    private Integer[] select_ids = new Integer[]{R.mipmap.main_online, R.mipmap.main_seebuild
            , R.mipmap.main_wait_rent, R.mipmap.main_onehouse, R.mipmap.main_map, R.mipmap.main_study, R.mipmap.main_man, R.mipmap.main_myhouse};

    private Handler handler;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void beforeInitView() {
        handler = new Handler(this);
    }

    @Override
    public void initView() {
        tv_main_online = findViewByIdNoCast(R.id.tv_main_online);
        tv_main_seebuild = findViewByIdNoCast(R.id.tv_main_seebuild);
        tv_main_wait_rent = findViewByIdNoCast(R.id.tv_main_wait_rent);
        tv_main_onehouse = findViewByIdNoCast(R.id.tv_main_onehouse);
        tv_main_map = findViewByIdNoCast(R.id.tv_main_map);
        tv_main_study = findViewByIdNoCast(R.id.tv_main_study);
        tv_main_man = findViewByIdNoCast(R.id.tv_main_man);
        tv_main_myhouse = findViewByIdNoCast(R.id.tv_main_myhouse);
    }

    @Override
    public void initData() {
        textViews = new TextView[]{tv_main_online, tv_main_seebuild, tv_main_wait_rent, tv_main_onehouse,
                tv_main_map,tv_main_study, tv_main_man,tv_main_myhouse};
        setOnClick(tv_main_online, tv_main_seebuild, tv_main_wait_rent, tv_main_onehouse,
                tv_main_map,tv_main_study, tv_main_man,tv_main_myhouse);
        setOnClick(R.id.et_main_search);
        // getApplication()//获取到application对象，只有activity才有这个方法,context没有这方法但是可以拿到getApplicationContext()

        AssetUtils.getOnlineTypeData(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_main_search://跳转搜索页面
                Bundle bundle = new Bundle();
                bundle.putInt("type", 5);
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.tv_main_online://在售豪宅
                setSelect(0);
                IntentUtils.openActivity(MainActivity.this, OnLineHouseActivity.class);
                break;
            case R.id.tv_main_seebuild://楼盘鉴赏
                IntentUtils.openActivity(MainActivity.this, JianShangActivity.class);
                setSelect(1);
                break;
            case R.id.tv_main_wait_rent://待租豪宅
                IntentUtils.openActivity(MainActivity.this, DaizuActivity.class);
                setSelect(2);
                break;
            case R.id.tv_main_onehouse://一手豪宅
                IntentUtils.openActivity(MainActivity.this, OnehandActivity.class);
                setSelect(3);
                break;
            case R.id.tv_main_map://地图找房
                IntentUtils.openActivity(MainActivity.this, FindHouseToMapActivity.class);
                setSelect(4);
                break;
            case R.id.tv_main_study://豪宅研究
                IntentUtils.openActivity(MainActivity.this, YanJiuActivity.class);
                setSelect(5);
                break;
            case R.id.tv_main_man://豪宅顾问
                IntentUtils.openActivity(MainActivity.this, GuWenActivity.class);
                setSelect(6);
                break;
            case R.id.tv_main_myhouse://我的豪宅
                IntentUtils.openActivity(MainActivity.this, MyHouseActivity.class);
                setSelect(7);
                break;
        }
    }

    public void setSelect(int postion) {
        for (int i = 0; i < textViews.length; i++) {
            if (i == postion) {
                textViews[i].setTextColor(Color.parseColor("#ff0000"));
                DrawableUtils.drawableTop(MainActivity.this, textViews[i], select_ids[i]);
            } else {
                textViews[i].setTextColor(Color.WHITE);
                DrawableUtils.drawableTop(MainActivity.this, textViews[i], normal_ids[i]);
            }
        }

    }

    private static final int TIMES = 2000;

    private boolean isBack = true;

    /**
     * 按下监听
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {//按下返回键

            if (isBack) {
                ToastUtil.showToast("再点一次退出");
                isBack = false;
                handler.sendEmptyMessageDelayed(1, 2000);
            } else {
                //退出app
                System.exit(0);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            case 1:
                isBack = true;
                break;
        }

        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
