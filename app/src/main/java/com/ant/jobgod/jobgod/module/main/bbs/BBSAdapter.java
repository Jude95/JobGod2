package com.ant.jobgod.jobgod.module.main.bbs;

import android.content.Context;
import android.net.Uri;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.SocietyModel;
import com.ant.jobgod.jobgod.util.RecentDateFormater;
import com.ant.jobgod.jobgod.util.TimeTransform;
import com.ant.jobgod.jobgod.util.Utils;
import com.ant.jobgod.jobgod.widget.LinearWrapContentRecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.umeng.comm.core.beans.CommConfig;
import com.umeng.comm.core.beans.Comment;
import com.umeng.comm.core.beans.FeedItem;
import com.umeng.comm.core.listeners.Listeners;
import com.umeng.comm.core.nets.responses.CommentResponse;
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

    private int ZAN = 1;

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BBSViewHolder(viewGroup);
    }

    class BBSViewHolder extends BaseViewHolder<FeedItem> {

        @InjectView(R.id.face)
        SimpleDraweeView face;
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.location)
        TextView location;
        @InjectView(R.id.mainContent)
        TextView mainContent;
        @InjectView(R.id.imgs)
        ViewStub imgs;
        @InjectView(R.id.relativeLayout)
        RelativeLayout relativeLayout;
        @InjectView(R.id.commentList)
        LinearWrapContentRecyclerView commentList;
        @InjectView(R.id.time)
        TextView time;
        @InjectView(R.id.likeImg)
        TextView likeImg;
        @InjectView(R.id.commentImg)
        TextView commentImg;
        @InjectView(R.id.commentCount)
        TextView commentCount;

        private CommentAdapter commentAdapter;

        public BBSViewHolder(ViewGroup parent) {
            super(parent, R.layout.bbs_item_fragment);
            ButterKnife.inject(this, itemView);
            commentAdapter = new CommentAdapter(getContext());
        }

        public void setCommentData(String feedId) {

            SocietyModel.getInstance().getComments(feedId, new Listeners.SimpleFetchListener<CommentResponse>() {
                @Override
                public void onComplete(CommentResponse commentResponse) {
                    commentAdapter.clear();
                    if (commentResponse.result.size() < 6) {
                        commentAdapter.addAll(commentResponse.result);
                    } else {
                        for (int i = 0; i < 5; i++) {
                            commentAdapter.add(commentResponse.result.get(i));
                        }
                        commentAdapter.add(new Comment());
                        commentList.getChildAt(5).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                commentAdapter.clear();
                                commentAdapter.addAll(commentResponse.result);
                                commentAdapter.add(new Comment());
                                ((TextView) commentList.getChildAt(commentList.getChildCount() - 1)).setText("点击收起");
                                commentList.getChildAt(commentList.getChildCount() - 1).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        commentAdapter.clear();
                                        for (int i = 0; i < 5; i++) {
                                            commentAdapter.add(commentResponse.result.get(i));
                                            setCommentData(feedId);
                                        }
                                    }
                                });
                            }
                        });
                    }
                }
            });

        }

        /**
         * 设置图片显示
         */
        public void setImgs(FeedItem data) {
            imgs.inflate();
            View view = LayoutInflater.from(getContext()).inflate(R.layout.umeng_comm_images_gv, null);
            WrapperGridView gridView = (WrapperGridView) view.findViewById(R.id.umeng_comm_msg_gridview);
            ImgListAdapter adapter = new ImgListAdapter(getContext(), R.layout.view_empty);
            adapter.addAll(data.getImages());
            gridView.setAdapter(adapter);
        }

        @Override
        public void setData(FeedItem data) {
            super.setData(data);
            long timeNum = Long.parseLong(data.publishTime) / 1000;
            String realName = Utils.base64ToString(data.creator.name);
            String[] sourceStrArray = realName.split("_", 2);
            if (sourceStrArray.length == 1) {
                name.setText(sourceStrArray[0]);
            } else
                name.setText(sourceStrArray[1]);
            time.setText(new TimeTransform(timeNum).toString(new RecentDateFormater()));
            commentImg.setText(data.commentCount + "");
            location.setText(data.locationAddr);
            mainContent.setText(data.text);
            face.setImageURI(Uri.parse(data.creator.iconUrl));
            commentCount.setText("评论" + "(" + data.commentCount + ")");
            Utils.Log("imgs:" + data.imageUrls.size());
            if (data.getImages() != null && data.getImages().size() > 0) {
                setImgs(data.sourceFeed);
            } else
                relativeLayout.setVisibility(View.GONE);

            commentList.setAdapter(commentAdapter);
            setCommentData(data.id);

            //点赞或取消赞
            likeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SocietyModel.getInstance().checkLogin(getContext()))
                        if (ZAN == 1) {
                            SocietyModel.getInstance().like(data.id, new Listeners.SimpleFetchListener<SimpleResponse>() {
                                @Override
                                public void onComplete(SimpleResponse simpleResponse) {
                                    if (simpleResponse.errCode == 0) {
                                        int count = Integer.parseInt(likeImg.getText().toString());
                                        likeImg.setText(count + 1 + "");
                                        likeImg.setPressed(true);
                                        ZAN = 0;
                                    }
                                }
                            });
                        } else
                            SocietyModel.getInstance().unLike(data.id, new Listeners.SimpleFetchListener<SimpleResponse>() {
                                @Override
                                public void onComplete(SimpleResponse simpleResponse) {
                                    if (simpleResponse.errCode == 0) {
                                        int count = Integer.parseInt(likeImg.getText().toString());
                                        likeImg.setText(count - 1 + "");
                                        likeImg.setPressed(false);
                                        ZAN = 1;
                                    }
                                }
                            });
                }
            });

            //评论
            commentImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SocietyModel.getInstance().checkLogin(getContext()))
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
                                        c.creator = CommConfig.getConfig().loginedUser;
                                        Utils.Log("comment--creator--" + c.creator);
                                        SocietyModel.getInstance().comment(c, new Listeners.FetchListener<SimpleResponse>() {
                                            @Override
                                            public void onStart() {

                                            }

                                            @Override
                                            public void onComplete(SimpleResponse simpleResponse) {
                                                Utils.Log("评论评论后返回的数据：" + simpleResponse);
                                                if (simpleResponse.errCode == 0) {
                                                    Utils.Toast("评论成功");
                                                    int commentCount = Integer.parseInt(commentImg.getText().toString());
                                                    commentCount++;
                                                    commentImg.setText(commentCount + "");
                                                }
                                            }
                                        });
                                    }
                                }).show();
                }
            });

        }

    }
}
