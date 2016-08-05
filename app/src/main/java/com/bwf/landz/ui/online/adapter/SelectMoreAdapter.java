package com.bwf.landz.ui.online.adapter;

import android.content.Context;
import android.graphics.Color;
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
 * Created by Lizhangfeng on 2016/7/25 0025.
 * Description:
 */
public class SelectMoreAdapter extends BaseAdapter {

    private List<ParamListBean> items;

    private Context context;

    private boolean isSecond;//是否是二级列表

    private int selectPos = 0;//选中item位置

    public SelectMoreAdapter(Context context, boolean isSecond) {
        this.context = context;
        this.isSecond = isSecond;
    }

    public void setItems(List<ParamListBean> items) {
        this.items = items;
    }

    public void setItems(List<ParamListBean> items, int selectPos) {
        this.items = items;
        this.selectPos = selectPos;
    }

    public List<ParamListBean> getItems() {
        return items;
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

        ViewHodler viewHodler = null;
        if (convertView == null) {
            viewHodler = new ViewHodler();
            convertView = View.inflate(context, R.layout.item_location, null);
            viewHodler.tv_item_location = (TextView) convertView.findViewById(R.id.tv_item_location);
            viewHodler.ll_location = (LinearLayout) convertView.findViewById(R.id.ll_location);
            viewHodler.img_nike = (ImageView) convertView.findViewById(R.id.img_nike);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }

        ParamListBean bean = items.get(position);
        viewHodler.tv_item_location.setText(bean.name);

        if (isSecond) {//二级列表
            viewHodler.ll_location.setBackgroundColor(Color.parseColor("#EEEEEE"));
            if (bean.isSelect) {
                viewHodler.tv_item_location.setTextColor(Color.parseColor("#4A245D"));
                viewHodler.img_nike.setVisibility(View.VISIBLE);

            } else {
                viewHodler.tv_item_location.setTextColor(Color.BLACK);
                viewHodler.img_nike.setVisibility(View.GONE);

            }
        } else {
            if (position == selectPos) {
                viewHodler.ll_location.setBackgroundColor(Color.parseColor("#EEEEEE"));
                viewHodler.tv_item_location.setTextColor(Color.parseColor("#4A245D"));
            } else {
                viewHodler.ll_location.setBackgroundColor(Color.WHITE);
                viewHodler.tv_item_location.setTextColor(Color.BLACK);
            }
        }

        return convertView;
    }

    private class ViewHodler {
        LinearLayout ll_location;
        TextView tv_item_location;
        ImageView img_nike;
    }


}
