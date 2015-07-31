package com.ant.jobgod.jobgod.module.main.bbs;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by alien on 2015/7/30.
 */
@RequiresPresenter(ForwardPresenter.class)
public class ForwardActivity extends BaseActivity<ForwardPresenter> {


    @InjectView(R.id.forwardText)
    EditText forwardText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbs_activity_forward);
        ButterKnife.inject(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bbs_forward, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.submit) {
            getPresenter().forward(forwardText.getText().toString());
        }
        return super.onOptionsItemSelected(item);
    }
}
