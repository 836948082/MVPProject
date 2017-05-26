package com.runtai.mvpproject.comment.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runtai.mvpproject.R;

/**
 * @作者：高炎鹏
 * @日期：2017/3/14时间15:08
 * @描述：
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    protected FragmentActivity mActivity;
    public final String TAG = getClass().getSimpleName();
    private boolean debugLifeCycler = false;
    public boolean isCurrentStart = false;

    /**
     * 根view
     */
    protected View view;

    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected boolean mIsPrepare;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getFragmentView(), container, false);
        initData(getArguments());
        initView(view, savedInstanceState);
        mIsPrepare = true;
        onLazyLoad();
        setListener();
        return view;
    }

    /**
     * 初始化数据
     *
     * @param arguments 接收到的从其他地方传递过来的参数
     */
    protected void initData(Bundle arguments) {
    }

    /**
     * 设置监听事件
     */
    protected void setListener() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.mIsVisible = isVisibleToUser;
        if (isVisibleToUser) {
            onVisibleToUser();
        }
    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisibleToUser() {
        if (mIsPrepare && mIsVisible) {
            onLazyLoad();
        }
    }

    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     */
    protected void onLazyLoad() {
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T findViewById(int id) {
        if (view == null) {
            return null;
        }
        return (T) view.findViewById(id);
    }

    public void onFragmentStart() {
        Log.e(TAG, "onFragmentStart");
        isCurrentStart = true;
    }

    public void onFragmentStop() {
        Log.e(TAG, "onFragmentStop");
        isCurrentStart = false;
    }

    @Override
    public void onClick(View v) {
    }

    /**
     * 设置根布局资源id
     */
    protected abstract int getFragmentView();

    /**
     * 初始化View
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    public void skip(Intent intent) {
        mActivity.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.activity_open, 0);
    }

}