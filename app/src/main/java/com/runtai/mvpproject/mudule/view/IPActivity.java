package com.runtai.mvpproject.mudule.view;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.runtai.mvpproject.R;
import com.runtai.mvpproject.comment.base.BaseActivity;
import com.runtai.mvpproject.mudule.bean.IPBean;
import com.runtai.mvpproject.mudule.contract.IPContract;
import com.runtai.mvpproject.mudule.presenter.IPPresenterImpl;
import com.runtai.mvpproject.mudule.util.ToastUtils;
import com.runtai.mvpproject.mudule.widget.ClearEditText;
import com.socks.library.KLog;

/**
 * @作者：高炎鹏
 * @日期：2017/3/13时间17:21
 * @描述：
 */

public class IPActivity extends BaseActivity implements IPContract.View {

    private IPActivity mActivity;
    private IPPresenterImpl presenter;
    ProgressDialog mProgressDialog;

    TextView tvMainCountry;
    TextView tvMainArea;
    TextView tvMainRegion;
    TextView tvMainCity;
    TextView tvMainIsp;
    ClearEditText etMainInputIp;
    TextView tvTitle;
    private Button btn_movie_queryIp;
    private ClearEditText editText;
    private TextView tv_movie_ip;

    @Override
    protected void beforeSetContent() {
        mActivity = this;
    }

    @Override
    protected int getView() {
        return R.layout.activity_ip;
    }

    @Override
    protected void initView() {
        presenter = new IPPresenterImpl(this);
        btn_movie_queryIp = (Button) findViewById(R.id.btn_movie_queryIp);
        btn_movie_queryIp.setOnClickListener(this);
        editText = (ClearEditText) findViewById(R.id.et_movie_input_ip);
        tv_movie_ip = (TextView) findViewById(R.id.tv_movie_ip);
        tvMainCountry = (TextView) findViewById(R.id.tv_movie_country);
        tvMainArea = (TextView) findViewById(R.id.tv_movie_area);
        tvMainRegion = (TextView) findViewById(R.id.tv_movie_region);
        tvMainCity = (TextView) findViewById(R.id.tv_movie_city);
        tvMainIsp = (TextView) findViewById(R.id.tv_movie_isp);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.btn_movie_queryIp:
                KLog.e("查询ip了");
                presenter.query();
                break;
        }
    }

    @Override
    public String getEditText() {
        return editText.getText().toString();
    }

    @Override
    public void showProgress() {
        mProgressDialog = ProgressDialog.show(this, "提示", "正在查询中,请稍后...");
        mProgressDialog.setCancelable(true);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void hideProgress() {
        mProgressDialog.hide();
        mProgressDialog.dismiss();
    }

    @Override
    public void setData(IPBean bean) {
        tv_movie_ip.setText(bean.getData().getIp());
        tvMainCountry.setText(bean.getData().getCountry());
        tvMainArea.setText(bean.getData().getArea());
        tvMainRegion.setText(bean.getData().getRegion());
        tvMainCity.setText(bean.getData().getCity());
        tvMainIsp.setText(bean.getData().getIsp());
    }

    @Override
    public void showInfo(String info) {
        ToastUtils.showToast(this, info);
    }

}
