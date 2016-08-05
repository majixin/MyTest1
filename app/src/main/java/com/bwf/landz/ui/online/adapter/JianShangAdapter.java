package com.bwf.landz.ui.online.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.landz.R;
import com.bwf.landz.entity.ParamListBean;

import java.util.List;

/**
 * Created by ma on 2016/8/3.
 * 楼房鉴赏Adapter
 */
public class JianShangAdapter extends BaseAdapter {

    private List<ParamListBean> items ;

    private Context context ;

    private List<Object> totalList;

    public JianShangAdapter(Context context) {
        this.context = context;
    }

    public JianShangAdapter(Context context, List<ParamListBean> items) {
        this.context = context;
        this.items = items;
    }

    public void setTotalList(List<Object> totalList){
        this.totalList = totalList;
    }

    public void setItems(List<ParamListBean> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public Object getItem(int position) {
        return items == null ? null : items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHodler viewHodler = null ;
        if(convertView == null){
            viewHodler = new ViewHodler();
            convertView = View.inflate(context, R.layout.item_location,null);
            viewHodler.tv_item_location = (TextView) convertView.findViewById(R.id.tv_item_location);
            viewHodler.ll_location = (LinearLayout) convertView.findViewById(R.id.ll_location);
            viewHodler.img_nike = (ImageView) convertView.findViewById(R.id.img_nike);
            convertView.setTag(viewHodler);
        }else{
            viewHodler = (ViewHodler) convertView.getTag();
        }

        ParamListBean bean = items.get(position);
        viewHodler.tv_item_location.setText(bean.name);

        if(bean.isSelect){   //二级列表
            viewHodler.ll_location.setBackgroundColor(Color.parseColor("#EEEEEE"));
            if(bean.isSelect){
                viewHodler.tv_item_location.setTextColor(Color.parseColor("#4A245D"));
            }else{
                viewHodler.tv_item_location.setTextColor(Color.BLACK);
                viewHodler.img_nike.setVisibility(View.GONE);
            }
        }else{
            if(bean.isSelect){
                viewHodler.ll_location.setBackgroundColor(Color.parseColor("#EEEEEE"));
                viewHodler.tv_item_location.setTextColor(Color.parseColor("#4A245D"));
            }else{
                viewHodler.ll_location.setBackgroundColor(Color.WHITE);
                viewHodler.tv_item_location.setTextColor(Color.BLACK);
            }
        }

        return convertView;
    }

    private class ViewHodler{
        LinearLayout ll_location ;
        TextView tv_item_location ;
        ImageView img_nike ;
    }
}
