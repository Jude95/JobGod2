package com.ant.jobgod.jobgod.module.main;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.util.BaseViewHolder;
import com.ant.jobgod.jobgod.util.RecyclerArrayAdapter;
import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by alien on 2015/7/9.
 */
public class SimpleAdapter extends RecyclerArrayAdapter<String> {

    public SimpleAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout linearLayout=new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        TextView textView=new TextView(getContext());
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, Utils.dip2px(12), 0, Utils.dip2px(12));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setLayoutParams(layoutParams);
        textView.setId(R.id.text);
        linearLayout.addView(textView);

        return new RegionViewHolder(linearLayout);
    }

    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        super.OnBindViewHolder(holder, position);
    }

    class RegionViewHolder extends BaseViewHolder<String>{

        public RegionViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(String data) {
            super.setData(data);
            TextView textView= (TextView) itemView.findViewById(R.id.text);
            textView.setText(data);
        }
    }
}
