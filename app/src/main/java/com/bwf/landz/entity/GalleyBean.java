package com.bwf.landz.entity;

/**
 * Created by ma on 2016/8/1.
 */
public class GalleyBean {
    public int pos;

    public String picType;

    public String typeName;

    public GalleyBean(int pos, String picType, String typeName) {
        this.pos = pos;
        this.picType = picType;
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "GalleyBean{" +
                "pos=" + pos +
                ", picType='" + picType + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
