package com.bwf.landz.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ma on 2016/8/4.
 * 二手楼盘集合
 */
public class ResblockArrBean implements Parcelable {

    /* 楼盘名称 */
    public String resblockName;
    /* 楼盘ID */
    public String resblockId;
    /* 图片路径(标题图) */
    public String imgUrl;
    /* 商圈名称 */
    public String circleTypeName;
    /* 均价 */
    public String avergPrice;
    public String sortNum;

    @Override
    public String toString() {
        return "ResblockArrBean{" +
                "resblockName='" + resblockName + '\'' +
                ", resblockId='" + resblockId + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", circleTypeName='" + circleTypeName + '\'' +
                ", avergPrice='" + avergPrice + '\'' +
                ", sortNum='" + sortNum + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resblockName);
        dest.writeString(this.resblockId);
        dest.writeString(this.imgUrl);
        dest.writeString(this.circleTypeName);
        dest.writeString(this.avergPrice);
        dest.writeString(this.sortNum);
    }
}
