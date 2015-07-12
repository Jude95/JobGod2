package com.ant.jobgod.jobgod.module.main.recommend;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.module.job.TopicDetailActivity;
import com.balysv.materialripple.MaterialRippleLayout;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Mr.Jude on 2015/3/27.
 */
public class TopicsView extends RelativeLayout {
    private View container1;
    private TextView title1;
    private TextView intro1;
    private SimpleDraweeView image1;
    private MaterialRippleLayout ripple1;

    private View container2;
    private TextView title2;
    private TextView intro2;
    private SimpleDraweeView image2;
    private MaterialRippleLayout ripple2;

    private View container3;
    private TextView title3;
    private TextView intro3;
    private SimpleDraweeView image3;
    private MaterialRippleLayout ripple3;

    public TopicsView(Context context) {
        this(context, null);
    }

    public TopicsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopicsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_topices,this);
        container1 = view.findViewById(R.id.container1);
        title1 = (TextView) view.findViewById(R.id.title1);
        intro1 = (TextView) view.findViewById(R.id.intro1);
        image1 = (SimpleDraweeView) view.findViewById(R.id.image1);
        ripple1= (MaterialRippleLayout) view.findViewById(R.id.ripple1);
        ripple1.setRippleColor(ripple1.getContext().getResources().getColor(R.color.Grey));

        container2 = view.findViewById(R.id.container2);
        title2 = (TextView) view.findViewById(R.id.title2);
        intro2 = (TextView) view.findViewById(R.id.intro2);
        image2 = (SimpleDraweeView) view.findViewById(R.id.image2);
        ripple2= (MaterialRippleLayout) view.findViewById(R.id.ripple2);
        ripple2.setRippleColor(ripple2.getContext().getResources().getColor(R.color.Grey));

        container3 = view.findViewById(R.id.container3);
        title3 = (TextView) view.findViewById(R.id.title3);
        intro3 = (TextView) view.findViewById(R.id.intro3);
        image3 = (SimpleDraweeView) view.findViewById(R.id.image3);
        ripple3= (MaterialRippleLayout) view.findViewById(R.id.ripple3);
        ripple3.setRippleColor(ripple3.getContext().getResources().getColor(R.color.Grey));
    }

    public void setTopic(final Topic[] topics){
        if (topics.length>0){
            container1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), TopicDetailActivity.class);
                    i.putExtra("topic", topics[0]);
                    v.getContext().startActivity(i);
                }
            });
            title1.setText(topics[0].getTitle());
            intro1.setText(topics[0].getSubTitle());
            image1.setImageURI(Uri.parse(topics[0].getImg()));
        }

        if (topics.length>1){
            container2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), TopicDetailActivity.class);
                    i.putExtra("topic",topics[1]);
                    v.getContext().startActivity(i);
                }
            });
            title2.setText(topics[1].getTitle());
            intro2.setText(topics[1].getSubTitle());
            image2.setImageURI(Uri.parse(topics[1].getImg()));
        }


        if (topics.length>2){
            container3.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), TopicDetailActivity.class);
                    i.putExtra("topic", topics[2]);
                    v.getContext().startActivity(i);
                }
            });
            title3.setText(topics[2].getTitle());
            intro3.setText(topics[2].getSubTitle());
            image3.setImageURI(Uri.parse(topics[2].getImg()));
        }

    }
}
