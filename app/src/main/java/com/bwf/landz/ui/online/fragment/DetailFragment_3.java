package com.bwf.landz.ui.online.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.framwork.image.ImageLoader;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.framwork.utils.ListViewUtils;
import com.bwf.landz.R;
import com.bwf.landz.entity.GuWenResultBean;
import com.bwf.landz.ui.guwen.TakeLookHistoryActivity;
import com.bwf.landz.ui.online.adapter.GuWenAdapter;

/**
 * 本房顾问
 */
public class DetailFragment_3 extends BaseFragment {

    private TextView tv_guwen;

    private ListView lv_guwen;

    private GuWenResultBean.GuWenBean result;

    private ImageLoader imageLoader;

    private String resourceId;

    public void setResult(GuWenResultBean.GuWenBean result, String resourceId, ImageLoader imageLoader) {
        this.result = result;
        this.resourceId = resourceId;
        this.imageLoader = imageLoader;
        initData();
    }

    @Override
    protected int getResource() {
        return R.layout.fragment_detail_fragment_3;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        tv_guwen = findViewByIdNoCast(R.id.tv_guwen);
        lv_guwen = findViewByIdNoCast(R.id.lv_guwen);
    }

    @Override
    protected void initData() {
        setOnClick(R.id.tv_guwen);
        if (result != null) {
            String guwen = String.format(getString(R.string.guwen), "" + result.totalAmount);
            tv_guwen.setText(Html.fromHtml(guwen));
            GuWenAdapter guWenAdapter = new GuWenAdapter(getContext(), result.showArr, imageLoader);
            lv_guwen.setAdapter(guWenAdapter);
            ListViewUtils.measureListViewHeight(lv_guwen);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_guwen:
                Bundle bundle = new Bundle();
                bundle.putString("resourceId", resourceId);
                if (result != null) {
                    bundle.putString("totalAmount", result.totalAmount);
                }
                IntentUtils.openActivity(getActivity(), TakeLookHistoryActivity.class, bundle);
                break;
        }
    }

}
