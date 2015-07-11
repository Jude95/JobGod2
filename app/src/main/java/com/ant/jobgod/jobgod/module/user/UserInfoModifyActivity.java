package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.http.RequestMap;
import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.util.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(UserInfoModifyPresenter.class)
public class UserInfoModifyActivity extends BaseActivity<UserInfoModifyPresenter> {


    @InjectView(R.id.displayName)
    TextView displayName;
    @InjectView(R.id.editName)
    TextView editName;
    @InjectView(R.id.displaySignature)
    TextView displaySignature;
    @InjectView(R.id.editSignature)
    TextView editSignature;
    @InjectView(R.id.displayGender)
    TextView displayGender;
    @InjectView(R.id.editGender)
    TextView editGender;
    @InjectView(R.id.displayHeight)
    TextView displayHeight;
    @InjectView(R.id.editHeight)
    TextView editHeight;
    @InjectView(R.id.displayAddress)
    TextView displayAddress;
    @InjectView(R.id.editAddress)
    TextView editAddress;
    @InjectView(R.id.displayEduLevel)
    TextView displayEduLevel;
    @InjectView(R.id.editEduLevel)
    TextView editEduLevel;
    @InjectView(R.id.displaySchool)
    TextView displaySchool;
    @InjectView(R.id.editSchool)
    TextView editSchool;
    @InjectView(R.id.displayMajor)
    TextView displayMajor;
    @InjectView(R.id.editMajor)
    TextView editMajor;
    @InjectView(R.id.editAward)
    EditText editAward;
    @InjectView(R.id.editCertificate)
    EditText editCertificate;
    @InjectView(R.id.editCharacter)
    EditText editCharacter;
    @InjectView(R.id.editLike)
    EditText editLike;
    @InjectView(R.id.editSpecialty)
    EditText editSpecialty;
    @InjectView(R.id.editIntro)
    EditText editIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_infomodify);
        ButterKnife.inject(this);
        init();
    }

    public void init() {
        editName.setOnClickListener(v -> createEditDialog("输入", 8, "最多8字", displayName));
        editSignature.setOnClickListener(v -> createEditDialog("输入", 32, "最多32字", displaySignature));
        editGender.setOnClickListener(v -> createSingleChoiceDialog("请选择", R.array.gender, displayGender));
        editHeight.setOnClickListener(v -> createEditDialog("输入", 8, "最多8字", displayHeight));
        editAddress.setOnClickListener(v -> createEditDialog("输入", 32, "最多32字", displayAddress));

        editEduLevel.setOnClickListener(v -> createSingleChoiceDialog("请选择", R.array.eduLevel, displayEduLevel));
        editSchool.setOnClickListener(v -> createEditDialog("输入", 32, "最多32字", displaySchool));
        editMajor.setOnClickListener(v -> createEditDialog("输入", 16, "最多16字", displayMajor));

    }

    /**
     * 网络请求参数
     */
    public void submitInfo() {
        RequestMap param = new RequestMap();
        param.put("name", displayName.getText().toString());
        param.put("sign", displaySignature.getText().toString());
        param.put("gender", displayGender.getText().toString());
        param.put("height", displayHeight.getText().toString());
        param.put("address", displayAddress.getText().toString());
        param.put("edulevel", displayEduLevel.getText().toString());
        param.put("school", displaySchool.getText().toString());
        param.put("major", displayMajor.getText().toString());
        param.put("award", editAward.getText().toString());
        param.put("certificate", editCertificate.getText().toString());
        param.put("character", editCharacter.getText().toString());
        param.put("like", editLike.getText().toString());
        param.put("specialty", editSpecialty.getText().toString());
        param.put("intro", editIntro.getText().toString());


        getPresenter().submitInfo(param);
    }

    /**
     * 创建一个编辑对话框
     *
     * @param title     标题
     * @param maxLength 输入最大字符串长度
     * @param hint      提示文字
     * @param text      显示选择内容控件
     */

    public void createEditDialog(String title, int maxLength, String hint, TextView text) {
        new MaterialDialog.Builder(this)
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

    /**
     * 创建一个单选项对话框
     *
     * @param title    标题
     * @param strArray 数组
     * @param display  显示选择后的内容控件
     */
    public void createSingleChoiceDialog(String title, int strArray, TextView display) {
        new MaterialDialog.Builder(this)
                .title(title)
                .items(strArray)
                .itemsCallbackSingleChoice(-1, (dialog, view, which, text) -> {
                    /**
                     * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                     * returning false here won't allow the newly selected radio button to actually be selected.
                     **/
                    if (text == null) {
                        Utils.Toast("请重新选择");
                        return false;
                    }
                    display.setText(text.toString());
                    return true;
                })
                .positiveText("确定")
                .show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_info_modify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.submit:
                submitInfo();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
