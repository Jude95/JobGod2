package com.ant.jobgod.jobgod.model;

import android.content.Context;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.io.File;

/**
 * Created by zhuchenxi on 15/7/21.
 */
public class RemoteFileModel extends AbsModel {
    public static RemoteFileModel getInstance() {
        return getInstance(RemoteFileModel.class);
    }
    public static final String ADDRESS = "";
    private String token;
    private UploadManager mUploadManager;
    public interface UploadListener{
        void onComplete(String path);
    }

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);

    }

    /**
     *
     * @param file 需上传文件
     * @return 上传文件访问地址
     */
    public String put(File file,UploadListener listener){
        String name = "u"+AccountModel.getInstance().getAccount().getId()+System.currentTimeMillis();
        String path = ADDRESS+name;
        mUploadManager.put(file, name, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                listener.onComplete(path);
            }
        },null);
        return name;
    }

}
