package com.runtai.mvpproject.mudule.model;

import android.util.Log;

import com.runtai.mvpproject.comment.api.BaseURL;
import com.runtai.mvpproject.comment.api.HttpApiService;
import com.runtai.mvpproject.comment.http.RetrofitUtils;
import com.runtai.mvpproject.mudule.base.OnHttpCallBack;
import com.runtai.mvpproject.mudule.bean.IPBean;
import com.runtai.mvpproject.mudule.contract.IPContract;
import com.socks.library.KLog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/03/13
 */

public class IPModelImpl implements IPContract.Model {

    @Override
    public void query(String ip, final OnHttpCallBack<IPBean> callBack) {
        RetrofitUtils.newInstence(BaseURL.IP_QUERY_URL)
                .create(HttpApiService.class)
                .queryIp(ip)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<IPBean>() {
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
                    public void onNext(IPBean ipbean) {
                        Log.e("回调", "回调" + ipbean.toString());
                        callBack.onSuccessful(ipbean);//请求成功---回调
                        KLog.e(ipbean.toString());
                    }
                });
    }

}