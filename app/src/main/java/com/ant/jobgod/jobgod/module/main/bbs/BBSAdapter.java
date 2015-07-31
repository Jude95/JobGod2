package com.ant.jobgod.jobgod.module.main.bbs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.PersonBriefModel;
import com.ant.jobgod.jobgod.model.SocietyModel;
import com.ant.jobgod.jobgod.util.RecentDateFormater;
import com.ant.jobgod.jobgod.util.TimeTransform;
import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.umeng.comm.core.beans.CommUser;
import com.umeng.comm.core.beans.Comment;
import com.umeng.comm.core.beans.FeedItem;
import com.umeng.comm.core.listeners.Listeners;
import com.umeng.comm.core.nets.responses.SimpleResponse;
import com.umeng.comm.ui.widgets.WrapperGridView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by alien on 2015/7/29.
 */
public class BBSAdapter extends RecyclerArrayAdapter<FeedItem> {

    public BBSAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BBSViewHolder(viewGroup);
    }

    class BBSViewHolder extends BaseViewHolder<FeedItem> {

        @InjectView(R.id.relativeLayout)
        RelativeLayout relativeLayout;
        @InjectView(R.id.imgs)
        ViewStub imgs;
        @InjectView(R.id.face)
        SimpleDraweeView face;
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.sign)
        TextView sign;
        @InjectView(R.id.mainContent)
        TextView mainContent;
        @InjectView(R.id.forardText)
        TextView forardText;
        @InjectView(R.id.location)
        TextView location;
        @InjectView(R.id.time)
        TextView time;
        @InjectView(R.id.forward)
        TextView forward;
        @InjectView(R.id.comment)
        TextView comment;
        @InjectView(R.id.view)
        LinearLayout view;

        private TextView like;

        public BBSViewHolder(ViewGroup parent) {
            super(parent, R.layout.bbs_item_fragment);
            ButterKnife.inject(this, itemView);

            like = (TextView) itemView.findViewById(R.id.like);
        }

        @Override
        public void setData(FeedItem data) {
            super.setData(data);
            long timeNum = Long.parseLong(data.publishTime) / 1000;
            time.setText(new TimeTransform(timeNum).toString(new RecentDateFormater()));
            forward.setText(data.forwardCount + "");
            comment.setText(data.commentCount + "");
            location.setText(data.locationAddr);
            mainContent.setText(data.text);
            face.setImageURI(Uri.parse(data.creator.iconUrl));
            name.setText(data.creator.name);
            sign.setText(PersonBriefModel.getInstance().getPersonBriefOnBlock(data.creator.id).getSign());

            Utils.Log(PersonBriefModel.getInstance().getPersonBriefOnBlock(data.creator.id).getSign() + "");
            Utils.Toast("creatorid:"+data.creator.id);

            if (data.sourceFeed != null) {
                forardText.setText(data.sourceFeed.text);
            }
            //添加图片显示出来
            if (data.getImages().size() > 0) {
                setImgs(data);
            }
            else
                relativeLayout.setVisibility(View.GONE);

            if (data.sourceFeed != null && data.sourceFeed.getImages().size() > 0) {
                setImgs(data.sourceFeed);
            }
            else
                relativeLayout.setVisibility(View.GONE);


            //点赞或取消赞
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (like.isClickable()) {
                        SocietyModel.getInstance().like(data.sourceFeedId, new Listeners.SimpleFetchListener<SimpleResponse>() {
                            @Override
                            public void onComplete(SimpleResponse simpleResponse) {
                                int num = Integer.parseInt(like.getText().toString());
                                like.setText(num + 1 + "");
                            }
                        });
                    } else
                        SocietyModel.getInstance().unLike(data.sourceFeedId, new Listeners.SimpleFetchListener<SimpleResponse>() {
                            @Override
                            public void onComplete(SimpleResponse simpleResponse) {
                                int num = Integer.parseInt(like.getText().toString());
                                like.setText(num - 1 + "");
                            }
                        });
                }
            });

            //转发
            forward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ForwardActivity.class);
                    intent.putExtra("feed", data);
                    getContext().startActivity(intent);
                }
            });

            //评论
            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new MaterialDialog.Builder(getContext())
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
                                    Comment c = new Comment();
                                    c.createTime = System.currentTimeMillis() + "";
                                    c.feedId = data.id;
                                    c.text = input.toString();

                                    CommUser user = new CommUser();
                                    user.iconUrl = AccountModel.getInstance().getAccount().getFace();
                                    user.name = AccountModel.getInstance().getAccount().getName();
                                    c.creator = user;

                                    SocietyModel.getInstance().comment(c, new Listeners.FetchListener<SimpleResponse>() {
                                        @Override
                                        public void onStart() {
                                        }

                                        @Override
                                        public void onComplete(SimpleResponse simpleResponse) {
                                            comment.setText(data.commentCount + 1 + "");
                                            Utils.Toast("评论成功");
                                        }
                                    });
                                }
                            }).show();
                }
            });

            view.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), BBSDetailActivity.class);
                intent.putExtra("feedId", data.id);
                getContext().startActivity(intent);
            });
        }

        /**
         * 设置图片显示
         */
        public void setImgs(FeedItem data) {
            View item = imgs.inflate();
            WrapperGridView gridView = (WrapperGridView) item.findViewById(R.id.umeng_comm_msg_gridview);
            ImgListAdapter adapter = new ImgListAdapter(getContext(), R.layout.view_empty);
            adapter.addAll(data.getImages());
            gridView.setAdapter(adapter);
        }


    }
}
