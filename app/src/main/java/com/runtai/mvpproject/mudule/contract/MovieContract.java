package com.runtai.mvpproject.mudule.contract;

import com.runtai.mvpproject.mudule.base.OnHttpCallBack;
import com.runtai.mvpproject.mudule.bean.MovieBean;

import java.util.List;

/**
 * @作者：高炎鹏
 * @日期：2017/3/13时间16:08
 * @描述：
 */

public class MovieContract {

    public interface View {
        void showProgress();

        void hideProgress();

        void setData(List<MovieBean.SubjectsBean> data);

        void scrollPosition(int position);

        void showInfo(String info);
    }

    public interface Presenter {
        void getData();

        void loadMoreMovie();

        void refreshMovie();
    }

    public interface Model {
        void getData(int start, int count, OnHttpCallBack<MovieBean> callBack);//获取信息
    }

}