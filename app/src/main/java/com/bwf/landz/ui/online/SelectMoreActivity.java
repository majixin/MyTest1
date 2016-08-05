package com.bwf.landz.ui.online;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.utils.ListUtils;
import com.bwf.landz.MyApplication;
import com.bwf.landz.R;
import com.bwf.landz.entity.OnLineTypeResultBean;
import com.bwf.landz.entity.OnlineTypeBean;
import com.bwf.landz.entity.ParamListBean;
import com.bwf.landz.ui.online.adapter.SelectMoreAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 筛选更多
 */
public class SelectMoreActivity extends BaseActivity {

    private SelectMoreAdapter adapter, adapter2;

    private ListView lv_location, lv_location2;

    private OnlineTypeBean onlineTypeBean;

    private OnLineTypeResultBean resultBean;

    private List<ParamListBean> mianji_paramList;

    private ArrayList<ParamListBean> new_paramList;

    private Map<Integer, String> selectMap;

    @Override
    public int getContentViewId() {
        return R.layout.activity_select_more;
    }

    @Override
    public void beforeInitView() {
        new_paramList = new ArrayList<>();
        selectMap = new HashMap<>();
        selectMap.put(0, "0");
        selectMap.put(1, "0");
        selectMap.put(2, "0");
        selectMap.put(3, "0");
        //获取面积列表
        onlineTypeBean = MyApplication.getApplication().getOnlineTypeBean();
        if (onlineTypeBean != null) {
            for (OnLineTypeResultBean bean : onlineTypeBean.result) {
                if ("1004".equals(bean.paramType)) {
                    mianji_paramList = bean.paramList;
                    mianji_paramList.get(0).isSelect = true;
                }
            }
        }

        resultBean = new OnLineTypeResultBean();
        List<ParamListBean> paramList = new ArrayList<>();
        paramList.add(new ParamListBean("排序", true, getPaiXu()));
        paramList.add(new ParamListBean("面积", false, mianji_paramList));
        paramList.add(new ParamListBean("特色", false, getTeSe()));
        paramList.add(new ParamListBean("一手/二手", false, getYiShou()));
        resultBean.paramList = paramList;
        new_paramList = (ArrayList<ParamListBean>) ListUtils.deepCopy(paramList);
    }

    public List<ParamListBean> getPaiXu() {
        List<ParamListBean> paramListBeen = new ArrayList<>();
        paramListBeen.add(new ParamListBean("默认排序", "-1", true));
        paramListBeen.add(new ParamListBean("面积从大到小", "1"));
        paramListBeen.add(new ParamListBean("面积从小到大", "2"));
        paramListBeen.add(new ParamListBean("总价从低到高", "3"));
        paramListBeen.add(new ParamListBean("总价从高到低", "4"));
        paramListBeen.add(new ParamListBean("关注度从高到低", "5"));
        return paramListBeen;
    }

    public List<ParamListBean> getTeSe() {
        List<ParamListBean> paramListBeen = new ArrayList<>();
        paramListBeen.add(new ParamListBean("不限", "0", true));
        paramListBeen.add(new ParamListBean("今日可看房", "1"));
        paramListBeen.add(new ParamListBean("钥匙房", "2"));
        return paramListBeen;
    }

    public List<ParamListBean> getYiShou() {
        List<ParamListBean> paramListBeen = new ArrayList<>();
        paramListBeen.add(new ParamListBean("不限", "0", true));
        paramListBeen.add(new ParamListBean("只看一手房", "1"));
        paramListBeen.add(new ParamListBean("只看二手房", "2"));
        return paramListBeen;
    }


    @Override
    public void initView() {
        lv_location = findViewByIdNoCast(R.id.lv_location);
        lv_location2 = findViewByIdNoCast(R.id.lv_location2);
    }

