package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.http.RequestMap;
import com.ant.jobgod.jobgod.app.BasePresenter;

/**
 * Created by alien on 2015/7/10.
 */
public class ModifyDetailPresenter extends BasePresenter<ModifyDetailActivity> {

    private final int REQUEST_CODE = 1;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        intent=new Intent(getView(), TextWriteActivity.class);
    }

    @Override
    protected void onCreateView(ModifyDetailActivity view) {
        super.onCreateView(view);
    }

    public void submitInfo(RequestMap param) {
        param.put("id", readStandVar("id", 0) + "");
        /**
         * 接下来去post到服务器
         */
    }

    public void awardToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserData().setAward(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

    public void certificateToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserData().setCertificate(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

    public void characterToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserData().setCharacter(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

    public void introToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserData().setIntro(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

    public void likeToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserData().setLike(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

    public void specialtyToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserData().setSpecialty(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

}
