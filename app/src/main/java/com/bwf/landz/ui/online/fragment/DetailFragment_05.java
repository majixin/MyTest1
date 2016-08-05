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
import com.bwf.framwork.image.ImageLoader2;
import com.bwf.landz.R;
import com.bwf.landz.ui.online.HouseOneDetailActivity;

public class DetailFragment_05 extends BaseFragment {

    private ImageView img_other_house ;

    private TextView tv_other_house ;

    private String other_house ;

    private OnShowPopListener onShowPopListener ;

    public void setOther_house(String other_house) {
        this.other_house = other_house;
        ImageLoader2.getInstance().displayImg(other_house,img_other_house);
    }


    public void setDesc(String desc) {
        tv_other_house.setText(desc);
    }

    @Override
    protected int getResource() {
        return R.layout.fragment_detail_fragment_05;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        img_other_house = findViewByIdNoCast(R.id.img_other_house);
        tv_other_house = findViewByIdNoCast(R.id.tv_other_house);
    }

    @Override
    protected void initData() {
        setOnClick(R.id.img_other_house);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_other_house:
                if(onShowPopListener != null){
                    onShowPopListener.showPop(other_house);
                }
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        if(context != null){
            if(context instanceof HouseOneDetailActivity){
                onShowPopListener = (OnShowPopListener) context;
            }
        }
        super.onAttach(context);
    }

    public interface OnShowPopListener {
        void showPop(String url);
    }
}