    @Override
    public void initData() {
        setOnClick(R.id.tv_clear, R.id.btn_ok);
        //一级列表
        adapter = new SelectMoreAdapter(this, false);
        lv_location.setAdapter(adapter);

        //二级
        adapter2 = new SelectMoreAdapter(this, true);
        lv_location2.setAdapter(adapter2);

        setParamListBean(resultBean.paramList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_clear://清空

//                for (int i = 0; i < resultBean.paramList.size(); i++) {
//                    for (int j = 0; j < resultBean.paramList.get(i).childList.size(); j++) {
//                        if (j == 0) {
//                            resultBean.paramList.get(i).childList.get(j).isSelect = true;
//                        } else
//                            resultBean.paramList.get(i).childList.get(j).isSelect = false;
//                    }
//                }
                resultBean.paramList.clear();
                resultBean.paramList = ListUtils.deepCopy(new_paramList);

                setParamListBean(resultBean.paramList);

                break;
            case R.id.btn_ok://确定
                String sort = "";//排序参数
                String buildSizeBegin = "";//建筑面积开始
                String buildSizeEnd = "";//建筑面积结束
                String feature = "";//特色
                String onlyLook = "";//一手二手
//                for (int i = 0; i < resultBean.paramList.size(); i++) {
//                    switch (i) {
//                        case 0:
//                            for (int j = 0; j < resultBean.paramList.get(0).childList.size(); j++) {
//                                if (resultBean.paramList.get(0).childList.get(j).isSelect)
//                                    sort = resultBean.paramList.get(0).childList.get(j).value;
//                            }
//                            break;
//                        case 1:
//                            for (int j = 0; j < resultBean.paramList.get(1).childList.size(); j++) {
//                                if (resultBean.paramList.get(1).childList.get(j).isSelect) {
//                                    buildSizeBegin = resultBean.paramList.get(1).childList.get(j).minValue;
//                                    buildSizeEnd = resultBean.paramList.get(1).childList.get(j).maxValue;
//                                }
//
//                            }
//                            break;
//                        case 2:
//                            for (int j = 0; j < resultBean.paramList.get(2).childList.size(); j++) {
//                                if (resultBean.paramList.get(2).childList.get(j).isSelect) {
//                                    feature = resultBean.paramList.get(2).childList.get(j).value + "," + feature;
//                                }
//                            }
//                            if (!TextUtils.isEmpty(feature) && feature.length() > 1)
//                                feature = feature.substring(0, feature.length() - 1);
//                            break;
//                        case 3:
//                            for (int j = 0; j < resultBean.paramList.get(3).childList.size(); j++) {
//                                if (resultBean.paramList.get(1).childList.get(j).isSelect) {
//                                    onlyLook = resultBean.paramList.get(3).childList.get(j).value;
//                                }
//
//                            }
//                            break;
//                    }
//                }

                sort = resultBean.paramList.get(0).childList.get(Integer.parseInt(selectMap.get(0))).value;
                buildSizeBegin = resultBean.paramList.get(1).childList.get(Integer.parseInt(selectMap.get(1))).minValue;
                buildSizeEnd = resultBean.paramList.get(1).childList.get(Integer.parseInt(selectMap.get(1))).maxValue;

                for (int i = 0; i < resultBean.paramList.get(2).childList.size(); i++) {
                    if (resultBean.paramList.get(2).childList.get(i).isSelect) {
                        feature = resultBean.paramList.get(2).childList.get(i).value + "," + feature;
                    }
                }
                if (!TextUtils.isEmpty(feature) && feature.length() > 1)
                    feature = feature.substring(0, feature.length() - 1);

                onlyLook = resultBean.paramList.get(3).childList.get(Integer.parseInt(selectMap.get(3))).value;

                Intent intent = new Intent();
                intent.putExtra("sort", sort);
                intent.putExtra("buildSizeBegin", buildSizeBegin);
                intent.putExtra("buildSizeEnd", buildSizeEnd);
                intent.putExtra("feature", feature);
                intent.putExtra("onlyLook", onlyLook);
                setResult(100, intent);
                finish();
                break;
        }
    }

    private int parentPos;

    public void setParamListBean(final List<ParamListBean> beanList) {

        if (beanList == null || beanList.isEmpty())
            return;

        adapter.setItems(beanList);
        adapter.notifyDataSetChanged();
        setSelectDatas(beanList, 0);

        lv_location.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parentPos = position;
                setSelectDatas(beanList, position);
            }
        });
        lv_location2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if ("特色".equals(beanList.get(parentPos).name)) {//多选

                    if (position == 0) {//不限
                        adapter2.getItems().get(position).isSelect = true;
                        selectMap.put(parentPos, "" + position);
                        for (int i = 1; i < adapter2.getItems().size(); i++) {
                            adapter2.getItems().get(i).isSelect = false;
                        }

                    } else {
                        adapter2.getItems().get(0).isSelect = false;//不限
                        if (adapter2.getItems().get(position).isSelect)
                            adapter2.getItems().get(position).isSelect = false;
                        else {
                            adapter2.getItems().get(position).isSelect = true;
                        }
                    }

                } else {//单选
                    for (int i = 0; i < adapter2.getItems().size(); i++) {
                        if (i == position) {
                            adapter2.getItems().get(i).isSelect = true;
                            selectMap.put(parentPos, "" + position);
                        } else
                            adapter2.getItems().get(i).isSelect = false;
                    }
                }

                adapter2.notifyDataSetChanged();

            }
        });
    }

    public void setSelectDatas(List<ParamListBean> beanList, int position) {
        //更新一级列表选中状态
        adapter.setItems(beanList, position);
        adapter.notifyDataSetChanged();

        //二级列表
        ParamListBean bean = beanList.get(position);
        List<ParamListBean> childList = bean.childList;
        adapter2.setItems(childList);
        adapter2.notifyDataSetChanged();

    }
}
