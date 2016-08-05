package com.bwf.landz.ui.welcome;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.utils.DisplayUtil;
import com.bwf.landz.R;
import com.bwf.landz.ui.welcome.adapter.GuideAdapter;

/**
 * Created by lizhangfeng on 2016/7/7 0007.
 * Description: 首次进入app的引导页
 */
public class GuidActivity extends BaseActivity {

    private ViewPager viewPager;

    private GuideAdapter adapter;

    /* 绘制原点 */
    private ImageView cicle_01, cicle_02, cicle_03, splash_img;

    private ImageView[] circles;

    private Integer[] imgs = new Integer[]{R.mipmap.splash_a, R.mipmap.splash_b, R.mipmap.splash_c};

    private LinearLayout ll_circle;

    @Override
    public int getContentViewId() {
        return R.layout.activity_guide;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {
        viewPager = findViewByIdNoCast(R.id.viewPager);
        ll_circle = findViewByIdNoCast(R.id.ll_circle);
        splash_img = findViewByIdNoCast(R.id.splash_img);
        cicle_01 = findViewByIdNoCast(R.id.cicle_01);
        cicle_02 = findViewByIdNoCast(R.id.cicle_02);
        cicle_03 = findViewByIdNoCast(R.id.cicle_03);
    }

    @Override
    public void initData() {
        circles = new ImageView[]{cicle_01, cicle_02, cicle_03};
        //设置点和文字的位置
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ll_circle.getLayoutParams();
        params.width = DisplayUtil.getDensity_Width(this);
        ll_circle.setLayoutParams(params);

        //添加圆点
//        ImageView img = new ImageView(this);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.leftMargin = DisplayUtil.dip2px(this,10);
//        img.setLayoutParams(layoutParams);
//        ll_circle.addView(img);


        adapter = new GuideAdapter(getSupportFragmentManager());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCheck(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOffscreenPageLimit(3);//默认是2个view，setOffscreenPageLimit(3)创建3个view
        viewPager.setAdapter(adapter);
        setCheck(0);
    }

    /**
     * 设置选中的圆点
     *
     * @param position
     */
    public void setCheck(int position) {
        splash_img.setImageResource(imgs[position]);
        for (int i = 0; i < circles.length; i++) {
            if (position == i)
                circles[i].setImageResource(R.mipmap.checked_page);
            else
                circles[i].setImageResource(R.mipmap.unchecked_page);
        }
        splash_bg_anim();
    }

    public void splash_bg_anim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(splash_img, "translationX", 0, DisplayUtil.getDensity_Width(this) - getResources().getDimension(R.dimen.splash_a), 0);
        animator.setDuration(15000);
        animator.setRepeatCount(Integer.MAX_VALUE);
        animator.setRepeatMode(ObjectAnimator.INFINITE);
        animator.start();
    }


    @Override
    public void onClick(View v) {

    }
}
