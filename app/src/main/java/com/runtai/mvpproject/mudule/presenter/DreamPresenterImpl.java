package com.runtai.mvpproject.mudule.presenter;

import com.runtai.mvpproject.mudule.base.OnHttpCallBack;
import com.runtai.mvpproject.mudule.bean.DreamBean;
import com.runtai.mvpproject.mudule.contract.DreamContract;
import com.runtai.mvpproject.mudule.model.DreamModelImpl;

/**
 * Created by Administrator on 2017/03/14
 */

public class DreamPresenterImpl implements DreamContract.Presenter {

    private DreamContract.View view;
    private DreamModelImpl model;

    public DreamPresenterImpl(DreamContract.View view) {
        this.view = view;
        model = new DreamModelImpl();
    }

    @Override
    public void query() {
        String q = view.getEditData();
        if (accord(q)) {
            view.showProgress();
            model.query(q, new OnHttpCallBack<DreamBean>() {
                @Override
                public void onSuccessful(DreamBean dreamBean) {
                    view.hideProgress();
                    view.setData(dreamBean);
                }

                @Override
                public void onFaild(String errorMsg) {
                    view.hideProgress();
                    view.showInfo(errorMsg);
                }
            });
        } else {
            view.showInfo("内容不能为空");
        }
    }

    @Override
    public boolean accord(String str) {
        return !str.equals("");
    }

}