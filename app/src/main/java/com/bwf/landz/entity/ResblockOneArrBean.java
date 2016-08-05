package com.bwf.landz.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ma on 2016/8/4.
 * 一手楼盘集合
 */
public class ResblockOneArrBean implements Parcelable {
    /* 楼盘名称 */
    public String resblockOneName;
    /* 楼盘ID */
    public String prodelId;
    /* 图片名称(标题图) */
    public String imgUrl;
    /* 建筑类型 */
    public String resblockType;
    /* 户型区间开始 */
    public String apartmentBegin;
    /* 户型区间结束 */
    public String apartmentEndl;
    /* 单价区间开始 */
    public String unitprBegin;
    /* 单价区间结束 */
    public String getUnitprEnd;
    /* 总价区间开始 */
    public String totalprBegin;
    /* 总价区间结束 */
    public String getTotalprEnd;
    /* 面积开始 */
    public String areaBegin;
    /* 面积结束 */
    public String getAreaEnd;
    public String sortNum;

    @Override
    public String toString() {
        return "ResblockOneArrBean{" +
                "resblockOneName='" + resblockOneName + '\'' +
                ", prodelId='" + prodelId + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", resblockType='" + resblockType + '\'' +
                ", apartmentBegin='" + apartmentBegin + '\'' +
                ", apartmentEndl='" + apartmentEndl + '\'' +
                ", unitprBegin='" + unitprBegin + '\'' +
                ", getUnitprEnd='" + getUnitprEnd + '\'' +
                ", totalprBegin='" + totalprBegin + '\'' +
                ", getTotalprEnd='" + getTotalprEnd + '\'' +
                ", areaBegin='" + areaBegin + '\'' +
                ", getAreaEnd='" + getAreaEnd + '\'' +
                ", sortNum='" + sortNum + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resblockOneName);
        dest.writeString(this.prodelId);
        dest.writeString(this.imgUrl);
        dest.writeString(this.resblockType);
        dest.writeString(this.apartmentBegin);
        dest.writeString(this.apartmentEndl);
        dest.writeString(this.unitprBegin);
        dest.writeString(this.getUnitprEnd);
        dest.writeString(this.totalprBegin);
        dest.writeString(this.getTotalprEnd);
        dest.writeString(this.areaBegin);
        dest.writeString(this.getAreaEnd);
        dest.writeString(this.sortNum);
    }
}
