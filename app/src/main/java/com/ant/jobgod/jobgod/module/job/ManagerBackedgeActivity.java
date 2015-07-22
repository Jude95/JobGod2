package com.ant.jobgod.jobgod.module.job;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.bean.Manager;
import com.ant.jobgod.jobgod.util.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by alien on 2015/7/22.
 */
@RequiresPresenter(ManagerBackedgePresenter.class)
public class ManagerBackedgeActivity extends BaseActivity<ManagerBackedgePresenter> {


    @InjectView(R.id.jobName)
    TextView jobName;
    @InjectView(R.id.jobStatus)
    TextView jobStatus;
    @InjectView(R.id.btnOperate)
    TextView btnOperate;
    @InjectView(R.id.evaluate)
    TextView evaluate;
    @InjectView(R.id.btnSubmit)
    TextView btnSubmit;
    @InjectView(R.id.viewJobChat)
    LinearLayout viewJobChat;

    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_activity_managerbackedge);
        ButterKnife.inject(this);
    }

    public void setData(Manager data) {
        jobName.setText(data.getJobName());
        switch (data.getStatus()) {
            case 0:
                jobStatus.setText("等待审核");
                btnOperate.setText("取消报名");
                btnOperate.setOnClickListener(v -> getPresenter().cancelApply());
                break;
            case 1:
                jobStatus.setText("被商家拒绝了");
                btnOperate.setVisibility(View.GONE);
                break;
            case 2:
                jobStatus.setText("通过报名,等待工作开始");
                btnOperate.setText("已报名");
                btnOperate.setEnabled(false);
                btnOperate.setBackgroundColor(Color.GRAY);
                break;
            case 3:
                jobStatus.setText("工作中");
                btnOperate.setText("已报名");
                btnOperate.setEnabled(false);
                btnOperate.setBackgroundColor(Color.GRAY);
                break;
            case 4:
                jobStatus.setText(data.getJobName() + "合同结束");
                btnOperate.setText("评价兼职");
                btnOperate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new MaterialDialog.Builder(ManagerBackedgeActivity.this)
                                .title("评价")
                                .inputType(InputType.TYPE_CLASS_TEXT)
                                .inputMaxLength(100)
                                .input("", "", new MaterialDialog.InputCallback() {
                                    @Override
                                    public void onInput(MaterialDialog dialog, CharSequence input) {
                                        if (input.toString().trim().isEmpty()) {
                                            Utils.Toast("不能为空");
                                            return;
                                        }
                                        setContent(input.toString());
                                        evaluate.setText(input.toString());
                                    }
                                }).show();
                    }
                });
                btnSubmit.setOnClickListener(v -> getPresenter().evaluateBiz());
                break;
        }

    }

    public void setBtnStatus(boolean isClick) {
        jobStatus.setText("已取消");
        jobStatus.setEnabled(isClick);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
