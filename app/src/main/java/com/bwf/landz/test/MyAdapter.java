package com.bwf.landz.test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwf.landz.R;

/**
 * Created by Lizhangfeng on 2016/7/14 0014.
 * Description:
 */
public class MyAdapter extends BaseAdapter {

    private Context context;

    private ClickListener clickListener;

    public MyAdapter(Context context,ClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.activity_main,null);
//            viewHolder.tv_test = (TextView) convertView.findViewById(R.id.tv_helle);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        viewHolder.tv_test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (clickListener != null){
//                    clickListener.click(v);
//                }
//            }
//        });

//        viewHolder.tv_test.post(new Runnable() {
//            @Override
//            public void run() {
//                //主线程
//            }
//        });

        return convertView;
    }

    public class ViewHolder{
        TextView tv_test;
    }

    public interface ClickListener{
        void click(View view);
    }

}
