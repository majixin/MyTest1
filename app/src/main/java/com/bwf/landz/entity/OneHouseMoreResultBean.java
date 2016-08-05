package com.bwf.landz.entity;

import com.bwf.framwork.base.BaseBean;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/7/28 0028.
 * Description: 更多一手房推荐bean
 */
public class OneHouseMoreResultBean extends BaseBean {

    public List<HouseOneArrBean> result;

    @Override
    public String toString() {
        return "OneHouseMoreResultBean{" +
                "result=" + result +
                '}';
    }
}
