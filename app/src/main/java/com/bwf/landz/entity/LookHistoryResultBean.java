package com.bwf.landz.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.bwf.framwork.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lizhangfeng on 2016/7/28 0028.
 * Description: 顾问看房列表
 */
public class LookHistoryResultBean extends BaseBean {

    public LookHistoryBean result;

    public class LookHistoryBean  {
        public List<GuWenResultBean.ShowArr> showArr;
        public String totalAmount;

        @Override
        public String toString() {
            return "LookHistoryBean{" +
                    "showArr=" + showArr +
                    ", totalAmount='" + totalAmount + '\'' +
                    '}';
        }


    }

    @Override
    public String toString() {
        return "LookHistoryResultBean{" +
                "result=" + result +
                '}';
    }
}
