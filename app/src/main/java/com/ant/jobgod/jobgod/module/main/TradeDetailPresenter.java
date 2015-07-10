package com.ant.jobgod.jobgod.module.main;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.LocationModel;
import com.ant.jobgod.jobgod.model.bean.JobPage;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alien on 2015/7/9.
 */
public class TradeDetailPresenter extends BasePresenter<TradeDetailActivity> {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setRegionData();
        setSortsData();
    }

    @Override
    protected void onCreateView(TradeDetailActivity view) {
        super.onCreateView(view);
        setTypeData();
    }

    @Override
    protected void onTakeView(TradeDetailActivity view) {
        super.onTakeView(view);
        setJobListData();
    }

    @Override
    protected void onSave(Bundle state) {
        super.onSave(state);
    }

    public void setRegionData(){
        List<String> list1,list2,list3;
        list1=new ArrayList<>();
        list2=new ArrayList<>();
        list3=new ArrayList<>();
        list1.add("重庆");
        list2.add("长寿");
        list3.add("双龙");
        getView().setRegionData(list1, list2, list3);
    }

    public void setTypeData(){
        List<String> types=new ArrayList<>();
        types.add("服务");
        types.add("技术");
        types.add("其他");
        types.add("实习");
        getView().setTypeData(types);
    }

    public void setSortsData(){
        List<String> sorts=new ArrayList<>();
        sorts.add("默认");
        sorts.add("最新");
        sorts.add("最热");
        sorts.add("冷门");
        sorts.add("最有钱");
        getView().setSortsData(sorts);
    }

    public void setJobListData(){
        JobModel.getInstance().getJobList(0, 10, LocationModel.getInstance().getCurLocation().getRegionCode()+"", "", 0, "", new DataCallback<JobPage>() {
            @Override
            public void success(String info, JobPage data) {
                getView().setJobListData(data.getJobs());
            }
        });
    }
}
