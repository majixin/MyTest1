package com.bwf.landz.entity;

import com.bwf.framwork.base.BaseBean;

/**
 * Created by Lizhangfeng on 2016/7/26 0026.
 * Description: 一手房详情返回参数
 */
public class HouseDetailResultBean extends BaseBean {
    public HouseDetailBean result;

    @Override
    public String toString() {
        return "HouseDetailResultBean{" +
                "result=" + result +
                '}';
    }
}
