package com.runtai.mvpproject.mudule.contract;

import com.runtai.mvpproject.comment.base.BaseMVPView;
import com.runtai.mvpproject.mudule.base.OnHttpCallBack;
import com.runtai.mvpproject.mudule.bean.DreamBean;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @作者：高炎鹏
 * @日期：2017/3/14时间16:15
 * @描述：
 */

public class DreamContract {

    public interface View extends BaseMVPView {
        String getEditData();

        void setData(DreamBean dreamBean);

        void selectPosition(int position, String title, List<String> list);
    }

    public interface Presenter {
        void query();

        boolean accord(String str);
    }

    public interface Model {
        void query(String str, OnHttpCallBack<DreamBean> callBack) throws UnsupportedEncodingException;
    }

}