package com.bwf.landz.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.bwf.framwork.utils.DisplayUtil;
import com.bwf.landz.R;
import com.bwf.landz.entity.ParamListBean;
import com.bwf.landz.ui.callback.PopwindowItemClickCallBack;
import com.bwf.landz.ui.online.adapter.LocationAdapter;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/7/15 0015.
 * Description:
 */
public class MyPopwindow extends PopupWindow {

    private LocationAdapter adapter, adapter2;

    private ListView lv_location, lv_location2;

    private View view;

    private boolean isOneList;//是否是一个列表

    private PopwindowItemClickCallBack callBack;

    public MyPopwindow(Context context, boolean isOneList) {
        super(context);
        this.isOneList = isOneList;
        init(context);
    }

    public void init(Context context) {
        View view = View.inflate(context, R.layout.pop_location, null);

        //popWindow相关设置
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(DisplayUtil.getDensity_Height(context));
        this.setFocusable(true);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.setBackgroundDrawable(new BitmapDrawable());

        lv_location = (ListView) view.findViewById(R.id.lv_location);
        lv_location2 = (ListView) view.findViewById(R.id.lv_location2);
        this.view = view.findViewById(R.id.view);
        adapter = new LocationAdapter(context, false);
        lv_location.setAdapter(adapter);
        //查看是否是一个列表
        if (isOneList) {
            lv_location2.setVisibility(View.GONE);
        } else {
            lv_location2.setVisibility(View.VISIBLE);
            adapter2 = new LocationAdapter(context, true);
            lv_location2.setAdapter(adapter2);
        }

        this.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowing()) {
                    dismiss();
                }
            }
        });

    }

    /**
     * 放ParamListBean数据
     *
     * @param beanList
     * @param isOneDismiss 点击 一级列表popwindow是否消
     */
    public void setParamListBean(final List<ParamListBean> beanList, final PopwindowItemClickCallBack callBack, final boolean isOneDismiss) {

        this.callBack = callBack;

        if (beanList == null || beanList.isEmpty())
            return;

        adapter.setItems(beanList);
        adapter.notifyDataSetChanged();
        setSelectDatas(beanList, 0);

        lv_location.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setSelectDatas(beanList, position);

                if (isOneDismiss) {
                    dismiss();
                    if (null != callBack)
                        callBack.onItemClick(beanList.get(position));
                }

            }
        });
        lv_location2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ParamListBean paramList = (ParamListBean) adapter2.getItem(position);

                if (null != callBack)
                    callBack.onItemClick(paramList);
                dismiss();//popwindow消失
            }
        });
    }

    public void setSelectDatas(List<ParamListBean> beanList, int position) {
        //更新一级列表选中状态
        for (int i = 0; i < beanList.size(); i++) {
            if (i == position)
                beanList.get(i).isSelect = true;
            else
                beanList.get(i).isSelect = false;
        }
        adapter.notifyDataSetChanged();

        if (!isOneList) {//二级列表
            ParamListBean bean = beanList.get(position);
            List<ParamListBean> childList = bean.childList;
            adapter2.setItems(childList);
            adapter2.notifyDataSetChanged();
        }
    }

    /**
     * 显示popwindow
     *
     * @param view
     */
    public void showPopWindow(View view) {
        if (!isShowing()) {
            this.showAsDropDown(view);//显示在view的下方
            // this.showAtLocation(view, Gravity.TOP, 0, 0);//可以显示在指定view的指定位置
        }
    }


}
