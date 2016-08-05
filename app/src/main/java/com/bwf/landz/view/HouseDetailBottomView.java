package com.bwf.landz.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.framwork.image.ImageLoader;
import com.bwf.landz.R;
import com.bwf.landz.entity.GuWenResultBean;

/**
 * Created by Lizhangfeng on 2016/7/26 0026.
 * Description: 详情底部联系人部分
 */
public class HouseDetailBottomView extends LinearLayout implements View.OnClickListener {

    private Activity activity;

    private GuWenResultBean.ShowArr showArr;

    private ImageLoader imageLoader;

    private ImageView im_detail_photo, im_detail_phone;

    private TextView tv_huxing_name, tv_huxing_phone;

    public HouseDetailBottomView(Context context) {
        this(context, null);
    }

    public HouseDetailBottomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HouseDetailBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化View
     *
     * @param context
     */
    public void initView(Context context) {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        View view = View.inflate(context, R.layout.bottom_house_detail, null);
        view.setLayoutParams(layoutParams);
        addView(view);
        im_detail_photo = (ImageView) findViewById(R.id.im_detail_photo);
        tv_huxing_phone = (TextView) findViewById(R.id.tv_huxing_phone);
        tv_huxing_name = (TextView) findViewById(R.id.tv_huxing_name);
        im_detail_phone = (ImageView) findViewById(R.id.im_detail_phone);
        im_detail_phone.setOnClickListener(this);
    }

    public void setShowArr(Activity activity, GuWenResultBean.ShowArr showArr, ImageLoader imageLoader) {
        this.activity = activity;
        this.showArr = showArr;
        this.imageLoader = imageLoader;
        initData();
    }

    /**
     * 加载数据
     */
    public void initData() {
        if (showArr != null) {
            imageLoader.displayImg(showArr.photo, im_detail_photo);
            tv_huxing_name.setText(showArr.createName);
            tv_huxing_phone.setText(showArr.phone);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == im_detail_phone) {//拨号界面
            if (showArr != null) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + showArr.phone));
                activity.startActivity(intent);
            }

        }
    }
}
