package com.bwf.landz.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.landz.R;

/**
 * Created by Lizhangfeng on 2016/7/29 0029.
 * Description:
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHodler> {

    private Context context;

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {//View

        View view = View.inflate(context, R.layout.item_guwen, null);

        ViewHodler viewHodler = new ViewHodler(view);

        return viewHodler;
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {//数据逻辑层
        holder.tv_test.setText("");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHodler extends RecyclerView.ViewHolder {

        TextView tv_test;

        public ViewHodler(View itemView) {
            super(itemView);
        }
    }

}
