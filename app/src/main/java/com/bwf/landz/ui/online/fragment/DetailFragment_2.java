package com.bwf.landz.ui.online.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.framwork.image.ImageLoader;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.landz.R;
import com.bwf.landz.entity.HouseDetailResultBean;
import com.bwf.landz.ui.online.LookPhotoActivity;

/**
 * 房源描述
 */
public class DetailFragment_2 extends BaseFragment {

    private TextView tv_house_desc;

    private int lineCount;//行数

    private ImageView img_down, img_titlePic;

    private HouseDetailResultBean resultBean;

    private ImageLoader imageLoader;

    public void setResultBean(HouseDetailResultBean resultBean, ImageLoader imageLoader) {
        this.resultBean = resultBean;
        this.imageLoader = imageLoader;
        initData();
    }

    @Override
    protected int getResource() {
        return R.layout.fragment_detail_fragment_2;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        tv_house_desc = findViewByIdNoCast(R.id.tv_house_desc);
        img_down = findViewByIdNoCast(R.id.img_down);
        img_titlePic = findViewByIdNoCast(R.id.img_titlePic);
        setOnClick(R.id.img_down);
    }

    @Override
    protected void initData() {
        setOnClick(R.id.img_titlePic);
        if (resultBean != null) {
            imageLoader.displayImg(resultBean.result.titlepicImg, img_titlePic);
            tv_house_desc.setText("" + resultBean.result.roomDescripe);
            tv_house_desc.postDelayed(new Runnable() {
                @Override
                public void run() {
                    lineCount = tv_house_desc.getLineCount();
                    tv_house_desc.setLines(2);
                }
            }, 100);
        }

    }

    private boolean isUp;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_down://
                if (isUp) {//从展开点击折叠
                    isUp = false;
                    tv_house_desc.setLines(2);
                    img_down.setImageResource(R.mipmap.first_down);
                } else {//从折叠到展开
                    isUp = true;
                    tv_house_desc.setLines(lineCount);
                    img_down.setImageResource(R.mipmap.content_up);
                }
                break;
            case R.id.img_titlePic://点击查看预览图
                IntentUtils.openActivity(getContext(), LookPhotoActivity.class);
            break;
        }
    }
}
