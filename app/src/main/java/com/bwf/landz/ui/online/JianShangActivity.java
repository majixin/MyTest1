package com.bwf.landz.ui.online;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.http.HttpHelper;
import com.bwf.framwork.http.HttpRequestAsyncTask;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.framwork.utils.ToastUtil;
import com.bwf.landz.MyApplication;
import com.bwf.landz.R;
import com.bwf.landz.entity.JianShangBean;
import com.bwf.landz.entity.JianShangResult;
import com.bwf.landz.entity.OnLineTypeResultBean;
import com.bwf.landz.entity.OnlineTypeBean;
import com.bwf.landz.entity.ParamListBean;
import com.bwf.landz.ui.online.adapter.JianShangAdapter;
import com.bwf.landz.view.JianShangPopWindow;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 楼盘鉴赏
 */
public class JianShangActivity extends BaseActivity {

    private TextView tv_jianshang_location,tv_jianshang_price,tv_jianshang_room,tv_jianshang_type,tv_jianshang_more;
    private RelativeLayout rl_jianshang_location,rl_jianshang_price,rl_jianshang_room,rl_jianshang_type,rl_jianshang_more;
    private OnlineTypeBean onlineTypeBean ;
    private List<Object> totalList;
    private JianShangAdapter jianShangAdapter ; //适配器

    private JianShangBean jianShangBean;

    @Override
    public int getContentViewId() {
        return R.layout.activity_jian_shang;
    }

    @Override
    public void beforeInitView() {
        totalList = new ArrayList<>();
        jianShangBean = new JianShangBean();
        onlineTypeBean = MyApplication.getApplication().getOnlineTypeBean();
    }

    @Override
    public void initView() {
        tv_jianshang_location = findViewByIdNoCast(R.id.tv_jianshang_location);
        tv_jianshang_price = findViewByIdNoCast(R.id.tv_jianshang_price);
        tv_jianshang_room = findViewByIdNoCast(R.id.tv_jianshang_room);
        tv_jianshang_type = findViewByIdNoCast(R.id.tv_jianshang_type);
        tv_jianshang_more = findViewByIdNoCast(R.id.tv_jianshang_more);
        rl_jianshang_location = findViewByIdNoCast(R.id.rl_jianshang_location);
        rl_jianshang_price = findViewByIdNoCast(R.id.rl_jianshang_price);
        rl_jianshang_room = findViewByIdNoCast(R.id.rl_jianshang_room);
        rl_jianshang_type = findViewByIdNoCast(R.id.rl_jianshang_type);
        rl_jianshang_more = findViewByIdNoCast(R.id.rl_jianshang_more);
    }

    @Override
    public void initData() {
        setOnClick(tv_jianshang_location,tv_jianshang_price,tv_jianshang_room,tv_jianshang_type,tv_jianshang_more);
        jianShangAdapter = new JianShangAdapter(this);
        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_jianshang_location://区域
                List<ParamListBean> paramList = null ;
                for(OnLineTypeResultBean onLineTypeResultBean :onlineTypeBean.result){
                    if("1001".equals(onLineTypeResultBean.paramType)){
                        paramList = onLineTypeResultBean.paramList;
                    }
                }
                JianShangPopWindow popWindow = new JianShangPopWindow(JianShangActivity.this,false);
                popWindow.setParamListBean(paramList);
                popWindow.showPopWindow(tv_jianshang_location);
                break;
            case R.id.tv_jianshang_price://价格
                List<ParamListBean> price = null ;
                for(OnLineTypeResultBean onLineTypeResultBean1 :onlineTypeBean.result){
                    if("1003".equals(onLineTypeResultBean1.paramType)){
                        price = onLineTypeResultBean1.paramList;
                    }
                }
                JianShangPopWindow pricepop = new JianShangPopWindow(JianShangActivity.this,true);
                pricepop.setParamListBean(price);
                pricepop.showPopWindow(tv_jianshang_price);
                break;
            case R.id.tv_jianshang_room://居室   1005
                List<ParamListBean> room = null ;
                for(OnLineTypeResultBean onLineTypeResultBean2 : onlineTypeBean.result){
                    if("1005".equals(onLineTypeResultBean2.paramType)){
                        room = onLineTypeResultBean2.paramList;
                    }
                }
                JianShangPopWindow roompop = new JianShangPopWindow(JianShangActivity.this,true);
                roompop.setParamListBean(room);
                roompop.showPopWindow(tv_jianshang_room);
                break;
            case R.id.tv_jianshang_type://类型   1006
                List<ParamListBean> type = null ;
                for(OnLineTypeResultBean onLineTypeResultBean3 : onlineTypeBean.result){
                    if("1006".equals(onLineTypeResultBean3.paramType)){
                        type = onLineTypeResultBean3.paramList;
                    }
                }
                JianShangPopWindow typepop = new JianShangPopWindow(JianShangActivity.this,true);
                typepop.setParamListBean(type);
                typepop.showPopWindow(tv_jianshang_type);
                break;
            case R.id.tv_jianshang_more://更多

                break;
        }
    }
    public void getData(){
        HttpHelper.getJianShang(this, jianShangBean, new HttpRequestAsyncTask.CallBack() {
            @Override
            public void OnSuccess(String result) {
                JianShangResult shangBean = new Gson().fromJson(result, JianShangResult.class);
                shangBean.initListData(shangBean,totalList);
                jianShangAdapter.setTotalList(totalList);
                jianShangAdapter.notifyDataSetChanged();
            }

            @Override
            public void OnFailed(String errMsg) {

            }
        });
    }

}
