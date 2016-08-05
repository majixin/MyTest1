package com.bwf.landz.entity;

import com.bwf.framwork.base.BaseBean;

import java.util.List;

/**
 * Created by ma on 2016/8/4.
 */
public class JianShangResultBean extends BaseBean {

    public List<ResblockArrBean> resblockArr ;
    public List<ResblockOneArrBean> resblockOneArr ;

    @Override
    public String toString() {
        return "JianShangResultBean{" +
                "resblockArr=" + resblockArr +
                ", resblockOne=" + resblockOneArr +
                '}';
    }
}
