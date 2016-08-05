package com.bwf.landz.ui.online;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.landz.MyApplication;
import com.bwf.landz.R;
import com.bwf.landz.entity.GalleyBean;
import com.bwf.landz.entity.ImgUrlArrBean;
import com.bwf.landz.ui.online.adapter.ImagePagerAdapter;
import com.bwf.landz.ui.online.adapter.LookPhotoAdapter;
import com.bwf.landz.ui.online.adapter.TextViewAdapter;
import com.bwf.landz.ui.online.fragment.DetailFragment_1;

import java.util.ArrayList;
import java.util.List;

/**
 * 点击查看预览图
 */
public class LookPhotoActivity extends BaseActivity implements DetailFragment_1.OnPageSelectListener {


    private DetailFragment_1 detail_fragment1;

    private List<ImgUrlArrBean> imgUrlArrBeen;

    private LinearLayout ll_list;//表格显示

    private ImageView img_list;

    private Gallery gallerg_str ;

    private TextViewAdapter adapter ;

    private List<GalleyBean> galleyBeen ;

    @Override
    public int getContentViewId() {
        return R.layout.activity_look_photo;
    }

    @Override
    public void beforeInitView() {
        galleyBeen = new ArrayList<>();
        imgUrlArrBeen = MyApplication.getApplication().getImgUrlArr();
    }

    @Override
    public void initView() {
        ll_list = findViewByIdNoCast(R.id.ll_list);
        img_list = findViewByIdNoCast(R.id.img_list);
        gallerg_str = findViewByIdNoCast(R.id.gallerg_str);
        detail_fragment1 = (DetailFragment_1) getSupportFragmentManager().findFragmentById(R.id.detail_fragment1);
    }

    @Override
    public void initData() {
        setOnClick(R.id.img_list);
        detail_fragment1.setImgUrlArr(imgUrlArrBeen, false,true);
        adapter = new TextViewAdapter(this);
        gallerg_str.setAdapter(adapter);
        gallerg_str.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addView(0);

        String last_type = imgUrlArrBeen.get(0).picType;

        for (int i = 0; i < imgUrlArrBeen.size(); i++) {//4,555,6
            if (!imgUrlArrBeen.get(i).picType.equals(last_type)) {
                last_type = imgUrlArrBeen.get(i).picType;
                addView(i);
            }
        }
        adapter.setDatas(galleyBeen);
        adapter.notifyDataSetChanged();
    }


    /**
     * 通过指定位置添加view
     *
     * @param pos
     */
    public void addView(int pos) {

        TextView textView = new TextView(this);
        textView.setPadding(10, 10, 10, 10);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(24);
        textView.setText(getTypeName(imgUrlArrBeen.get(0).picType));
        GalleyBean galleyBean1 = new GalleyBean(pos,imgUrlArrBeen.get(pos).picType,getTypeName(imgUrlArrBeen.get(pos).picType));
        galleyBeen.add(galleyBean1);
        ll_list.addView(textView);

        List<ImgUrlArrBean> new_imgs = new ArrayList<>();

        for (ImgUrlArrBean imgUrlArrBean : imgUrlArrBeen) {
            if (imgUrlArrBean.picType.equals(imgUrlArrBeen.get(pos).picType)) {
                new_imgs.add(imgUrlArrBean);
            }
        }

        RecyclerView recyclerView = new RecyclerView(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new LookPhotoAdapter(this, new_imgs));
        ll_list.addView(recyclerView);
    }

    public String getTypeName(String type) {
        if ("1".equals(type)) {
            return "外景图";
        }
        if ("2".equals(type)) {
            return "地理位置图";
        }
        if ("3".equals(type)) {
            return "座栋分布图";
        }
        if ("4".equals(type)) {
            return "户型图";
        }
        if ("5".equals(type)) {
            return "样板间";
        }
        if ("6".equals(type)) {
            return "实勘图";
        }
        return "未知";
    }

    @Override
    public void onClick(View v) {
        if (v == img_list) {
            img_list.setVisibility(View.GONE);
            findViewById(R.id.ll_fragment).setVisibility(View.GONE);
//            getSupportFragmentManager().beginTransaction().hide(detail_fragment1);//隐藏fragment
            ll_list.setVisibility(View.VISIBLE);
        }
    }

    public boolean isPageSelected , isItemSelect ;

    private int num ; //当前view循环次数


    @Override
    public void onPageSelected(int position) {//viewpager滑动的时候回调
        isPageSelected = true ;
        num = position / imgUrlArrBeen.size() ;

        if(!isItemSelect){
            ImgUrlArrBean imgUrlArrBean = imgUrlArrBeen.get(position % imgUrlArrBeen.size());
            String type = imgUrlArrBean.picType;
            for(int i = 0 ; i < galleyBeen.size() ; i ++ ){
                if(type.equals(galleyBeen.get(i).picType)){
                    gallerg_str.setSelection(i);//滚动到指定位置
                }
            }
        }
        gallerg_str.postDelayed(new Runnable() {
            @Override
            public void run() {
               isPageSelected = false ;
            }
        },100);
    }
}
