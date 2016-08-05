package com.bwf.landz.ui.online.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwf.framwork.image.ImageLoader;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.landz.R;
import com.bwf.landz.entity.ImgUrlArrBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lizhangfeng on 2016/7/26 0026.
 * Description: 展示图adapter
 */
public class ImagePagerAdapter extends PagerAdapter {

    private Activity activity;

    public List<ImgUrlArrBean> imgUrlArr;

    private List<ImageView> imageViews;

    private ImageLoader imageLoader;

    private boolean clickDismiss;

    public ImagePagerAdapter(Activity activity, List<ImgUrlArrBean> imgUrlArr) {
        this.activity = activity;
        this.imgUrlArr = imgUrlArr;
        imageViews = new ArrayList<>();
        imageLoader = new ImageLoader();
        if (imgUrlArr != null && !imgUrlArr.isEmpty()) {
            for (ImgUrlArrBean imgUrlArrBean : imgUrlArr) {
                ImageView imageView = new ImageView(activity);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageViews.add(imageView);
            }
        }

    }

    public void setClickDismiss(boolean clickDismiss) {
        this.clickDismiss = clickDismiss;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        boolean flag = (view == object);
        return flag;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position % imageViews.size()));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = imageViews.get(position % imageViews.size());
        imageView.setImageResource(R.mipmap.defult_onepic);
        imageLoader.displayImg(imgUrlArr.get(position % imageViews.size()).picName, imageView);
        if (imageView.getParent() == null)//没有被其他view addVIew过,如果被其他addView过再被container addView会报错
            container.addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickDismiss){
                    if (activity != null && !activity.isFinishing()) {
                        activity.finish();
                    }
                }

            }
        });

        return imageView;
    }
}
