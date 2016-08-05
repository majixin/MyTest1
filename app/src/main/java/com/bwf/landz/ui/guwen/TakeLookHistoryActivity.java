package com.bwf.landz.ui.guwen;

import android.view.View;
import android.widget.TextView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.http.HttpRequestAsyncTask;
import com.bwf.framwork.image.ImageLoader;
import com.bwf.landz.R;
import com.bwf.landz.entity.LookHistoryResultBean;
import com.bwf.landz.ui.online.adapter.GuWenAdapter;
import com.bwf.landz.view.RefreshListView;
import com.google.gson.Gson;

/**
 * 带看房顾问页面
 */
public class TakeLookHistoryActivity extends BaseActivity {

    private TextView tv_title;

    private RefreshListView lv_look;

    private ImageLoader imageLoader;

    private GuWenAdapter guWenAdapter;

    private String resourceId, totalAmount;
    private int pageNo;

    @Override
    public int getContentViewId() {
        return R.layout.activity_take_look_history;
    }

    @Override
    public void beforeInitView() {
        resourceId = getIntent().getStringExtra("resourceId");
        totalAmount = getIntent().getStringExtra("totalAmount");
        imageLoader = new ImageLoader();
    }

    @Override
    public void initView() {
        lv_look = findViewByIdNoCast(R.id.lv_look);
        tv_title = findViewByIdNoCast(R.id.tv_title);
    }

    @Override
    public void initData() {
        tv_title.setText(String.format(getString(R.string.guwen), totalAmount));
        guWenAdapter = new GuWenAdapter(this, imageLoader);
        lv_look.setAdapter(guWenAdapter);
        lv_look.setOnLoadMoreListener(new RefreshListView.OnLoadMoreListener() {
            @Override
            public void loadMore() {
                pageNo++;
                getData();
            }

            @Override
            public void onRefresh() {
                pageNo = 0;
                getData();
            }
        });
        getData();
    }

    /**
     * 获取带看房顾问列表
     */
    public void getData() {
        HttpHelper.getLookHistory(this, resourceId, "1", pageNo, new HttpRequestAsyncTask.CallBack() {
            @Override
            public void OnSuccess(String result) {
                LookHistoryResultBean resultBean = new Gson().fromJson(result, LookHistoryResultBean.class);
                if (resultBean != null && resultBean.result != null) {
                    guWenAdapter.setShowArrs(resultBean.result.showArr);
                    guWenAdapter.notifyDataSetChanged();
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
}
