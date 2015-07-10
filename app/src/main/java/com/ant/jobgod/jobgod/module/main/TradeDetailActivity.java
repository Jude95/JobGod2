package com.ant.jobgod.jobgod.module.main;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.module.job.JobBriefAdapter;
import com.ant.jobgod.jobgod.util.BaseViewHolder;
import com.ant.jobgod.jobgod.util.RecyclerArrayAdapter;
import com.ant.jobgod.jobgod.util.Utils;
import com.ant.jobgod.jobgod.widget.RegionView;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(TradeDetailPresenter.class)
public class TradeDetailActivity extends BaseActivity<TradeDetailPresenter> {

    @InjectView(R.id.jobs_list)
    SuperRecyclerView jobsList;
    @InjectView(R.id.btnRegion)
    LinearLayout btnRegion;
    @InjectView(R.id.btnType)
    LinearLayout btnType;
    @InjectView(R.id.btnSort)
    LinearLayout btnSort;
    @InjectView(R.id.selectGroup)
    LinearLayout selectGroup;
    private int IS_OPEN_POPUP_WINDOW = 0;
    private int IS_FIRST_CREATE_POPUPWINDOW = 0;
    private JobBriefAdapter adapter;

    private PopupWindow mRegionPopupWindow;
    private RegionView mRegionView;
    private TypeAdapter typeAdapter;
    private List<String> provinceList, cityList, regionList;

    private PopupWindow mTypePopupWindow;

    private PopupWindow mSortPopupWindow;
    private List<String> sorts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_trade_detail);
        ButterKnife.inject(this);
        setTitle("兼职列表");
        /*加载job列表*/
        jobsList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new JobBriefAdapter(this);
        jobsList.setAdapter(adapter);
        /*第一个弹出窗口*/
        mRegionView = new RegionView(this, Utils.dip2px(240));
        mRegionView.setData(provinceList, cityList, regionList);

        typeAdapter=new TypeAdapter(this,R.layout.main_item_hotjob);

        createRegionPopupWindow();
        createTypePopupWindow();
        ctreateSortPopupWindow();

        btnRegion.setOnClickListener(v -> {
            if (IS_OPEN_POPUP_WINDOW == 0) {
                mRegionPopupWindow.showAsDropDown(selectGroup);
                IS_OPEN_POPUP_WINDOW = 1;
            } else {
                mRegionPopupWindow.dismiss();
                IS_OPEN_POPUP_WINDOW = 0;
            }
        });

        btnType.setOnClickListener(v -> {
            if (IS_OPEN_POPUP_WINDOW == 0) {
                mTypePopupWindow.showAsDropDown(v);
                IS_OPEN_POPUP_WINDOW = 1;
            } else {
                mTypePopupWindow.dismiss();
                IS_OPEN_POPUP_WINDOW = 0;
            }
        });
        btnSort.setOnClickListener(v -> {
            if (IS_OPEN_POPUP_WINDOW == 0) {
                mSortPopupWindow.showAsDropDown(selectGroup);
                IS_OPEN_POPUP_WINDOW = 1;
            } else {
                mSortPopupWindow.dismiss();
                IS_OPEN_POPUP_WINDOW = 0;
            }
        });

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void createRegionPopupWindow() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,Utils.dip2px(240)));

        View view = new View(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(4)));
        view.setBackground(getResources().getDrawable(R.drawable.bottom_shadow));

        linearLayout.addView(mRegionView);
        linearLayout.addView(view);

        mRegionPopupWindow = new PopupWindow(linearLayout, Utils.getScreenWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
        mRegionPopupWindow.setTouchable(false);
        mRegionPopupWindow.setFocusable(true);
    }

    public void setRegionData(List<String> provinceList, List<String> cityList, List<String> regionList) {
        this.provinceList = provinceList;
        this.cityList = cityList;
        this.regionList = regionList;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void createTypePopupWindow(){
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(240)));

        GridView gridView = new GridView(this);
        gridView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        gridView.setNumColumns(3);
        gridView.setBackgroundColor(getResources().getColor(R.color.bg));
        gridView.setHorizontalSpacing(Utils.dip2px(0.5f));
        gridView.setVerticalSpacing(Utils.dip2px(0.5f));
        gridView.setPadding(Utils.dip2px(0.5f), Utils.dip2px(0.5f), Utils.dip2px(0.5f), Utils.dip2px(0.5f));

        View view = new View(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(4)));
        view.setBackground(getResources().getDrawable(R.drawable.bottom_shadow));

        linearLayout.setBackgroundColor(Color.WHITE);
        gridView.setAdapter(typeAdapter);
        linearLayout.addView(gridView);
        linearLayout.addView(view);

        mTypePopupWindow=new PopupWindow(linearLayout,Utils.getScreenWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
        mTypePopupWindow.setFocusable(true);
        mTypePopupWindow.setTouchable(false);
    }

    public void setTypeData(List<String> data){
        typeAdapter.addAll(data);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void ctreateSortPopupWindow(){
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        for(int i=0;i<5;i++){
            TextView textView=new TextView(this);
            textView.setText(sorts.get(i));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(16);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, Utils.dip2px(12), 0, Utils.dip2px(12));
            textView.setLayoutParams(layoutParams);

            View view =new View(this);
            view.setBackgroundColor(R.color.divide);
            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(0.4f)));
            linearLayout.addView(view);

            linearLayout.setBackgroundColor(Color.WHITE);
            linearLayout.addView(textView);
        }

        View view = new View(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(4)));
        view.setBackground(getResources().getDrawable(R.drawable.bottom_shadow));
        linearLayout.addView(view);

        mSortPopupWindow=new PopupWindow(linearLayout,Utils.getScreenWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
        mSortPopupWindow.setFocusable(true);
        mSortPopupWindow.setTouchable(false);
    }

    public void setSortsData(List<String> data){
        sorts=data;
    }

    public void setJobListData(JobBrief[] jobBrief) {
        adapter.clear();
        adapter.addAll(jobBrief);
    }

    class TypeAdapter extends ArrayAdapter<String>{

        public TypeAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView text=new TextView(getContext());
            text.setGravity(Gravity.CENTER);
            text.setTextSize(16);
            text.setPadding(0,Utils.dip2px(8),0,Utils.dip2px(8));
            text.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            text.setText(getItem(position));
            return text;
        }

        @Override
        public int getCount() {
            return super.getCount();
        }

    }

    /**
     * Created by alien on 2015/7/9.
     */
    public static class SimpleAdapter extends RecyclerArrayAdapter<String> {

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
}
