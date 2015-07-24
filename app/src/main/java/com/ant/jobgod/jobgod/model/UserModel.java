package com.ant.jobgod.jobgod.model;

import android.content.Context;

import com.android.http.RequestManager;
import com.android.http.RequestMap;
import com.ant.jobgod.jobgod.config.API;
import com.ant.jobgod.jobgod.model.bean.AroundPersonBriefPage;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.PersonBrief;
import com.ant.jobgod.jobgod.model.bean.UserDetail;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

/**
 * Created by Mr.Jude on 2015/6/6.
 * 关于用户数据的处理。
 */
public class UserModel extends AbsModel {
    public static UserModel getInstance() {
        return getInstance(UserModel.class);
    }

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        getJoin(new DataCallback<JobBrief[]>() {
            @Override
            public void success(String info, JobBrief[] data) {
                //Do nothing.because I can't write null for the callback.
            }
        });
    }

    public void modifyName(String name, StatusCallback callback) {
        RequestManager.getInstance().post(API.URL.ModifyName, new RequestMap("name", name), callback);
    }

    public void modifySign(String sign, StatusCallback callback) {
        RequestManager.getInstance().post(API.URL.ModifySign, new RequestMap("sign", sign), callback);
    }

    public void modifyFace(String face, StatusCallback callback) {
        RequestManager.getInstance().post(API.URL.ModifyFace, new RequestMap("face", face), callback);
    }


    public void getCollection(DataCallback<JobBrief[]> callback) {
        RequestManager.getInstance().post(API.URL.GetCollections, null, callback);
    }

    public void getAttentionFromMe(DataCallback<PersonBrief[]> callback) {
        RequestManager.getInstance().post(API.URL.GetAttentionFromMe, null, callback);
    }

    public void getAttentionToMe(DataCallback<PersonBrief[]> callback) {
        RequestManager.getInstance().post(API.URL.GetAttentionToMe, null, callback);
    }

    public void getJoin(DataCallback<JobBrief[]> callback) {
        RequestManager.getInstance().post(API.URL.GetJoin, null, callback.add(new DataCallback<JobBrief[]>() {
            @Override
            public void success(String info, JobBrief[] data) {
                RongYunModel.getInstance().syncGroups(data);
            }
        }));
    }

    /**
     * 获取用户的信息
     */
    public void getUserDetail(int id,DataCallback<UserDetail> callback){
        RequestMap param=new RequestMap("id",id+"");
        RequestManager.getInstance().post(API.URL.GetUserDetail,param,callback);
    }

    /**
     * 更新个人信息
     * @param userDetail
     * @param callback
     */
    public void updateMyDetail(UserDetail userDetail, StatusCallback callback) {
        RequestMap params = new RequestMap();

        params.put("eduLevel", userDetail.getEduLevel()+"");
        params.put("major", userDetail.getMajor());
        params.put("school", userDetail.getSchool());
        params.put("birthday", userDetail.getBirthday() + "");
        params.put("gender", userDetail.getGender() + "");
        params.put("height", userDetail.getHeight() + "");
        params.put("award", userDetail.getAward());
        params.put("certificate", userDetail.getCertificate());
        params.put("character", userDetail.getCharacter());
        params.put("like", userDetail.getLike());
        params.put("specialty", userDetail.getSpecialty());
        params.put("intro", userDetail.getIntro());
        params.put("address",userDetail.getAddress());
        RequestManager.getInstance().post(API.URL.UpdateUserDetail, params, callback);
    }

    /**
     * 获取附近的人
     * @param page
     * @param count
     * @param callback
     */
    public void getAroundUsers(int page, int count, DataCallback<AroundPersonBriefPage> callback) {
        RequestMap params = new RequestMap();
        params.put("page", page + "");
        params.put("count", count + "");
        RequestManager.getInstance().post(API.URL.GetAroundFriends, params, callback);
    }

    /**
     *关注他人
     * @param id
     * @param callback
     */
    public void attention(int id, StatusCallback callback) {
        RequestManager.getInstance().post(API.URL.Attention, new RequestMap("id", id+""), callback);
    }

    /**
     * 取消关注他人
     * @param id
     * @param callback
     */
    public void unAttention(int id, StatusCallback callback) {
        RequestManager.getInstance().post(API.URL.UnAttention, new RequestMap("id", id+""), callback);
    }

    /**
     * 提交身份验证信息
     */
    public void certificate(String idCard,String realName,String img,StatusCallback callback){
        RequestMap param=new RequestMap();
        param.put("id_card",idCard);
        param.put("real_name",realName);
        param.put("img",img);
        RequestManager.getInstance().post(API.URL.Certificate,param,callback);
    }

    /**
     * 获取七牛的token
     */
    public void getQiniuToken(DataCallback<String> callback){
        RequestManager.getInstance().get(API.URL.QiniuToken, callback);
    }

    /**
     * 上传图片到七牛
     * @param path
     * @param handler
     */
    public void uploadToQiniu(String path,UpCompletionHandler handler){
        getQiniuToken(new DataCallback<String>() {
            @Override
            public void success(String ifo, String data) {
                Utils.Log("token:" + data);
                UploadManager uploadManager = new UploadManager();
                /**
                 * 参数null要改动
                 */
                uploadManager.put(path, path, data, handler, null);
            }
        });
    }

    /**
     * 上传图片地址到业务服务器
     * @param smallImg
     * @param largeImg
     * @param callback
     */
    public void uploadToServer(String smallImg,String largeImg,StatusCallback callback){
        RequestMap param=new RequestMap();
        param.put("small",smallImg);
        param.put("large",largeImg);
        RequestManager.getInstance().post(API.URL.ModFace,param,callback);
    }
}
