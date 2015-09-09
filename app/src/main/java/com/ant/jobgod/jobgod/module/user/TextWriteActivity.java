package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.ant.jobgod.jobgod.R;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by alien on 2015/7/12.
 */
@RequiresPresenter(TextWritePresenter.class)
public class TextWriteActivity extends BeamBaseActivity<TextWritePresenter> {

    @InjectView(R.id.data)
    TextInputLayout data;

    private Intent intent;

    private final int RESULT_DATA = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_textwrite);
        ButterKnife.inject(this);
        intent = getIntent();
        setTitleAndResult();
    }

    public void setTitleAndResult() {
        ModifyDetailActivity.InfoFlag flag = (ModifyDetailActivity.InfoFlag) intent.getSerializableExtra(ModifyDetailActivity.KEY_FLAG);
        String content = intent.getStringExtra(ModifyDetailActivity.DATA);
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        switch (flag) {
            case AWARD:
                setTitle("修改奖项");
                break;
            case CERTIFICATE:
                setTitle("修改证书");
                break;
            case CHARACTER:
                setTitle("修改性格");
                break;
            case LIKE:
                setTitle("修改爱好");
                break;
            case SPECIALTY:
                setTitle("修改特长");
                break;
            case INTRO:
                setTitle("修改简介");
                break;
        }
        data.getEditText().setText(content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_modifydata, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.submit) {
            intent.putExtra(ModifyDetailActivity.DATA, data.getEditText().getText().toString());
            setResult(RESULT_DATA, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
