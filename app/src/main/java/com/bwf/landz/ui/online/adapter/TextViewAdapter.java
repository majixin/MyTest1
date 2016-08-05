package com.bwf.landz.ui.online.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwf.landz.R;
import com.bwf.landz.entity.GalleyBean;

import java.util.List;

/**
 * Created by ma on 2016/8/1.
 * galley
 */
public class TextViewAdapter extends BaseAdapter {

    private Context context ;

    private List<GalleyBean> galleyBeen ;

    public TextViewAdapter(Context c) {
        context = c;
    }

    public void setDatas(List<GalleyBean> galleyBeen) {
        this.galleyBeen = galleyBeen;
    }
    @Override
    public int getCount() {
        if(galleyBeen != null){
            return galleyBeen.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return galleyBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.galley_text_item,null);
            TextView tv_house_typt = (TextView) convertView.findViewById(R.id.tv_house_type);
            tv_house_typt.setText(galleyBeen.get(position).typeName);
        }
        return convertView;
    }
}
