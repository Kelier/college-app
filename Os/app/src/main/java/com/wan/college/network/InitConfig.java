package com.wan.college.network;

import android.app.Application;
import android.content.IntentFilter;
import android.util.Log;

import com.wan.college.utils.NetUtils;

import org.xutils.x;


/**
 * Created by 万文杰 on 2016/12/28.
 */

public class InitConfig extends Application{
    static final String TAG="test";
    public static String baseUrl="http://10.10.149.128:8080/College/";
    //保存图片本地路径
    private String cachePath ;
    private static final String IMAGE_FILE_NAME = "faceImage.jpg";
    private static final String IMAGE_FILE_TEMPNAME = "tempImage.jpg";
    //申明变量保存网络状态
    private static Application mApplication;
    public static int mNetWorkState;
    public void onCreate(){
        super.onCreate();
        mApplication = this;
        x.Ext.init(this);//初始化Xutils
        x.Ext.setDebug(true);//开启调试模式
        cachePath= getExternalCacheDir().getAbsolutePath() + "/";
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(new NetBroadcastReceiver(),intentFilter);

    }
    public static  void log(String info){
        Log.i(TAG,info);
    }
    public String getCachePath() {
        return cachePath;
    }
    public String getUserPhoto() {
        return cachePath + IMAGE_FILE_NAME ;
    }
    public String getTempPhoto() {
        return cachePath + IMAGE_FILE_TEMPNAME ;
    }
}
