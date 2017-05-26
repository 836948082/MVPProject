package com.runtai.mvpproject.mudule.model;

import com.runtai.mvpproject.comment.api.BaseURL;
import com.runtai.mvpproject.comment.api.HttpApiService;
import com.runtai.mvpproject.comment.http.RetrofitUtils;
import com.runtai.mvpproject.mudule.base.OnHttpCallBack;
import com.runtai.mvpproject.mudule.bean.MovieBean;
import com.runtai.mvpproject.mudule.contract.MovieContract;
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

public class MovieModelImpl implements MovieContract.Model {

    @Override
    public void getData(int start, int count, final OnHttpCallBack<MovieBean> callBack) {
        RetrofitUtils.newInstence(BaseURL.MOVIE_TOP250_URL)
                .create(HttpApiService.class)
                .getMovies(start, count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<MovieBean>() {
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
                    public void onNext(MovieBean movieBean) {
                        callBack.onSuccessful(movieBean);//请求成功---回调
                        KLog.e(movieBean.toString());
                    }
                });
    }
}
