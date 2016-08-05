package com.bwf.landz.ui.search.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import com.bwf.landz.R;
import com.bwf.landz.entity.SearchBean;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/7/25 0025.
 * Description:搜索
 */
public class SearchAdapter extends BaseAdapter {

    private Context context;

    public List<SearchBean> result;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setResult(List<SearchBean> result) {
        this.result = result;
    }

    @Override
    public int getCount() {
        return result == null ? 0 : result.size();
    }

    @Override
    public Object getItem(int position) {
        return result == null ? null : result.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_search, null);
            viewHolder.tv_search_content = (TextView) convertView.findViewById(R.id.tv_search_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (result.get(position) != null)
            viewHolder.tv_search_content.setText(result.get(position).name);

        return convertView;
    }

    private class ViewHolder {
        TextView tv_search_content;
    }

}
