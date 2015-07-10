package com.ant.jobgod.jobgod.module.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.Banner;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.module.job.JobBriefAdapter;
import com.ant.jobgod.jobgod.module.job.JobDetailActivity;
import com.ant.jobgod.jobgod.widget.LinearWrapContentRecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusFragment;

/**
 * Created by Mr.Jude on 2015/7/10.
 */
@RequiresPresenter(RecommendPresenter.class)
public class RecommendFragment extends NucleusFragment<RecommendPresenter> {
    @InjectView(R.id.vpgAd)
    RollPagerView vpgAd;
    @InjectView(R.id.tvHotJobMore)
    TextView tvHotJobMore;
    @InjectView(R.id.vpgHotJob)
    RollPagerView vpgHotJob;
    @InjectView(R.id.tvTopicMore)
    TextView tvTopicMore;
    @InjectView(R.id.viewTopic)
    TopicsView viewTopic;
    @InjectView(R.id.tvGuessMore)
    TextView tvGuessMore;
    @InjectView(R.id.listGuess)
    LinearWrapContentRecyclerView listGuess;

    private AdAdapter mAdAdapter;
    private HotJobAdapter mHotJobAdapter;
    private JobBriefAdapter mGuessAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View rootView = inflater.inflate(R.layout.main_fragment_recommend, container, false);
        ButterKnife.inject(this, rootView);
        vpgAd.setAdapter(mAdAdapter = new AdAdapter(getActivity()));
        vpgHotJob.setAdapter(mHotJobAdapter = new HotJobAdapter(getActivity()));
        listGuess.setAdapter(mGuessAdapter = new JobBriefAdapter(getActivity()));
        return rootView;
    }

    public void setAd(Banner[] banner){
        mAdAdapter.setData(banner);
    }
    public void setHotJob(JobBrief[] jobs){
        mHotJobAdapter.setData(jobs);
    }
    public void setTopic(Topic[] topics){
        viewTopic.setTopic(topics);
    }

    public void setGuess(JobBrief[] jobs){
        mGuessAdapter.clear();
        mGuessAdapter.addAll(jobs);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    /**
     * Created by alien on 2015/7/7.
     */
    public static class AdAdapter extends StaticPagerAdapter {

        @InjectView(R.id.sdvAdImg)
        SimpleDraweeView sdvAdImg;
        private Banner[] banners;
        private Context context;

        public AdAdapter(Context context) {
            this.context = context;
        }

        @Override
        public View getView(ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.main_item_ad, container, false);
            ButterKnife.inject(this, view);
            sdvAdImg.setImageURI(Uri.parse(banners[position].getImg()));
            return view;
        }

        @Override
        public int getCount() {
            return banners==null?0:banners.length;
        }

        public void setData(Banner[] banners) {
            this.banners = banners;
            notifyDataSetChanged();
        }
    }

    /**
     * Created by alien on 2015/7/7.
     */
    public static class HotJobAdapter extends StaticPagerAdapter {

        SimpleDraweeView sdvHotJobImg;
        TextView tvTitle;
        private Context context;
        private JobBrief[] data;

        public HotJobAdapter(Context context) {
            this.context = context;
        }

        @Override
        public View getView(ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(context);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            for(int i=0;i<3;i++){
                View item = inflater.inflate(R.layout.main_item_hotjob, container, false);
                item.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
                sdvHotJobImg= (SimpleDraweeView) item.findViewById(R.id.sdvHotJobImg);
                tvTitle= (TextView) item.findViewById(R.id.tvTitle);
                int index = position * 3+i;
                if (index<data.length){
                    sdvHotJobImg.setImageURI(Uri.parse(data[index].getImg()));
                    tvTitle.setText(data[index].getTitle());
                }
                item.setOnClickListener(v -> {
                    Intent intent=new Intent(item.getContext(), JobDetailActivity.class);
                    intent.putExtra("id",data[index].getId());
                    item.getContext().startActivity(intent);
                });
                linearLayout.addView(item);
            }
            return linearLayout;
        }

        @Override
        public int getCount() {
            return data == null ? 0 : data.length % 3 == 0 ? data.length / 3 : data.length / 3 + 1;
        }

        public void setData(JobBrief[] data) {
            this.data = data;
            notifyDataSetChanged();
        }
    }
}
