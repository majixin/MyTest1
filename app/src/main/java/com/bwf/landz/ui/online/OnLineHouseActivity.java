package com.bwf.landz.ui.online;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framwork.Constants;
import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.http.HttpRequestAsyncTask;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.framwork.utils.ToastUtil;
import com.bwf.landz.MyApplication;
import com.bwf.landz.R;
import com.bwf.landz.entity.HouseArrBean;
import com.bwf.landz.entity.HouseOneArrBean;
import com.bwf.landz.entity.OnLineHouseResult;
import com.bwf.landz.entity.OnLineHouseResultBean;
import com.bwf.landz.entity.OnLineTypeResultBean;
import com.bwf.landz.entity.OnlineTypeBean;
import com.bwf.landz.entity.ParamListBean;
import com.bwf.landz.request.OnLineHouseRequest;
import com.bwf.landz.ui.callback.PopwindowItemClickCallBack;
import com.bwf.landz.ui.online.adapter.OnLineHouseAdapter;
import com.bwf.landz.view.MyPopwindow;
import com.bwf.landz.view.RefreshListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 在售豪宅
 */
public class OnLineHouseActivity extends BaseActivity implements PopwindowItemClickCallBack {

    private OnlineTypeBean onlineTypeBean;
    private TextView tv_location, tv_price, tv_room, tv_type, tv_more;
    private RelativeLayout rl_location, rl_price, rl_room, rl_type, rl_more;

    /* 地区，价格，居室，类型数据 */
    private List<ParamListBean> location_paramList, price_paramList, room_paramList, type_paramList;

    //加载数据的ListView
    private RefreshListView lv_online_house;

    private int currentPos = -1;//popwindow标志位

    private List<Object> totalList;

    private OnLineHouseAdapter onLineHouseAdapter;//适配器

    private OnLineHouseRequest request;//请求对象

    @Override
    public int getContentViewId() {
        return R.layout.activity_on_line_house;
    }

    @Override
    public void beforeInitView() {
        request = new OnLineHouseRequest();
        request.resblockName = getIntent().getStringExtra("resblockName");
        request.circleTypeCode = getIntent().getStringExtra("circleTypeCode");

        totalList = new ArrayList<>();
        onlineTypeBean = MyApplication.getApplication().getOnlineTypeBean();
        if (onlineTypeBean != null) {

            for (OnLineTypeResultBean onLineTypeResultBean : onlineTypeBean.result) {
                if ("1001".equals(onLineTypeResultBean.paramType)) {//地区
                    location_paramList = onLineTypeResultBean.paramList;
                }
                if ("1008".equals(onLineTypeResultBean.paramType)) {//价格
                    price_paramList = onLineTypeResultBean.paramList;
                }
                if ("1005".equals(onLineTypeResultBean.paramType)) {//居室
                    room_paramList = onLineTypeResultBean.paramList;
                }
                if ("1006".equals(onLineTypeResultBean.paramType)) {//类型
                    type_paramList = onLineTypeResultBean.paramList;
                }
            }
        }
    }

    @Override
    public void initView() {
        tv_location = findViewByIdNoCast(R.id.tv_location);
        tv_price = findViewByIdNoCast(R.id.tv_price);
        tv_room = findViewByIdNoCast(R.id.tv_room);
        tv_type = findViewByIdNoCast(R.id.tv_type);
        tv_more = findViewByIdNoCast(R.id.tv_more);
        rl_location = findViewByIdNoCast(R.id.rl_location);
        rl_price = findViewByIdNoCast(R.id.rl_price);
        rl_room = findViewByIdNoCast(R.id.rl_room);
        rl_type = findViewByIdNoCast(R.id.rl_type);
        rl_more = findViewByIdNoCast(R.id.rl_more);
        lv_online_house = findViewByIdNoCast(R.id.lv_online_house);

    }

