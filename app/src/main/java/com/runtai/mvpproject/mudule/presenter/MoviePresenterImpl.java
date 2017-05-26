package com.runtai.mvpproject.mudule.presenter;

import com.runtai.mvpproject.mudule.base.OnHttpCallBack;
import com.runtai.mvpproject.mudule.bean.MovieBean;
import com.runtai.mvpproject.mudule.contract.MovieContract;
import com.runtai.mvpproject.mudule.model.MovieModelImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/03/13
 */

public class MoviePresenterImpl implements MovieContract.Presenter {

    MovieContract.View view;
    MovieContract.Model model;
    int start = 0;
    int count = 5;
    List<MovieBean.SubjectsBean>list = new ArrayList<>();

    public MoviePresenterImpl(MovieContract.View view) {
        this.view = view;
        model = new MovieModelImpl();
    }

    @Override
    public void getData() {
        view.showProgress();
        model.getData(start, count, new OnHttpCallBack<MovieBean>() {
            @Override
            public void onSuccessful(MovieBean movieBean) {
                view.hideProgress();
                list.addAll(movieBean.getSubjects());
                view.setData(list);
                view.scrollPosition(start);
            }

            @Override
            public void onFaild(String errorMsg) {
                view.hideProgress();
                view.showInfo(errorMsg);
            }
        });
    }

    @Override
    public void loadMoreMovie() {
        start += 5;
        getData();
    }

    @Override
    public void refreshMovie() {
        start = 0;
        count = 5;
        list.clear();
        getData();
    }
}