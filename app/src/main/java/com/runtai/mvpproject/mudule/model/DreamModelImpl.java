package com.runtai.mvpproject.mudule.model;

import android.util.Log;

import com.runtai.mvpproject.comment.api.BaseURL;
import com.runtai.mvpproject.comment.api.HttpApiService;
import com.runtai.mvpproject.comment.http.RetrofitUtils;
import com.runtai.mvpproject.mudule.base.OnHttpCallBack;
import com.runtai.mvpproject.mudule.bean.DreamBean;
import com.runtai.mvpproject.mudule.contract.DreamContract;
import com.socks.library.KLog;

import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/03/14
 */

public class DreamModelImpl implements DreamContract.Model {

    @Override
    public void query(String q, final OnHttpCallBack<DreamBean> callBack) {

        String content = "";
        try {
            content = URLDecoder.decode(q, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("key", "f86ed9f21931cd311deffada92b58ac7");
        map.put("full", "1");
        map.put("q", content);

        RetrofitUtils.newInstence(BaseURL.DREAM_URL)
                .create(HttpApiService.class)
                .getDream(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<DreamBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        //失败的时候回调-----一下可以忽略 直接 callBack.onFaild("请求失败");
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onFaild("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.onFaild("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.onFaild("网络连接超时!!");
                        } else {
                            callBack.onFaild("发生未知错误" + e.getMessage());
                            KLog.e(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(DreamBean dreamBean) {
                        callBack.onSuccessful(dreamBean);//请求成功---回调
                        KLog.e(dreamBean.toString());
                    }
                });
    }
}