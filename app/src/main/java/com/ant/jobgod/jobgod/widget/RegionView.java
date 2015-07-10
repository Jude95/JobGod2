package com.ant.jobgod.jobgod.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.module.main.TradeDetailActivity;
import com.ant.jobgod.jobgod.util.Utils;

import java.util.List;

/**
 * Created by alien on 2015/7/9.
 */
public class RegionView extends LinearLayout {

    private RecyclerView province;
    private TradeDetailActivity.SimpleAdapter provinceAdapter;
    private RecyclerView city;
    private TradeDetailActivity.SimpleAdapter cityAdapter;
    private RecyclerView region;
    private TradeDetailActivity.SimpleAdapter regionAdapter;
    private int height;
    private List<String> provinceList,cityList,regionList;

    public RegionView(Context context,int height) {
        super(context);
        this.height=height;
        initView();
    }

    public RegionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initView(){
        setOrientation(HORIZONTAL);
        setBackgroundColor(Color.WHITE);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));

        province=new RecyclerView(getContext());
        city=new RecyclerView(getContext());
        region=new RecyclerView(getContext());
        province.setLayoutManager(new LinearLayoutManager(getContext()));
        city.setLayoutManager(new LinearLayoutManager(getContext()));
        region.setLayoutManager(new LinearLayoutManager(getContext()));

        province.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        city.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        region.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));

        provinceAdapter=new TradeDetailActivity.SimpleAdapter(getContext());
        province.setAdapter(provinceAdapter);
        cityAdapter=new TradeDetailActivity.SimpleAdapter(getContext());
        city.setAdapter(cityAdapter);
        regionAdapter=new TradeDetailActivity.SimpleAdapter(getContext());
        region.setAdapter(regionAdapter);

        View view1 = new View(getContext());
        View view2 = new View(getContext());
        view1.setLayoutParams(new LayoutParams(Utils.dip2px(0.8f),ViewGroup.LayoutParams.MATCH_PARENT));
        view2.setLayoutParams(new LayoutParams(Utils.dip2px(0.8f),ViewGroup.LayoutParams.MATCH_PARENT));
        view1.setBackgroundColor(R.color.divide);
        view2.setBackgroundColor(R.color.divide);

        addView(province);
        addView(view1);
        addView(city);
        addView(view2);
        addView(region);

    }

    /**
     * 给三个recycleview绑定相应的数据
     * @param provinceList
     * @param cityList
     * @param regionList
     */
    public void setData(List<String> provinceList,List<String> cityList,List<String> regionList){
        this.provinceList=provinceList;
        this.cityList=cityList;
        this.regionList=regionList;
        provinceAdapter.addAll(provinceList);
        cityAdapter.addAll(cityList);
        regionAdapter.addAll(regionList);
    }

}
