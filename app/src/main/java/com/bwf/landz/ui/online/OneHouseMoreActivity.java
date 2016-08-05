package com.bwf.landz.ui.online;

import android.view.View;
import android.widget.TextView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.http.HttpRequestAsyncTask;
import com.bwf.landz.R;
import com.bwf.landz.entity.OneHouseMoreResultBean;
import com.bwf.landz.ui.online.adapter.OnHouseMoreAdapter;
import com.bwf.landz.view.RefreshListView;
import com.google.gson.Gson;

/**
 * 一手房更多推荐
 */
public class OneHouseMoreActivity extends BaseActivity {

    private RefreshListView lv_one_house_more;

    private String houseId, resblockId;//房源id，楼盘id

    private OnHouseMoreAdapter adapter;

    private TextView tv_title;

    private int pageNo;

    @Override
    public int getContentViewId() {
        return R.layout.activity_one_house_more;
    }

    @Override
    public void beforeInitView() {
        houseId = getIntent().getStringExtra("houseOneId");
        resblockId = getIntent().getStringExtra("resblockId");
    }

    @Override
    public void initView() {
        tv_title = findViewByIdNoCast(R.id.tv_title);
        lv_one_house_more = findViewByIdNoCast(R.id.lv_one_house_more);
    }

    @Override
    public void initData() {
        tv_title.setText("" + getString(R.string.title_one_house_more));
        adapter = new OnHouseMoreAdapter(this);
        lv_one_house_more.setAdapter(adapter);
        lv_one_house_more.setOnLoadMoreListener(new RefreshListView.OnLoadMoreListener() {
            @Override
            public void loadMore() {//加载更多
                pageNo++;
                getData();
            }

            @Override
            public void onRefresh() {//下拉刷新
                pageNo = 0;
                getData();
            }
        });
        getData();
    }

    /**
     * 获取一手房更多推荐
     */
    public void getData() {
        showProgressBar();
        HttpHelper.getOneHouseMore(this, houseId, resblockId, 0, new HttpRequestAsyncTask.CallBack() {
            @Override
            public void OnSuccess(String result) {
                lv_one_house_more.setOnComplete();
                dismissProgressBar();
                OneHouseMoreResultBean resultBean = new Gson().fromJson(result, OneHouseMoreResultBean.class);
                if (resultBean != null) {
                    //将数据解析成对象
                    adapter.setTotalList(resultBean.result);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void OnFailed(String errMsg) {
                dismissProgressBar();
                lv_one_house_more.setOnComplete();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
