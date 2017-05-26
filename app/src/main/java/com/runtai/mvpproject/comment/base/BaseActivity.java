package com.runtai.mvpproject.comment.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.runtai.library.SwipeBackHelper;
import com.runtai.mvpproject.R;

public abstract class BaseActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setWindowStatusBarColor(this, R.color.title_color);

        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(true)
                .setSwipeEdgePercent(0.5f)
                .setSwipeSensitivity(0.5f)
                .setClosePercent(0.5f)
                .setSwipeRelateEnable(true).setSwipeSensitivity(1);

        beforeSetContent();
        setContentView(getView());
        init();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }

    /**
     * 返回键调用此方法
     */
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        overridePendingTransition(0, R.anim.activity_close);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.activity_close);
    }

    public void skip(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.activity_open, 0);
    }

    private void init() {
        getIntentInfo();
        initViews();
        setListener();
        getDatas();
    }

    private void initViews() {
        getWindow().setWindowAnimations(R.style.ActivityAnimation);

        /**
         * 沉浸式导航栏
         */
        //透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        initView();
    }

    /**
     * 设置状态栏颜色
     * @param activity
     * @param colorResId
     */
    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取 意图 intent中的数据，如果不需要获取，则可以不做处理
     */
    protected void getIntentInfo() {
    }

    /**
     * 设置监听事件，如果不用设置，则不用设置
     */
    protected void setListener() {
    }

    /**
     * 获取数据,根据实际项目，放在onResume或者onPause中
     */
    protected void getDatas() {
    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void doBeforeSetContent() {
        // TODO Auto-generated method stub
        super.doBeforeSetContent();
        beforeSetContent();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected int getContentViewID() {
        return getView();
    }

    /**
     * 根据资源id 获取View ，不用强制转换
     */
    protected <A extends View> A getView(int id) {
        return (A) findViewById(id);
    }

    protected abstract void beforeSetContent();
    protected abstract int getView();
    protected abstract void initView();

}
