package com.bwf.landz.ui.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.http.HttpRequestAsyncTask;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.framwork.utils.ToastUtil;
import com.bwf.landz.R;
import com.bwf.landz.entity.SearchBean;
import com.bwf.landz.entity.SearchResultBean;
import com.bwf.landz.ui.online.OnLineHouseActivity;
import com.bwf.landz.ui.search.adapter.SearchAdapter;
import com.google.gson.Gson;

import java.util.List;

/**
 * 搜索页面
 */
public class SearchActivity extends BaseActivity {

    private ListView lv_search;

    private EditText et_search;

    private SearchAdapter adapter;

    /**
     * type=1 地图过来的 type=2,二手楼盘过来的 type=3为买卖 type=4 租赁列表过来 type=5 首页过来
     */
    private int type;

    public List<SearchBean> searchBeen;

    @Override
    public int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    public void beforeInitView() {
        type = getIntent().getIntExtra("type", 0);
    }

    @Override
    public void initView() {
        et_search = findViewByIdNoCast(R.id.et_search);
        lv_search = findViewByIdNoCast(R.id.lv_search);
    }

    @Override
    public void initData() {
        switch (type) {
            case 5:
                et_search.setHint("请输入楼盘名称或房源特征…");
                break;
        }

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = et_search.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    getData(content);
                } else {
                    searchBeen.clear();
                    adapter.setResult(searchBeen);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        adapter = new SearchAdapter(this);
        lv_search.setAdapter(adapter);


        //item点击监听
        lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String resblockName = "";
                String circleTypeCode = "";
                SearchBean bean = (SearchBean) adapter.getItem(position);
                if ("0".equals(bean.type) || "1".equals(bean.type))
                    resblockName = bean.name;
                else
                    circleTypeCode = bean.id;
                Bundle bundle = new Bundle();
                bundle.putString("resblockName", resblockName);
                bundle.putString("circleTypeCode", circleTypeCode);
                IntentUtils.openActivity(SearchActivity.this, OnLineHouseActivity.class, bundle);


            }
        });

    }

    /**
     * 获取搜索数据
     *
     * @param content
     */
    public void getData(String content) {
        HttpHelper.getSearchDatas(this, content, "0", new HttpRequestAsyncTask.CallBack() {
            @Override
            public void OnSuccess(String result) {
                dismissSoftKeyboard(SearchActivity.this);
                SearchResultBean resultBean = new Gson().fromJson(result, SearchResultBean.class);
                searchBeen = resultBean.result;
                adapter.setResult(searchBeen);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void OnFailed(String errMsg) {
                dismissSoftKeyboard(SearchActivity.this);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
