package com.runtai.mvpproject.mudule.contract;

import com.runtai.mvpproject.mudule.base.OnHttpCallBack;
import com.runtai.mvpproject.mudule.bean.IPBean;

/**
 * @作者：高炎鹏
 * @日期：2017/3/13时间18:03
 * @描述：
 */

public class IPContract {

    public interface View {
        String getEditText();

        void showProgress();

        void hideProgress();

        void setData(IPBean ipbean);

        void showInfo(String info);
    }

    public interface Presenter {
        void query();

        boolean isIp(String ip);
    }

    public interface Model {
        void query(String ip, OnHttpCallBack<IPBean> callBack);
    }

}