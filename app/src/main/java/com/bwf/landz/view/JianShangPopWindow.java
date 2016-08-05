package com.bwf.landz.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.bwf.landz.R;
import com.bwf.landz.entity.ParamListBean;
import com.bwf.landz.ui.online.adapter.JianShangAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 2016/8/3.
 */
public class JianShangPopWindow extends PopupWindow {

    private JianShangAdapter adapter,adapter2 ;

    private ListView lv_location,lv_location2;

    private boolean isOneList;//是否是一个列表

    private View view ;

    public JianShangPopWindow(Context context,boolean isOneList) {
        super(context);
        this.isOneList = isOneList ;
        init(context);
    }

    public void init(Context context){

        View view = View.inflate(context,R.layout.pop_location,null);
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setFocusable(true);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        lv_location = (ListView) view.findViewById(R.id.lv_location);
        lv_location2 = (ListView) view.findViewById(R.id.lv_location2);
        this.view = view.findViewById(R.id.view);
        adapter = new JianShangAdapter(context);
        adapter2 = new JianShangAdapter(context);

        lv_location.setAdapter(adapter);
        lv_location2.setAdapter(adapter2);

        if(isOneList){
            lv_location2.setVisibility(View.GONE);
        }else{
            lv_location2.setVisibility(View.VISIBLE);
            adapter2 = new JianShangAdapter(context);
            lv_location2.setAdapter(adapter2);
        }
        this.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShowing()){
                    dismiss();
                }
            }
        });
    }

    /**
     * 放ParamListBean数据
     * @param beanlist
     */
    public void setParamListBean(final List<ParamListBean> beanlist){

        if(beanlist == null || beanlist.isEmpty()){
            return ;
        }

        adapter.setItems(beanlist);
        adapter.notifyDataSetChanged();

        lv_location.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for(int i = 0 ; i < beanlist.size() ; i++){
                    if(i == position){
                        beanlist.get(i).isSelect = true ;
                    }else{
                        beanlist.get(i).isSelect = false ;
                    }
                }
                adapter.notifyDataSetChanged();

                ParamListBean bean = beanlist.get(position);
                List<ParamListBean> childList =bean.childList;
                adapter2.setItems(childList);
                adapter2.notifyDataSetChanged();
            }
        });
    }

    public void showPopWindow(View view){
        if(!isShowing()){
            this.showAsDropDown(view);
        }
    }

}
