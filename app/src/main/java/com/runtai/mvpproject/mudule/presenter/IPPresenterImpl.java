package com.runtai.mvpproject.mudule.presenter;

import com.runtai.mvpproject.mudule.base.OnHttpCallBack;
import com.runtai.mvpproject.mudule.bean.IPBean;
import com.runtai.mvpproject.mudule.contract.IPContract;
import com.runtai.mvpproject.mudule.model.IPModelImpl;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/03/13
 */

public class IPPresenterImpl implements IPContract.Presenter {

    private IPContract.View view;
    private IPModelImpl model;

    public IPPresenterImpl(IPContract.View view) {
        this.view = view;
        model = new IPModelImpl();
    }

    @Override
    public void query() {
        String ip = view.getEditText();
        if (isIp(ip)) {//正则判断
            view.showProgress();
            model.query(ip, new OnHttpCallBack<IPBean>() {
                @Override
                public void onSuccessful(IPBean ipbean) {
                    view.hideProgress();
                    view.setData(ipbean);
                }

                @Override
                public void onFaild(String errorMsg) {
                    view.hideProgress();
                    view.showInfo(errorMsg);
                }
            });
        } else {
            view.showInfo("IP地址格式有误...");
        }
    }

    @Override
    public boolean isIp(String ip) {
        String regex = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        return Pattern.compile(regex).matcher(ip).matches();
    }

}