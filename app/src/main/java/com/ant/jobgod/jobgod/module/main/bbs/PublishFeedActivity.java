package com.ant.jobgod.jobgod.module.main.bbs;

import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.LocationModel;
import com.ant.jobgod.jobgod.util.Utils;
import com.umeng.comm.core.beans.CommConfig;
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

    private LocalImageListAdapter imgListAdapter;
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
        imgListAdapter = new LocalImageListAdapter(this, R.layout.view_nomore);
        feedItem = new FeedItem();

        commUser = CommConfig.getConfig().loginedUser;
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(imgListAdapter);

        getImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getImgFromAlbum();
            }
        });

    }

    public void setOneImg(ImageItem item) {
        if (imgListAdapter.getCount() >= 9) {
            Utils.Toast("最多只能添加9张");
            return;
        }
        imgListAdapter.add(item);
        imgListAdapter.notifyDataSetChanged();
        listImg.add(item);
        feedItem.imageUrls.add(item);
        Utils.Log("count:" + imgListAdapter.getCount());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bbs_publish, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.submit) {

            feedItem.text = content.getText().toString().trim();
            feedItem.location = new Location("");
            feedItem.locationAddr = LocationModel.getInstance().getCurLocation().getAddress();
            feedItem.type = feedItem.creator.permisson == CommUser.Permisson.ADMIN ? 1 : 0;
            feedItem.creator = CommConfig.getConfig().loginedUser;
            showProgress("发布中");
            getPresenter().publishFeed(feedItem, new Listeners.SimpleFetchListener<FeedItemResponse>() {
                @Override
                public void onComplete(FeedItemResponse feedItemResponse) {
                    Utils.Log("发布后返回的数据：" + feedItemResponse);
                    dismissProgress();
                    if (feedItemResponse.errCode == 0) {
                        finish();
                    } else
                        Utils.Log("发布失败");
                }

            });


        }
        return super.onOptionsItemSelected(item);
    }

}
