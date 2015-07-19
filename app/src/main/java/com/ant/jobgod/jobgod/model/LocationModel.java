package com.ant.jobgod.jobgod.model;

import android.content.Context;
import android.os.Bundle;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.android.http.RequestManager;
import com.android.http.RequestMap;
import com.ant.jobgod.jobgod.config.API;
import com.ant.jobgod.jobgod.model.bean.Location;
import com.ant.jobgod.jobgod.util.FileManager;
import com.ant.jobgod.jobgod.util.Utils;

import java.io.File;
import java.io.Serializable;

/**
 * Created by Mr.Jude on 2015/1/28.
 * 地理位置的管理
 */
public class LocationModel extends AbsModel implements Serializable{

    private static final String FILENAME = "location";
    private Location location;

    public static LocationModel getInstance(){
        return getInstance(LocationModel.class);
    }

    public Location getCurLocation(){
        return location;
    }

    @Override
    protected void onAppCreate(Context ctx) {
        File file = FileManager.getInstance().getChild(FileManager.Dir.Object,FILENAME);
        if(file!=null && file.exists()){
            location = (Location) Utils.readObjectFromFile(file);
        }
        if (location == null)location = new Location();
        startLocation(ctx);
    }

    public void startLocation(final Context ctx){
        final LocationManagerProxy mLocationManagerProxy;
        mLocationManagerProxy = LocationManagerProxy.getInstance(ctx);
        mLocationManagerProxy.setGpsEnable(false);
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, 500*1000, 15, new AMapLocationListener() {
                    @Override
                    public void onLocationChanged(AMapLocation aMapLocation) {
                        location.setLocation(aMapLocation);
                        Utils.Log("Save location.AdCode is " + aMapLocation.getAdCode());
                        Utils.writeObjectToFile(location,FileManager.getInstance().getChild(FileManager.Dir.Object,FILENAME));
                        uploadAddress();
                    }


                    @Override
                    public void onLocationChanged(android.location.Location location) {

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                });
    }

    public void uploadAddress(){
        if (AccountModel.getInstance().getAccount() == null)return;
        RequestMap params = new RequestMap();
        params.put("lng",location.getLongitude()+"");
        params.put("lat",location.getLatitude()+"");
        params.put("address",location.getAddress());
        RequestManager.getInstance().post(API.URL.SyncLocation,params,null);
    }
}
