package com.bwf.framwork.http;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.bwf.framwork.base.BaseBean;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.framwork.utils.NetWorkUtils;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

/**
 * Created by Lizhangfeng on 2016/7/13 0013.
 * Description:
 */
public class HttpRequestAsyncTask extends AsyncTask<Request, Integer, String> {

    /* 设置编码格式 */
    private static final String CHARSET = "UTF-8";

    private CallBack callBack;

    private Activity activity;

    public HttpRequestAsyncTask(Activity activity, CallBack callBack) {
        this.callBack = callBack;
        this.activity = activity;
    }

    private HttpURLConnection buildHttpClient(String urlStr, int timeout) {

        URL url = null;
        HttpURLConnection conn = null;
        try {
            LogUtils.e("请求地址: " + urlStr);
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(timeout);// 读取超时 单位毫秒
            conn.setConnectTimeout(timeout);// 连接超时
            conn.setUseCaches(false);
            conn.setDoOutput(true);// 是否输入参数
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    protected String doInBackground(Request... requests) {//相当于Thread run

        String resultString = "";
        Request request = requests[0];

        if (!NetWorkUtils.isNetDeviceAvailable(activity)) {
            return "网络不可用";
        }

        try {
            HttpURLConnection connection = buildHttpClient(request.getUrl(), request.getRequestTimeOut());
            connection.setRequestMethod(request.getMethod().getMethed());// 请求方式
            StringBuffer params = new StringBuffer();
            if (request.getParams() != null) {
                for (Map.Entry<String, String> entry : request.getParams().entrySet()) {
                    params.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                    LogUtils.e("请求参数：" + entry.getKey() + " : " + entry.getValue());
                }
                byte[] bypes = params.toString().substring(1, params.length()).getBytes();
                connection.getOutputStream().write(bypes);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                LogUtils.e("请求接口失败：responseCode ＝ " + responseCode);
                return null;
            }
            InputStream in = connection.getInputStream();
            InputStreamReader isReader = new InputStreamReader(in, CHARSET);
            BufferedReader bufReader = new BufferedReader(isReader);
            resultString = bufReader.readLine();
            LogUtils.e("接口返回结果：" + resultString);
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return resultString;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {//运行于主线程

        if (activity != null && !activity.isFinishing()) {//activity结束掉之后不希望返回给主线程，防止activity还有null对象调用出现NullPointExcption
            if (!TextUtils.isEmpty(result)) {
                if ("网络不可用".equals(result)) {
                    if (callBack != null)
                        callBack.OnFailed("网络不可用");
                }
                if (callBack != null) {
                    try {
                        BaseBean baseBean = new Gson().fromJson(result, BaseBean.class);
                        if ("200".equals(baseBean.resultStatus)) {
                            callBack.OnSuccess(result);
                        }
                    } catch (Exception e) {
                        callBack.OnFailed("数据解析异常");
                    }

                }
            } else {
                if (callBack != null)
                    callBack.OnFailed("请求失败");
            }
        }

        super.onPostExecute(result);
    }

    public interface CallBack {

        void OnSuccess(String result);

        void OnFailed(String errMsg);
    }

}
