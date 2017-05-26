package com.runtai.mvpproject.mudule.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.runtai.mvpproject.R;
import com.runtai.mvpproject.comment.base.BaseActivity;
import com.runtai.mvpproject.mudule.bean.DreamBean;
import com.runtai.mvpproject.mudule.contract.DreamContract;
import com.runtai.mvpproject.mudule.presenter.DreamPresenterImpl;
import com.runtai.mvpproject.mudule.util.DividerItemDecoration;
import com.runtai.mvpproject.mudule.util.KeyboardUtil;
import com.runtai.mvpproject.mudule.util.ToastUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * @作者：高炎鹏
 * @日期：2017/3/14时间16:12
 * @描述：
 */

public class DreamActivity extends BaseActivity implements DreamContract.View {

    private ProgressDialog mProgressDialog;
    private DreamPresenterImpl presenter;
    private EditText dream_edit;
    private Button dream_query;
    private RecyclerView dream_list;

    @Override
    protected void beforeSetContent() {

    }

    @Override
    protected int getView() {
        return R.layout.activity_dream;
    }

    @Override
    protected void initView() {
        presenter = new DreamPresenterImpl(this);
        dream_edit = (EditText) findViewById(R.id.dream_edit);
        dream_query = (Button) findViewById(R.id.dream_query);
        dream_list = (RecyclerView) findViewById(R.id.dream_list);
        dream_query.setOnClickListener(this);

        dream_list.setLayoutManager(new LinearLayoutManager(this));//设置为listview的布局
        dream_list.setItemAnimator(new DefaultItemAnimator());//设置动画
        dream_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));//添加分割线
    }

    @Override
    protected void setListener() {
        super.setListener();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.dream_query:
                presenter.query();
                KeyboardUtil.closeKeyboard(DreamActivity.this, dream_query);
                break;
        }
    }

    @Override
    public String getEditData() {
        return dream_edit.getText().toString().trim();
    }

    @Override
    public void setData(DreamBean dreamBean) {
        if (dreamBean.getResult().size() <= 0) {
            return;
        }
        //鸿洋大神的通用适配器(真的很好用哦)
        CommonAdapter<DreamBean.ResultBean> commonAdapter = new CommonAdapter<DreamBean.ResultBean>(this, R.layout.dream_item, dreamBean.getResult()) {
            @Override
            protected void convert(ViewHolder holder, final DreamBean.ResultBean resultBean, final int position) {
                final String title = resultBean.getTitle();
                holder.setText(R.id.dream_title, title);//设置标题
                final String desc = resultBean.getDes();
                holder.setText(R.id.dream_desc, desc);//设置内容

                holder.setOnClickListener(R.id.dream_liner, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectPosition(position, resultBean.getTitle(), resultBean.getList());
                    }
                });
            }
        };
        dream_list.setAdapter(commonAdapter);
    }

    @Override
    public void selectPosition(int position, String title, List<String> list) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1) {
                sb.append("\n\n");
            }
        }
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(sb.toString())
                .setPositiveButton("确定", null)
                .show();
        Log.e("sb", position + "=======" + sb.toString());
    }

    @Override
    public void showProgress() {
        mProgressDialog = ProgressDialog.show(this, "提示", "正在获取中,请稍后...");
    }

    @Override
    public void hideProgress() {
        mProgressDialog.hide();
    }

    @Override
    public void showInfo(String info) {
        ToastUtils.showToast(this, info);
    }

}
