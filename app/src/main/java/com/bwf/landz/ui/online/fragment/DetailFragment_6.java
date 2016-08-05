package com.bwf.landz.ui.online.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.http.HttpRequestAsyncTask;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.framwork.utils.ListUtils;
import com.bwf.framwork.utils.ListViewUtils;
import com.bwf.landz.R;
import com.bwf.landz.entity.OnLineHouseResult;
import com.bwf.landz.entity.OneHouseMoreResultBean;
import com.bwf.landz.ui.online.OneHouseMoreActivity;
import com.bwf.landz.ui.online.adapter.OnHouseMoreAdapter;
import com.bwf.landz.ui.online.adapter.OnLineHouseAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 更多一手房源推荐
 */
public class DetailFragment_6 extends BaseFragment {

    private TextView tv_house_more;

    private ListView lv_house_more;

    private OnHouseMoreAdapter adapter;

    private String houseId, resblockId;//房源id，楼盘id


    public void setHouseId(String houseId, String resblockId) {
        this.houseId = houseId;
        this.resblockId = resblockId;
        getData();
    }


    @Override
    protected int getResource() {
        return R.layout.fragment_detail_fragment_6;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        tv_house_more = findViewByIdNoCast(R.id.tv_house_more);
        lv_house_more = findViewByIdNoCast(R.id.lv_house_more);
    }

    @Override
    protected void initData() {
        setOnClick(R.id.tv_house_more);
    }

    /**
     * 获取一手房更多推荐
     */
    public void getData() {
        HttpHelper.getOneDetailMore(getActivity(), houseId, resblockId, new HttpRequestAsyncTask.CallBack() {
            @Override
            public void OnSuccess(String result) {
                OneHouseMoreResultBean resultBean = new Gson().fromJson(result, OneHouseMoreResultBean.class);
                if (resultBean != null) {
                    if (resultBean.result != null && !resultBean.result.isEmpty()) {
                        tv_house_more.setText(String.format(getString(R.string.houser_more), resultBean.result.get(0).totalNumber));
                    }
                    //将数据解析成对象
                    adapter = new OnHouseMoreAdapter(getContext());
                    adapter.setTotalList(resultBean.result);
                    lv_house_more.setAdapter(adapter);
                    ListViewUtils.measureListViewHeight(lv_house_more);
                }

            }

            @Override
            public void OnFailed(String errMsg) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_house_more:
                Bundle bundle = new Bundle();
                bundle.putString("houseOneId",houseId);
                bundle.putString("resblockId",resblockId);
                IntentUtils.openActivity(getContext(), OneHouseMoreActivity.class,bundle);
                break;
        }
    }
}
