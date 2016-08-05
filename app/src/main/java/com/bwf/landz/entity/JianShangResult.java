package com.bwf.landz.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ma on 2016/8/4.
 */
public class JianShangResult {

    public JianShangResultBean result ;

    /**
     * 合并两种集合
     * @param resultBean
     * @param totalList
     */
    public void initListData(JianShangResult resultBean , List<Object> totalList){
       if(resultBean != null){
           List<ResblockArrBean> resblockArr = resultBean.result.resblockArr;
           List<ResblockOneArrBean> resblockOneArr = resultBean.result.resblockOneArr;

           List<Object> newtotalList = new ArrayList<>();
           //合并两种类型的集合
           if(resblockArr != null && !resblockArr.isEmpty()){
               newtotalList.addAll(resblockArr);
           }
           if(resblockOneArr != null && !resblockOneArr.isEmpty()){
               newtotalList.addAll(resblockOneArr);
           }

           Collections.sort(newtotalList, new Comparator<Object>() {

               @Override
               public int compare(Object o1, Object o2) {

                   int a,b = 0 ;
                   if(o1 instanceof ResblockArrBean){
                       a = Integer.parseInt(((ResblockArrBean) o1).sortNum);
                   }else{
                       a = Integer.parseInt(((ResblockOneArrBean)o1).sortNum);
                   }
                   if(o2 instanceof ResblockArrBean){
                       b = Integer.parseInt(((ResblockArrBean) o2).sortNum);
                   }else{
                       b = Integer.parseInt(((ResblockOneArrBean) o2).sortNum);
                   }
                   if(a > b){
                       return 1 ;
                   }
                   if(a < b ){
                       return -1 ;
                   }

                   return 0;
               }
           });
           totalList.addAll(newtotalList);
       }

    }

    @Override
    public String toString() {
        return "JianShangResult{" +
                "result=" + result +
                '}';
    }
}
