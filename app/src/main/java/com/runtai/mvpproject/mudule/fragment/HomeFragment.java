package com.runtai.mvpproject.mudule.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.runtai.mvpproject.R;
import com.runtai.mvpproject.comment.base.BaseFragment;
import com.runtai.mvpproject.mudule.view.DreamActivity;
import com.runtai.mvpproject.mudule.view.MovieActivity;

/**
 * @作者：高炎鹏
 * @日期：2017/3/14时间14:41
 * @描述：
 */

public class HomeFragment extends BaseFragment {

    private Button movie;
    private Button dream;

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        movie = (Button) view.findViewById(R.id.movie);
        dream = (Button) view.findViewById(R.id.dream);
    }

    @Override
    protected void setListener() {
        super.setListener();
        movie.setOnClickListener(this);
        dream.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.movie:
                skip(new Intent(mActivity, MovieActivity.class));
                break;
            case R.id.dream:
                skip(new Intent(mActivity, DreamActivity.class));
                break;
        }
    }

    @Override
    public void onFragmentStart() {
        super.onFragmentStart();
    }
}
