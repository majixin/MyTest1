package com.bwf.landz.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bwf.framwork.image.ImageLoader2;
import com.bwf.landz.R;

/**
 * Created by ma on 2016/8/1.
 */
public class OhtherHousePopwindow extends PopupWindow {

    public OhtherHousePopwindow(Context context , String url) {
        super(context);
        init(context,url);
    }

    public void init(Context context , String url){
        View view = View.inflate(context, R.layout.pop_other_house,null);
        View view_pop = view.findViewById(R.id.view_pop);
        ImageView img_pop_other_house = (ImageView) view.findViewById(R.id.img_pop_other_house);
        ImageLoader2.getInstance().displayImg(url,img_pop_other_house);
        //popwindow相关设置
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setAnimationStyle(R.style.PopupAnimation);//设置Popwindow动画
        view_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OhtherHousePopwindow.this.dismiss();
            }
        });
    }

    /**
     * 显示Popwindow
     * @param view
     */
    public void showPopWindow(View view){
        if(!isShowing()){
            this.showAsDropDown(view);//显示在view的下方
        }
    }

}
