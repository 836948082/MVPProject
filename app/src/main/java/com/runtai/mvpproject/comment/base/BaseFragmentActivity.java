package com.runtai.mvpproject.comment.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * BaseFragmentActivity对FragmentActivity一些简单的封装
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements OnClickListener {

    protected BaseFragmentActivity CTX = BaseFragmentActivity.this;
    protected LayoutInflater inflater;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetContent();
        if (getContentViewID() == -1) {
            setContentView(getContentView());
        } else {
            setContentView(getContentViewID());
        }

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                        | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        init(savedInstanceState);
    }

    /**
     * 在设置 content内容前的操作，比如设置全屏
     */
    protected void doBeforeSetContent() {
    }

    /**
     * 获取 意图 intent中的数据，如果不需要获取，则可以不做处理
     */
    protected void getIntentInfo() {
    }

    /**
     * 没有layout的可以使用该方法为activity添加contentview
     */
    protected View getContentView() {
        return null;
    }

    /**
     * 设置本界面 的Layout的id,如果是定义view，只要重新写该方法，让返回值为0即可
     */
    protected abstract int getContentViewID();

    /**
     * 设置监听事件，如果不用设置，则不用设置
     */
    protected void setListener() {
    }

    /**
     * 初始化所有View和数据
     */
    private void init(Bundle savedInstanceState) {
        getIntentInfo();
        initView(savedInstanceState);
        setListener();
        getDatas();
    }

    /**
     * 获取数据,根据实际项目，放在onResume或者onPause中
     */
    protected void getDatas() {
    }

    /**
     * 初始化所有控件
     */
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 监听Click事件
     */
    public void onClick(View view) {
    }

    /**
     * 封装Intent跳转
     *
     * @param clazz
     * @param isCloseSelf
     */
    protected void skip(Class<?> clazz, boolean isCloseSelf) {
        Intent intent = new Intent(CTX, clazz);
        startActivity(intent);
        if (isCloseSelf) {
            CTX.finish();
        }
    }

    /**
     * 封装Intent跳转
     *
     * @param key
     * @param value
     * @param clazz
     * @param isCloseSelf
     */
    protected void skip(String key, String value, Class<?> clazz, boolean isCloseSelf) {
        Intent intent = new Intent();
        intent.setClass(CTX, clazz);
        if (!TextUtils.isEmpty(key)) {
            intent.putExtra(key, value);
        }
        startActivity(intent);
        if (isCloseSelf) {
            CTX.finish();
        }
    }

    /**
     * 封装Intent跳转
     *
     * @param key
     * @param obj
     * @param clazz
     * @param isCloseSelf
     */
    protected void skip(String key, Serializable obj, Class<?> clazz, boolean isCloseSelf) {
        Intent intent = new Intent();
        intent.setClass(CTX, clazz);
        if (!TextUtils.isEmpty(key)) {
            intent.putExtra(key, obj);
        }
        startActivity(intent);
        if (isCloseSelf) {
            CTX.finish();
        }
    }

    /**
     * 封装Intent跳转
     *
     * @param key
     * @param obj
     * @param clazz
     * @param isCloseSelf
     */
    protected void skip(String key, ArrayList<Parcelable> obj, Class<?> clazz, boolean isCloseSelf) {
        Intent intent = new Intent();
        intent.setClass(CTX, clazz);
        if (!TextUtils.isEmpty(key)) {
            intent.putParcelableArrayListExtra(key, obj);
        }
        startActivity(intent);
        if (isCloseSelf) {
            CTX.finish();
        }
    }

    /**
     * 封装Intent跳转
     *
     * @param key
     * @param obj
     * @param clazz
     * @param isCloseSelf
     */
    protected void skip(String key, int obj, Class<?> clazz, boolean isCloseSelf) {
        Intent intent = new Intent();
        intent.setClass(CTX, clazz);
        if (!TextUtils.isEmpty(key)) {
            intent.putExtra(key, obj);
        }
        startActivity(intent);
        if (isCloseSelf) {
            CTX.finish();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
//        Log.e("触摸时间", "" + System.currentTimeMillis());
        return super.dispatchTouchEvent(ev);
    }

}