    @Override
    public void initData() {
        setOnClick(rl_location, rl_price, rl_room, rl_type, rl_more);

        onLineHouseAdapter = new OnLineHouseAdapter(this);
        lv_online_house.setAdapter(onLineHouseAdapter);
        //加载更多
        lv_online_house.setOnLoadMoreListener(new RefreshListView.OnLoadMoreListener() {
            @Override
            public void loadMore() {
                request.pageNo++;
                getData();
            }

            @Override
            public void onRefresh() {
                request.pageNo = 0;
                getData();
            }
        });

        //
        lv_online_house.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {
                    if (onLineHouseAdapter.getItem(position - 1) instanceof HouseOneArrBean) {
                        HouseOneArrBean bean = (HouseOneArrBean) onLineHouseAdapter.getItem(position - 1);
                        Bundle bundle = new Bundle();
                        bundle.putString("houseOneId", bean.houseOneId);
                        bundle.putString("resblockId", bean.resblockOneId);
                        IntentUtils.openActivity(OnLineHouseActivity.this, HouseOneDetailActivity.class,bundle);
                    }
                }

            }
        });

        getData();

    }

    /**
     * 获取在线房源数据
     */
    public void getData() {
        showProgressBar();
        if (request.pageNo == 0)
            totalList.clear();
        HttpHelper.getOnLineHouseList(this, request, new HttpRequestAsyncTask.CallBack() {
            @Override
            public void OnSuccess(String result) {

                lv_online_house.setOnComplete();

                dismissProgressBar();
                //将数据解析成对象
                OnLineHouseResult resultBean = new Gson().fromJson(result, OnLineHouseResult.class);
                resultBean.initListData(resultBean, totalList);
                onLineHouseAdapter.setTotalList(totalList);
                onLineHouseAdapter.notifyDataSetChanged();

            }

            @Override
            public void OnFailed(String errMsg) {
                lv_online_house.setOnComplete();
                dismissProgressBar();
                ToastUtil.showToast(errMsg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_location://区域
                currentPos = 0;
                MyPopwindow myPopwindow = new MyPopwindow(OnLineHouseActivity.this, false);
                myPopwindow.setParamListBean(location_paramList, this, false);
                myPopwindow.showPopWindow(rl_location);
                break;
            case R.id.rl_price://价格
                currentPos = 1;
                MyPopwindow myPopwindow2 = new MyPopwindow(OnLineHouseActivity.this, true);
                myPopwindow2.setParamListBean(price_paramList, this, true);
                myPopwindow2.showPopWindow(rl_price);
                break;
            case R.id.rl_room://
                currentPos = 2;
                MyPopwindow myPopwindow3 = new MyPopwindow(OnLineHouseActivity.this, true);
                myPopwindow3.setParamListBean(room_paramList, this, true);
                myPopwindow3.showPopWindow(rl_room);
                break;
            case R.id.rl_type://
                currentPos = 3;
                MyPopwindow myPopwindow4 = new MyPopwindow(OnLineHouseActivity.this, true);
                myPopwindow4.setParamListBean(type_paramList, this, true);
                myPopwindow4.showPopWindow(rl_type);
                break;
            case R.id.rl_more://更多
                Intent intent = new Intent(OnLineHouseActivity.this, SelectMoreActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    public void onItemClick(ParamListBean paramListBean) {

        if (paramListBean == null) {
            return;
        }

        switch (currentPos) {
            case 0://区域
                tv_location.setText("" + paramListBean.name);
                request.circleTypeCode = paramListBean.key;
                break;
            case 1://价格
                tv_price.setText("" + paramListBean.name);
                request.totalPricesBegin = paramListBean.minValue;
                request.totalPricesEnd = paramListBean.maxValue;
                break;
            case 2://居室
                tv_room.setText("" + paramListBean.name);
                request.bedroomAmount = paramListBean.value;
                break;
            case 3://类型
                tv_type.setText("" + paramListBean.name);
                request.buildingType = paramListBean.name;
                break;
        }

        request.pageNo = 0;
        getData();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == 100) {//从更多点击确定返回
            if (data != null) {
                request.sort = data.getStringExtra("sort");
                request.buildSizeBegin = data.getStringExtra("buildSizeBegin");
                request.buildSizeEnd = data.getStringExtra("buildSizeEnd");
                request.feature = data.getStringExtra("feature");
                request.onlyLook = data.getStringExtra("onlyLook");
                request.pageNo = 0;
                getData();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
