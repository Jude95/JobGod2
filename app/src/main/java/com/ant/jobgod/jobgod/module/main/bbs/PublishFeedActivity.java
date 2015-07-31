package com.ant.jobgod.jobgod.module.main.bbs;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.bean.AccountData;
import com.ant.jobgod.jobgod.util.Utils;
import com.umeng.comm.core.beans.CommUser;
import com.umeng.comm.core.beans.FeedItem;
import com.umeng.comm.core.beans.ImageItem;
import com.umeng.comm.core.listeners.Listeners;
import com.umeng.comm.core.nets.responses.FeedItemResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by alien on 2015/7/30.
 */
@RequiresPresenter(PublishFeedPresenter.class)
public class PublishFeedActivity extends BaseActivity<PublishFeedPresenter> {


    @InjectView(R.id.content)
    EditText content;
    @InjectView(R.id.getImg)
    ImageView getImg;
    
    private ImgListAdapter adapter;

    private GridView gridView;

    private FeedItem feedItem;

    private CommUser commUser;

    private List<ImageItem> listImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbs_activity_publishfeed);
        ButterKnife.inject(this);

        listImg = new ArrayList<>();

        adapter = new ImgListAdapter(this, R.layout.view_nomore);

        commUser = new CommUser();
        feedItem = new FeedItem();
        AccountData accountData = AccountModel.getInstance().getAccount();
        commUser.name = accountData.getName();
        commUser.iconUrl = accountData.getFace();
        commUser.id = accountData.getId() + "";
        feedItem.creator = commUser;

        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(adapter);

        getImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getImgs();
            }
        });
    }

    public void setImg(ImageItem item) {
        if(adapter.getCount()>=9){
            Utils.Toast("最多只能添加9张");
            return;
        }
        adapter.add(item);
        adapter.notifyDataSetChanged();
        listImg.add(item);
        Utils.Log("count:" + adapter.getCount());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bbs_publish, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.submit) {
            feedItem.imageUrls = listImg;
            feedItem.text = content.getText().toString();
            Utils.Log("feeditem:"+feedItem );

            getPresenter().publishFeed(feedItem, new Listeners.SimpleFetchListener<FeedItemResponse>() {
                @Override
                public void onComplete(FeedItemResponse feedItemResponse) {
                    Utils.Toast("发布成功");
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}
