package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by alien on 2015/7/10.
 */
public class ModifyDetailPresenter extends BasePresenter<ModifyDetailActivity> {

    private final int REQUEST_CODE = 1;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        intent = new Intent(getView(), TextWriteActivity.class);
    }

    @Override
    protected void onCreateView(ModifyDetailActivity view) {
        super.onCreateView(view);
    }

    public void updateUserDetail() {
        UserModel.updateUserDetail(getView().getUserDetail(), new StatusCallback() {
            @Override
            public void success(String info) {
                if (info.equals("success")) {
                    Utils.Toast("保存成功");
                }
            }
        });
    }

    public void awardToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserDetail().setAward(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

    public void certificateToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserDetail().setCertificate(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

    public void characterToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserDetail().setCharacter(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

    public void introToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserDetail().setIntro(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

    public void likeToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserDetail().setLike(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

    public void specialtyToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserDetail().setSpecialty(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

}
