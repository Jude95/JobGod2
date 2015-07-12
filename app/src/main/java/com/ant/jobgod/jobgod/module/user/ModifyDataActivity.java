package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.util.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by alien on 2015/7/12.
 */
@RequiresPresenter(ModifyFacePresenter.class)
public class ModifyDataActivity extends BaseActivity<ModifyFacePresenter> {

    @InjectView(R.id.data)
    TextInputLayout data;

    private Intent intent;

    private final int RESULT_DATA=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_modifydata);
        ButterKnife.inject(this);
        intent=getIntent();
        setTitleAndResult();

    }

    public void setTitleAndResult(){
        String viewName=intent.getStringExtra("viewName");
        String content=intent.getStringExtra("data");
        intent.putExtra("viewName", viewName);
        switch (viewName){
            case "award":
                setTitle("修改奖项");
                data.setHint("奖项");
                break;
            case "certificate":
                setTitle("修改证书");
                data.setHint("证书");
                break;
            case "character":
                setTitle("修改性格");
                data.setHint("性格");
                break;
            case "like":
                setTitle("修改爱好");
                data.setHint("爱好");
                break;
            case "specialty":
                setTitle("修改特长");
                data.setHint("特长");
                break;
            case "intro":
                setTitle("修改简介");
                data.setHint("简介");
                data.getEditText().setHint("修改简介");
                break;
        }
        data.getEditText().setText(content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_modifydata,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.submit){
            intent.putExtra("data",data.getEditText().getText().toString());
            Utils.Log("data:" + data.getEditText().getText().toString());
            setResult(RESULT_DATA, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
