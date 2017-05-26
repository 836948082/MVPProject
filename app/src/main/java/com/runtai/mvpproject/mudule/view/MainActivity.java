package com.runtai.mvpproject.mudule.view;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.runtai.library.SwipeBackHelper;
import com.runtai.mvpproject.R;
import com.runtai.mvpproject.comment.base.BaseActivity;
import com.runtai.mvpproject.comment.base.BaseFragment;
import com.runtai.mvpproject.comment.view.NoScrollViewPager;
import com.runtai.mvpproject.mudule.adapter.HomeTabAdapter;

/**
 * @作者：高炎鹏
 * @日期：2017/3/14时间14:21
 * @描述：
 */

public class MainActivity extends BaseActivity{

    private NoScrollViewPager vp_main;
    private LinearLayout ll_tab1, ll_tab2, ll_tab3, ll_tab4;
    private ImageView iv_tab1, iv_tab2, iv_tab3, iv_tab4;
    private HomeTabAdapter adapter;
    private TextView tv_tab1, tv_tab2, tv_tab3, tv_tab4;
    private int oldPosition = -1;

    private Handler handler = new Handler();
    private boolean isShowFinish = false;

    @Override
    protected void beforeSetContent() {

    }

    @Override
    protected int getView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false);

        ll_tab1 = (LinearLayout) findViewById(R.id.ll_tab1);
        ll_tab2 = (LinearLayout) findViewById(R.id.ll_tab2);
        ll_tab3 = (LinearLayout) findViewById(R.id.ll_tab3);
        ll_tab4 = (LinearLayout) findViewById(R.id.ll_tab4);
        ll_tab1.setOnClickListener(this);
        ll_tab2.setOnClickListener(this);
        ll_tab3.setOnClickListener(this);
        ll_tab4.setOnClickListener(this);
        iv_tab1 = (ImageView) findViewById(R.id.iv_tab1);
        iv_tab2 = (ImageView) findViewById(R.id.iv_tab2);
        iv_tab3 = (ImageView) findViewById(R.id.iv_tab3);
        iv_tab4 = (ImageView) findViewById(R.id.iv_tab4);
        vp_main = (NoScrollViewPager) findViewById(R.id.vp_main);
        tv_tab1 = (TextView) findViewById(R.id.tv_tab1);
        tv_tab2 = (TextView) findViewById(R.id.tv_tab2);
        tv_tab3 = (TextView) findViewById(R.id.tv_tab3);
        tv_tab4 = (TextView) findViewById(R.id.tv_tab4);

        adapter = new HomeTabAdapter(getSupportFragmentManager());
        adapter.addHomeTab();

        vp_main.setOffscreenPageLimit(3);
        vp_main.setAdapter(adapter);

        onClick(ll_tab1);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_tab1:
                changeButtomStates(0);
                changeTab(0);
                break;
            case R.id.ll_tab2:
                changeButtomStates(1);
                changeTab(1);
                break;
            case R.id.ll_tab3:
                changeButtomStates(2);
                changeTab(2);
                break;
            case R.id.ll_tab4:
                changeButtomStates(3);
                changeTab(3);
                break;
        }
    }

    private void changeButtomStates(int position) {
        int grayColor = getResources().getColor(R.color.gray_text);
        int redColor = getResources().getColor(R.color.red_text);
        switch (position) {
            case 0:
                iv_tab1.setImageResource(R.mipmap.home_icon_checked);
                iv_tab2.setImageResource(R.mipmap.order_icon_unchecked);
                iv_tab3.setImageResource(R.mipmap.order_icon_unchecked);
                iv_tab4.setImageResource(R.mipmap.personal_icon_unchecked);
                tv_tab1.setTextColor(redColor);
                tv_tab2.setTextColor(grayColor);
                tv_tab3.setTextColor(grayColor);
                tv_tab4.setTextColor(grayColor);
                break;
            case 1:
                iv_tab1.setImageResource(R.mipmap.home_icon_unchecked);
                iv_tab2.setImageResource(R.mipmap.order_icon_checked);
                iv_tab3.setImageResource(R.mipmap.order_icon_unchecked);
                iv_tab4.setImageResource(R.mipmap.personal_icon_unchecked);
                tv_tab1.setTextColor(grayColor);
                tv_tab2.setTextColor(redColor);
                tv_tab3.setTextColor(grayColor);
                tv_tab4.setTextColor(grayColor);
                break;
            case 2:
                iv_tab1.setImageResource(R.mipmap.home_icon_unchecked);
                iv_tab2.setImageResource(R.mipmap.order_icon_unchecked);
                iv_tab3.setImageResource(R.mipmap.order_icon_checked);
                iv_tab4.setImageResource(R.mipmap.personal_icon_unchecked);
                tv_tab1.setTextColor(grayColor);
                tv_tab2.setTextColor(grayColor);
                tv_tab3.setTextColor(redColor);
                tv_tab4.setTextColor(grayColor);
                break;
            case 3:
                iv_tab1.setImageResource(R.mipmap.home_icon_unchecked);
                iv_tab2.setImageResource(R.mipmap.order_icon_unchecked);
                iv_tab3.setImageResource(R.mipmap.order_icon_unchecked);
                iv_tab4.setImageResource(R.mipmap.personal_icon_checked);
                tv_tab1.setTextColor(grayColor);
                tv_tab2.setTextColor(grayColor);
                tv_tab3.setTextColor(grayColor);
                tv_tab4.setTextColor(redColor);
                break;
            default:
                break;
        }
        notifyChildFragment(position);
    }


    private void notifyChildFragment(int position) {
        if (oldPosition == position) {
            return;
        }
        ((BaseFragment) adapter.getItem(position)).onFragmentStart();
        if (oldPosition != -1) {
            ((BaseFragment) adapter.getItem(oldPosition)).onFragmentStop();
        }
        oldPosition = position;
    }

    public void changeTab(int position) {
        if (position < 0 || position > 3 || adapter.getList().size() == 0) {
            return;
        }
        vp_main.setCurrentItem(position, false);
    }
}
