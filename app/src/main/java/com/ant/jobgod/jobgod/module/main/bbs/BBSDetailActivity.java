package com.ant.jobgod.jobgod.module.main.bbs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.PersonBriefModel;
import com.ant.jobgod.jobgod.model.SocietyModel;
import com.ant.jobgod.jobgod.util.RecentDateFormater;
import com.ant.jobgod.jobgod.util.TimeTransform;
import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.comm.core.beans.CommUser;
import com.umeng.comm.core.beans.Comment;
import com.umeng.comm.core.beans.FeedItem;
import com.umeng.comm.core.listeners.Listeners;
import com.umeng.comm.core.nets.responses.SimpleResponse;
import com.umeng.comm.ui.widgets.WrapperGridView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by alien on 2015/7/29.
 */
@RequiresPresenter(BBSDetailPresenter.class)
public class BBSDetailActivity extends BaseActivity<BBSDetailPresenter> {


    @InjectView(R.id.face)
    SimpleDraweeView face;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.msgText)
    TextView msgText;
    @InjectView(R.id.forardText)
    TextView forardText;
    @InjectView(R.id.linearLayout)
    LinearLayout linearLayout;
    @InjectView(R.id.like)
    TextView like;
    @InjectView(R.id.viewLike)
    RelativeLayout viewLike;
    @InjectView(R.id.forward)
    TextView forward;
    @InjectView(R.id.viewForward)
    RelativeLayout viewForward;
    @InjectView(R.id.comment)
    TextView comment;
    @InjectView(R.id.viewComment)
    RelativeLayout viewComment;
    @InjectView(R.id.imgs)
    ViewStub imgs;
    @InjectView(R.id.sign)
    TextView sign;

    private FeedItem feedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbs_activity_detail);
        ButterKnife.inject(this);

    }


    public void setData(FeedItem feed) {
        feedItem = feed;
        msgText.setText(feed.text);
        sign.setText(PersonBriefModel.getInstance().getPersonBriefOnBlock(feed.creator.id).getSign());
        if (feedItem.creator.iconUrl != null) {
            face.setImageURI(Uri.parse(feed.creator.iconUrl));
            name.setText(feed.creator.name);

            if (feed.sourceFeed != null)
                forardText.setText(feed.sourceFeed.text);
        }
        if (feed.getImages().size() > 0) {
            setImgs(feed);
        }
        if (feed.sourceFeed != null && feed.sourceFeed.getImages().size() > 0) {
            setImgs(feed.sourceFeed);
        }
        setComments();
    }


    public void setImgs(FeedItem feed) {
        View item = imgs.inflate();
        WrapperGridView gridView = (WrapperGridView) item.findViewById(R.id.umeng_comm_msg_gridview);
        ImgListAdapter adapter = new ImgListAdapter(this, R.layout.view_empty);
        adapter.addAll(feed.getImages());
        gridView.setAdapter(adapter);
    }

    /**
     * 添加评论
     */
    public void setComments() {
        int commentCount = feedItem.commentCount;
        View item = LayoutInflater.from(this).inflate(R.layout.bbs_item_comment, null);
        SimpleDraweeView face = (SimpleDraweeView) item.findViewById(R.id.face);
        TextView name = (TextView) item.findViewById(R.id.name);
        TextView content = (TextView) item.findViewById(R.id.content);
        TextView date = (TextView) item.findViewById(R.id.date);

        /**
         * 评论的添加
         */
        Utils.Log("commentCount:" + commentCount);
        for (int i = 0; i < commentCount; i++) {
            Comment comment = feedItem.comments.get(i);
            long time = Long.parseLong(comment.createTime) / 1000;

            face.setImageURI(Uri.parse(comment.creator.iconUrl));
            name.setText(comment.creator.name);
            content.setText(comment.text);
            date.setText(new TimeTransform(time).toString(new RecentDateFormater()));

            linearLayout.addView(item);
        }

        //点赞
        viewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (like.isClickable()) {
                    SocietyModel.getInstance().like(feedItem.sourceFeedId, new Listeners.SimpleFetchListener<SimpleResponse>() {
                        @Override
                        public void onComplete(SimpleResponse simpleResponse) {
                            int num = Integer.parseInt(like.getText().toString());
                        }
                    });
                } else
                    SocietyModel.getInstance().unLike(feedItem.sourceFeedId, new Listeners.SimpleFetchListener<SimpleResponse>() {
                        @Override
                        public void onComplete(SimpleResponse simpleResponse) {
                            int num = Integer.parseInt(like.getText().toString());
                        }
                    });
            }
        });

        //转发
        viewForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BBSDetailActivity.this, ForwardActivity.class);
                intent.putExtra("feed", feedItem);
                startActivity(intent);
            }
        });

        //评论
        viewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(BBSDetailActivity.this)
                        .title("评论")
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .inputMaxLength(32)
                        .input("", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                if (input.toString().trim().isEmpty()) {
                                    Utils.Toast("不能为空");
                                    return;
                                }
                                Comment comment = new Comment();
                                comment.createTime = System.currentTimeMillis() + "";
                                comment.creator = new CommUser();
                                if (getIntent().getParcelableExtra("feed") != null) {
                                    comment.feedId = ((FeedItem) getIntent().getParcelableExtra("feed")).id;
                                }
                                comment.text = input.toString();

                                SocietyModel.getInstance().comment(comment, new Listeners.FetchListener<SimpleResponse>() {
                                    @Override
                                    public void onStart() {

                                    }

                                    @Override
                                    public void onComplete(SimpleResponse simpleResponse) {
                                        Utils.Toast("评论成功");
                                    }
                                });
                            }
                        }).show();
            }
        });

    }
}
