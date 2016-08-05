package com.bwf.landz.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.bwf.framwork.Constants;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ma on 2016/8/3.
 */
public class JianShangBean{
    /* 楼盘ID */
    public String resblockId;
    /* 区域编码 */
    public String areaCode;
    /* 商圈ID */
    public String circleTypeCode;
    /* 均价开始(一手单价、二手均价) */
    public String avgPriceBegin;
    /* 均价结束(一手单价、二手均价) */
    public String avgPriceEnd;
    /* 1均价从高到低(降序) 2均价从低到高(升序) */
    public String avgPriceSort;
    /* 是否优惠:1优惠 2不优惠(二手) */
    public String isFavorable;
    /* 只看楼盘:1一手 2二手 */
    public String onlyLook;
    /* 经度 */
    public String longitude;
    /* 纬度 */
    public String latitude;
    /* 房型 */
    public String resblockType;
    /* 房间数 */
    public String roomNumber;

    public int pageNo;

    public int pageSize = Constants.PAGE_SIZE;

    public Map<String , String > getJsMap(){
        Map<String , String> map = new HashMap<>();
        map.put("pageNo",""+pageNo);
        map.put("pageSize",""+pageSize);

        if(!TextUtils.isEmpty(resblockId)){
            map.put("resblockId",""+resblockId);
        }
        if(!TextUtils.isEmpty(areaCode)){
            map.put("areaCode",""+areaCode);
        }
        if(!TextUtils.isEmpty(circleTypeCode)){
            map.put("circleTypeCode",""+circleTypeCode);
        }
        if(!TextUtils.isEmpty(avgPriceBegin)){
            map.put("avgPriceBegin",""+avgPriceBegin);
        }
        if(!TextUtils.isEmpty(avgPriceEnd)){
            map.put("avgPriceEnd",""+avgPriceEnd);
        }
        if(!TextUtils.isEmpty(avgPriceSort)){
            map.put("avgPriceSort",""+avgPriceSort);
        }
        if(!TextUtils.isEmpty(isFavorable)){
            map.put("isFavorable",""+isFavorable);
        }
        if(!TextUtils.isEmpty(onlyLook)){
            map.put("onlyLook",""+onlyLook);
        }
        if(!TextUtils.isEmpty(longitude)){
            map.put("longitude",""+longitude);
        }
        if(!TextUtils.isEmpty(latitude)){
            map.put("latitude",""+latitude);
        }
        if(!TextUtils.isEmpty(resblockType)){
            map.put("resblockType",""+resblockType);
        }
        if(!TextUtils.isEmpty(roomNumber)){
            map.put("roomNumber",""+roomNumber);
        }
        return map ;
    }
}
