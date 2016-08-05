package com.bwf.landz.ui.online.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.framwork.image.ImageLoader;
import com.bwf.framwork.image.ImageLoader2;
import com.bwf.framwork.utils.DisplayUtil;
import com.bwf.landz.R;
import com.bwf.landz.entity.ImgUrlArrBean;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/7/29 0029.
 * Description: 查看预览图adapter
 */
public class LookPhotoAdapter extends RecyclerView.Adapter<LookPhotoAdapter.ViewHodler> {

    private Context context;

    private List<ImgUrlArrBean> imgUrlArrBeen;

    public LookPhotoAdapter(Context context, List<ImgUrlArrBean> imgUrlArrBeen) {
        this.context = context;
        this.imgUrlArrBeen = imgUrlArrBeen;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {//View


        View view = View.inflate(context, R.layout.item_look_photo, null);
        ViewHodler viewHodler = new ViewHodler(view);
        viewHodler.img_photo = (ImageView) view.findViewById(R.id.img_photo);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {//数据逻辑层
        holder.img_photo.setImageResource(R.mipmap.defult_twopic);
        if (imgUrlArrBeen.get(position) != null)
            ImageLoader2.getInstance().displayImg(imgUrlArrBeen.get(position).picName, holder.img_photo);

    }

    @Override
    public int getItemCount() {
        return imgUrlArrBeen == null ? 0 : imgUrlArrBeen.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {

        ImageView img_photo;

        public ViewHodler(View itemView) {
            super(itemView);
        }
    }

}
