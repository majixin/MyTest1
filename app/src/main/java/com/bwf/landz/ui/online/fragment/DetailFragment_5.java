package com.bwf.landz.ui.online.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framwork.base.BaseFragment;
import com.bwf.framwork.utils.StringUtils;
import com.bwf.landz.R;
import com.bwf.landz.entity.HouseDetailBean;

/**
 * 楼盘概览
 */
public class DetailFragment_5 extends BaseFragment {

    private TextView tv_onedetail_zhuangxiu;
    private TextView tv_onedetail_zhuangxiumoney;
    private TextView tv_onedetail_zhuangxiuarea;
    private TextView tv_onedetail_jiaofangtime;
    private TextView tv_gone_weizhi, tv_gone_wuye, tv_gone_kaifashang, tv_gone_caizhi, tv_gone_cheweishu, tv_gone_jianzhufenge,
            tv_gone_loujianju, tv_gone_cainuan, tv_gone_gongnuan, tv_gone_wuyefei, tv_gone_area, tv_onedetail_floor,
            tv_onedetail_rongji, tv_onedetail_lvhua;

    private RelativeLayout rl_gone_text;//可隐藏的部分
    private RelativeLayout rl_gone_down;//控制显示与隐藏
    private ImageView jiantou;//指示箭头

    private HouseDetailBean result;

    public void setResult(HouseDetailBean result) {
        this.result = result;
        initData();
    }

    @Override
    protected int getResource() {
        return R.layout.fragment_detail_fragment_5;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        tv_onedetail_zhuangxiu = findViewByIdNoCast(R.id.tv_onedetail_zhuangxiu);
        tv_onedetail_zhuangxiumoney = findViewByIdNoCast(R.id.tv_onedetail_zhuangxiumoney);
        tv_onedetail_zhuangxiuarea = findViewByIdNoCast(R.id.tv_onedetail_zhuangxiuarea);
        tv_onedetail_jiaofangtime = findViewByIdNoCast(R.id.tv_onedetail_jiaofangtime);
        tv_gone_weizhi = findViewByIdNoCast(R.id.tv_gone_weizhi);
        tv_gone_wuye = findViewByIdNoCast(R.id.tv_gone_wuye);
        tv_gone_kaifashang = findViewByIdNoCast(R.id.tv_gone_kaifashang);
        tv_gone_caizhi = findViewByIdNoCast(R.id.tv_gone_caizhi);
        tv_gone_cheweishu = findViewByIdNoCast(R.id.tv_gone_cheweishu);
        tv_gone_jianzhufenge = findViewByIdNoCast(R.id.tv_gone_jianzhufenge);
        tv_gone_loujianju = findViewByIdNoCast(R.id.tv_gone_loujianju);
        tv_gone_cainuan = findViewByIdNoCast(R.id.tv_gone_cainuan);
        tv_gone_gongnuan = findViewByIdNoCast(R.id.tv_gone_gongnuan);
        tv_gone_wuyefei = findViewByIdNoCast(R.id.tv_gone_wuyefei);
        tv_gone_area = findViewByIdNoCast(R.id.tv_gone_area);
        tv_onedetail_floor = findViewByIdNoCast(R.id.tv_onedetail_floor);
        tv_onedetail_rongji = findViewByIdNoCast(R.id.tv_onedetail_rongji);
        tv_onedetail_lvhua = findViewByIdNoCast(R.id.tv_onedetail_lvhua);
        rl_gone_text = findViewByIdNoCast(R.id.rl_gone_text);
        rl_gone_down = findViewByIdNoCast(R.id.rl_gone_down);
        jiantou = findViewByIdNoCast(R.id.imageView3);
    }

    @Override
    protected void initData() {
        setOnClick(R.id.rl_gone_down);
        if (result != null) {
            //装修标准
            tv_onedetail_zhuangxiu.setText(result.decorationName);
            //单平方装修标准元
            tv_onedetail_zhuangxiumoney.setText(StringUtils.doubleFormat(result.metersPrice) + "元");
            tv_onedetail_zhuangxiuarea.setText(StringUtils.doubleFormat(result.covers) + "平米");
            tv_onedetail_jiaofangtime.setText(StringUtils.longToStrng(StringUtils.isEmpty(result.launchTime) ? 0 : Long.parseLong(result.launchTime), "yyyy-MM-dd"));
            tv_onedetail_rongji.setText(StringUtils.doubleFormat(result.volumeRate) + "%");
            tv_onedetail_lvhua.setText(StringUtils.doubleFormat(result.greeningRate) + "%");
            tv_onedetail_floor.setText(StringUtils.doubleFormat(result.storey) + "m");
            //建筑面积
            tv_gone_area.setText(StringUtils.doubleFormat(result.gfa) + "㎡");
            tv_gone_wuyefei.setText(StringUtils.doubleFormat(result.propertyCosts) + "元/月/㎡");
            tv_gone_gongnuan.setText(result.heating);
            tv_gone_cainuan.setText(result.heating1);
            //楼间距
            tv_gone_loujianju.setText(StringUtils.doubleFormat(result.floorSpace) + "m");
            //建筑风格
            tv_gone_jianzhufenge.setText(result.buildingType);
            tv_gone_cheweishu.setText(result.parkingNum + "个");
            //材质
            tv_gone_caizhi.setText(result.facadeMaterial);
            //开发商
            tv_gone_kaifashang.setText(result.developers);
            //物业
            tv_gone_wuye.setText(result.immobilien);
            //位置
            tv_gone_weizhi.setText(result.lage);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_gone_down:
                if (rl_gone_text.getVisibility() == View.GONE) {
                    rl_gone_text.setVisibility(View.VISIBLE);
                    jiantou.setBackgroundResource(R.mipmap.content_up);
                } else {
                    rl_gone_text.setVisibility(View.GONE);
                    jiantou.setBackgroundResource(R.mipmap.first_down);
                }
                break;
        }
    }
}
