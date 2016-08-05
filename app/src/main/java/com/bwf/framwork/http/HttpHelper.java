package com.bwf.framwork.http;

import android.app.Activity;

import com.bwf.framwork.Constants;
import com.bwf.framwork.utils.StringUtils;
import com.bwf.framwork.utils.UrlUtils;
import com.bwf.landz.entity.JianShangBean;
import com.bwf.landz.request.OnLineHouseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lizhangfeng on 2016/7/13 0013.
 * Description:
 */
public class HttpHelper {

    public static void login(Activity activity, HttpRequestAsyncTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "landz");
        map.put("password", "123456");
        Request request = new Request("http://baidu.com", Request.Method.POST, map);
        HttpRequestAsyncTask httpRequestAsyncTask = new HttpRequestAsyncTask(activity, callBack);
        httpRequestAsyncTask.execute(request);
    }

    /**
     * 在线房源
     *
     * @param activity
     * @param callBack
     */
    public static void getOnLineHouseList(Activity activity, OnLineHouseRequest requestBean, HttpRequestAsyncTask.CallBack callBack) {

        Request request = new Request(UrlUtils.ONLINE_HOUSE, Request.Method.POST, requestBean == null ? null : requestBean.getRequestMap());
        HttpRequestAsyncTask httpRequestAsyncTask = new HttpRequestAsyncTask(activity, callBack);
        httpRequestAsyncTask.execute(request);
    }

    /**
     * 楼盘鉴赏
     */
    public static void getJianShang(Activity activity, JianShangBean jianShangBean, HttpRequestAsyncTask.CallBack callBack) {

        Request request = new Request(UrlUtils.JIANSHANG, Request.Method.GET, jianShangBean == null ? null : jianShangBean.getJsMap());
        HttpRequestAsyncTask httpRequestAsyncTask = new HttpRequestAsyncTask(activity, callBack);
        httpRequestAsyncTask.execute(request);
    }


    /**
     * 搜索
     *
     * @param content 搜索的内容
     * @param type    搜索类型
     */
    public static void getSearchDatas(Activity activity, String content, String type, HttpRequestAsyncTask.CallBack callBack) {

        Map<String, String> map = new HashMap<>();
        map.put("keyWords", content);
        map.put("type", "" + type);
        Request request = new Request(UrlUtils.SEARCH_URL, Request.Method.GET, map);
        HttpRequestAsyncTask httpRequestAsyncTask = new HttpRequestAsyncTask(activity, callBack);
        httpRequestAsyncTask.execute(request);
    }

    /**
     * 房子详情
     *
     * @param activity
     * @param houseOneId
     * @param callBack
     */
    public static void getDetail(Activity activity, String houseOneId, HttpRequestAsyncTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("houseOneId", houseOneId);
        Request request = new Request(UrlUtils.HOUSE_DETAIL, Request.Method.GET, map);
        HttpRequestAsyncTask httpRequestTask = new HttpRequestAsyncTask(activity, callBack);
        httpRequestTask.execute(request);
    }

    /**
     * 一手房看房记录_本房顾问列表
     *
     * @param houseOneId
     */
    public static void getOneDetailLook(Activity activity, String houseOneId, HttpRequestAsyncTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("houseOneId", houseOneId);
        Request request = new Request(UrlUtils.HOUSE_DETAIL_LOOK, Request.Method.GET, map);
        HttpRequestAsyncTask httpRequestTask = new HttpRequestAsyncTask(activity, callBack);
        httpRequestTask.execute(request);
    }

    /**
     * 更多一手房源推荐
     *
     * @param houseId    房源id
     * @param resblockId 楼盘ID
     */
    public static void getOneDetailMore(Activity activity, String houseId, String resblockId, HttpRequestAsyncTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("houseId", houseId);
        map.put("resblockId", resblockId);
        map.put("flag", "1");
        map.put("pageNo", "0");
        map.put("pageSize", "3");
        Request request = new Request(UrlUtils.HOUSE_ONE_DETAIL_RECOMMEND_LIST_URL, Request.Method.GET, map);
        HttpRequestAsyncTask httpRequestTask = new HttpRequestAsyncTask(activity, callBack);
        httpRequestTask.execute(request);
    }

    /**
     * 更多一手房源推荐列表
     *
     * @param houseId    房源id
     * @param resblockId 楼盘ID
     */
    public static void getOneHouseMore(Activity activity, String houseId, String resblockId, int pageNo, HttpRequestAsyncTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("houseId", houseId);
        map.put("resblockId", resblockId);
        map.put("flag", "2");
        map.put("pageNo", "" + pageNo);
        map.put("pageSize", "" + Constants.PAGE_SIZE);
        Request request = new Request(UrlUtils.HOUSE_ONE_DETAIL_RECOMMEND_LIST_URL, Request.Method.GET, map);
        HttpRequestAsyncTask httpRequestTask = new HttpRequestAsyncTask(activity, callBack);
        httpRequestTask.execute(request);
    }

    /**
     * 顾问带看房历史
     * @param resourceId 房源ID
     * @param type 类型 1：一手房源详情客户看房记录 2：二手房源详情客户看房记录
     * @param pageNo 页数
     */
    public static void getLookHistory(Activity activity, String resourceId, String type, int pageNo, HttpRequestAsyncTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isNotEmpty(resourceId)) {
            map.put("resourceId", resourceId);
        }
        if (StringUtils.isNotEmpty(type)) {
            map.put("type", type);
        }
        map.put("flag", "2");
        map.put("pageNo", "" + pageNo);
        map.put("pageSize", "" + Constants.PAGE_SIZE);
        Request request = new Request(UrlUtils.SEE_HISTORY_LIST_URL, Request.Method.GET, map);
        HttpRequestAsyncTask httpRequestTask = new HttpRequestAsyncTask(activity, callBack);
        httpRequestTask.execute(request);
    }

}
