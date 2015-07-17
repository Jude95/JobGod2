package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by alien on 2015/7/10.
 */
public class UserDetailPresenter extends BasePresenter<UserDetailActivity> {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onCreateView(UserDetailActivity view) {
        super.onCreateView(view);
    }


    public void startAcitivity(Class<?> ctx){
        intent=new Intent();
        intent.setClass(getView(),ctx);
        getView().startActivity(intent);
    }


    public void createEditDialog(String title, int maxLength, String hint, TextView text) {
        new MaterialDialog.Builder(getView())
                .title(title)
                .titleColor(R.color.DeepOrange)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .inputMaxLength(maxLength)
                .input(hint, "", (dialog, input) -> {
                    if (input.toString().trim().isEmpty()) {
                        Utils.Toast("标题不能为空");
                        return;
                    } else
                        text.setText(input.toString());
                }).show();
    }
}
