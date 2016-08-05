package com.bwf.landz.ui.online.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.landz.R;
import com.bwf.landz.entity.ImgUrlArrBean;
import com.bwf.landz.ui.online.LookPhotoActivity;
import com.bwf.landz.ui.online.adapter.ImagePagerAdapter;

import java.util.List;

/**
 * ViewPager 展示
 */
public class DetailFragment_1 extends BaseFragment implements Handler.Callback {

    private static final int MSG_DELAY = 3000;//延迟发送s

    private ViewPager viewPager;

    public List<ImgUrlArrBean> imgUrlArr;//展示图集合

    private ImagePagerAdapter imagePagerAdapter;

    private Handler mHandler;

    private int currentItem;

    private boolean canAutoScroll;//手动和自动冲突的boolean

    private boolean isOnCreate;//

    private boolean canAuto;//是否可以自动滚动

    private OnPageSelectListener onPageSelectListener ;

    public void setImgUrlArr(List<ImgUrlArrBean> imgUrlArr) {
        this.imgUrlArr = imgUrlArr;
        imagePagerAdapter = new ImagePagerAdapter(getActivity(), imgUrlArr);
        viewPager.setAdapter(imagePagerAdapter);
        mHandler.sendEmptyMessageDelayed(1, MSG_DELAY);
        canAuto = true;
    }

    public void setImgUrlArr(List<ImgUrlArrBean> imgUrlArr, boolean canAuto,boolean canDismiss) {
        this.imgUrlArr = imgUrlArr;
        this.canAuto = canAuto;
        imagePagerAdapter = new ImagePagerAdapter(getActivity(), imgUrlArr);
        imagePagerAdapter.setClickDismiss(canDismiss);
        viewPager.setAdapter(imagePagerAdapter);
        if (canAuto)
            mHandler.sendEmptyMessageDelayed(1, MSG_DELAY);

    }

    @Override
    protected int getResource() {
        return R.layout.fragment_detail_fragment_1;
    }

    @Override
    protected void beforeInitView() {
        isOnCreate = true;
        mHandler = new Handler(this);
        //getChildFragmentManager() fragment中获取FragmentManager
    }

    @Override
    protected void initView(View rootView) {
        viewPager = findViewByIdNoCast(R.id.viewPager);
    }

    @Override
    protected void initData() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;

                if(onPageSelectListener != null){
                    onPageSelectListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if (!canAuto)//不能自动播放了
                    return;

                if (state == ViewPager.SCROLL_STATE_IDLE) {//滑动停止
                    if (canAutoScroll) {
                        canAutoScroll = false;
                        mHandler.sendEmptyMessageDelayed(1, MSG_DELAY);
                    }

                }
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {//viewpager 拽拉
                    canAutoScroll = true;
                    mHandler.sendEmptyMessage(2);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1://切换ViewPager页面
//                mHandler.removeMessages(1);
                currentItem++;
                viewPager.setCurrentItem(currentItem);
                mHandler.sendEmptyMessageDelayed(1, MSG_DELAY);
                break;
            case 2://自动播放停止
                mHandler.removeMessages(1);
                break;
        }
        return false;
    }

    @Override
    public void onPause() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        super.onPause();
    }

    @Override
    public void onResume() {

        if (isOnCreate) {
            isOnCreate = !isOnCreate;
        } else {
            if (canAuto) {
                if (mHandler != null)
                    mHandler.sendEmptyMessageDelayed(1, MSG_DELAY);
            }
        }

        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        if(context != null && context instanceof LookPhotoActivity){
            onPageSelectListener = (OnPageSelectListener) context;
        }
        super.onAttach(context);
    }

    public interface OnPageSelectListener{
        void onPageSelected(int position);
    }

    /**
     * viewPager滚动到指定位置
     * @param currentItem
     */
    public void setCurrentItem(int currentItem){
        viewPager.setCurrentItem(currentItem);
    }
}
