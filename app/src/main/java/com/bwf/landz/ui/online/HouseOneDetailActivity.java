package com.bwf.landz.ui.online;

import android.view.View;
import android.widget.ScrollView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.http.HttpRequestAsyncTask;
import com.bwf.framwork.image.ImageLoader;
import com.bwf.landz.MyApplication;
import com.bwf.landz.R;
import com.bwf.landz.entity.GuWenResultBean;
import com.bwf.landz.entity.HouseDetailBean;
import com.bwf.landz.entity.HouseDetailResultBean;
import com.bwf.landz.ui.online.fragment.DetailFragment_05;
import com.bwf.landz.ui.online.fragment.DetailFragment_1;
import com.bwf.landz.ui.online.fragment.DetailFragment_2;
import com.bwf.landz.ui.online.fragment.DetailFragment_3;
import com.bwf.landz.ui.online.fragment.DetailFragment_4;
import com.bwf.landz.ui.online.fragment.DetailFragment_5;
import com.bwf.landz.ui.online.fragment.DetailFragment_6;
import com.bwf.landz.view.HouseDetailBottomView;
import com.bwf.landz.view.HouseDetailTitleBar;
import com.bwf.landz.view.OhtherHousePopwindow;
import com.google.gson.Gson;

/**
 * 一手房详情
 */
public class HouseOneDetailActivity extends BaseActivity implements DetailFragment_05.OnShowPopListener {

    /* 标题部分 */
    private HouseDetailTitleBar titleBar;
    /* 底部悬浮部分 */
    private HouseDetailBottomView bottomView;
    /* 房源id，楼盘id */
    private String houseOneId, resblockId;

    private DetailFragment_1 detail_fragment1;//ViewPager 展示
    private DetailFragment_2 detail_fragment2;//房源描述
    private DetailFragment_3 detail_fragment3;//本房顾问
    private DetailFragment_4 detail_fragment4;//房源基本信息
    private DetailFragment_05 detailFragment_05 ;//其他户型推荐
    private DetailFragment_5 detail_fragment5;//楼盘概述
    private DetailFragment_6 detail_fragment6;//更多推荐

    private ImageLoader imageLoader;

    private ScrollView scrollView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_house_one_detail;
    }

    @Override
    public void beforeInitView() {
        imageLoader = new ImageLoader();
        houseOneId = getIntent().getStringExtra("houseOneId");
        resblockId = getIntent().getStringExtra("resblockId");
    }

    @Override
    public void initView() {
        titleBar = findViewByIdNoCast(R.id.titleBar);
        bottomView = findViewByIdNoCast(R.id.bottomView);
        scrollView = findViewByIdNoCast(R.id.scrollView);
        detail_fragment1 = (DetailFragment_1) getSupportFragmentManager().findFragmentById(R.id.detail_fragment1);
        detail_fragment2 = (DetailFragment_2) getSupportFragmentManager().findFragmentById(R.id.detail_fragment2);
        detail_fragment3 = (DetailFragment_3) getSupportFragmentManager().findFragmentById(R.id.detail_fragment3);
        detail_fragment4 = (DetailFragment_4) getSupportFragmentManager().findFragmentById(R.id.detail_fragment4);
        detailFragment_05 = (DetailFragment_05) getSupportFragmentManager().findFragmentById(R.id.detail_fragment05);
        detail_fragment5 = (DetailFragment_5) getSupportFragmentManager().findFragmentById(R.id.detail_fragment5);
        detail_fragment6 = (DetailFragment_6) getSupportFragmentManager().findFragmentById(R.id.detail_fragment6);
    }

    @Override
    public void initData() {
        titleBar.setTitle("昆泰嘉瑞中心", "2020-2074万 262平米 2室2厅2卫");
        detail_fragment6.setHouseId(houseOneId, resblockId);
        getData();
        getGuWenData();
    }

    /**
     * 详情
     */
    public void getData() {
        HttpHelper.getDetail(this, houseOneId, new HttpRequestAsyncTask.CallBack() {
            @Override
            public void OnSuccess(String result) {
                HouseDetailResultBean resultBean = new Gson().fromJson(result, HouseDetailResultBean.class);
                if (resultBean != null && resultBean.result != null) {
                    HouseDetailBean houseDetailBean = resultBean.result;
                    MyApplication.getApplication().setImgUrlArr(houseDetailBean.imgUrlArr);
                    detail_fragment1.setImgUrlArr(houseDetailBean.imgUrlArr);
                    detail_fragment2.setResultBean(resultBean, imageLoader);
                    detail_fragment4.setResult(houseDetailBean);

                    String desc = houseDetailBean.bedroomAmount + "室" + houseDetailBean.parlorAmount + "厅" + houseDetailBean.parlorAmount + "卫"
                            + " " + houseDetailBean.innenbereichSize + "平米";
                    detailFragment_05.setDesc(desc);
                    if (resultBean.result.imgUrlArr != null && !resultBean.result.imgUrlArr.isEmpty())
                        detailFragment_05.setOther_house(resultBean.result.imgUrlArr.get(0).picName);
                    detail_fragment5.setResult(resultBean.result);
                    scrollView.scrollTo(0, 0);
                }
            }

            @Override
            public void OnFailed(String errMsg) {

            }
        });
    }

    /**
     * 本方顾问列表
     */
    public void getGuWenData() {
        HttpHelper.getOneDetailLook(this, houseOneId, new HttpRequestAsyncTask.CallBack() {
            @Override
            public void OnSuccess(String result) {
                GuWenResultBean resultBean = new Gson().fromJson(result, GuWenResultBean.class);
                if (resultBean != null) {
                    detail_fragment3.setResult(resultBean.result, houseOneId, imageLoader);
                    if (resultBean.result.showArr != null && !resultBean.result.showArr.isEmpty())
                        bottomView.setShowArr(HouseOneDetailActivity.this, resultBean.result.showArr.get(0), imageLoader);
                }
            }

            @Override
            public void OnFailed(String errMsg) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void showPop(String url) {
        OhtherHousePopwindow ohtherHousePopwindow = new OhtherHousePopwindow(this,url);
        ohtherHousePopwindow.showPopWindow(titleBar);
    }
}
