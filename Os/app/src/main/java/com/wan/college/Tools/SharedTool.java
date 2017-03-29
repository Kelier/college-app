package com.wan.college.Tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by 万文杰 on 2017/1/3.
 */

public class SharedTool {

    public static void recordUid(Context context,String uid){
        SharedPreferences spf = context.getSharedPreferences("login_info",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString("uid", uid);
        editor.apply();
    }
    public static String getUid(Context context){
        SharedPreferences spf = context.getSharedPreferences("login_info",context.MODE_PRIVATE);
        return spf.getString("uid",null);
    }

    public static void recordProvince(Context context,String province){
        SharedPreferences spf = context.getSharedPreferences("login_info",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString("province", province);
        editor.apply();
    }

    public static String getProvince(Context context){
        SharedPreferences spf = context.getSharedPreferences("login_info",context.MODE_PRIVATE);
        return spf.getString("province",null);
    }

    public static void recordSubject(Context context,String subject){
        SharedPreferences spf = context.getSharedPreferences("login_info",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString("subject", subject);
        editor.apply();
    }
    public static String getSubject(Context context){
        SharedPreferences spf = context.getSharedPreferences("login_info",context.MODE_PRIVATE);
        return spf.getString("subject",null);
    }

    public static void recordScore(Context context,String score){
        SharedPreferences spf = context.getSharedPreferences("login_info",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString("score", score);
        editor.apply();
    }
    public static String getScore(Context context){
        SharedPreferences spf = context.getSharedPreferences("login_info",context.MODE_PRIVATE);
        return spf.getString("score",null);
    }
    public static void recordData(Context context,String score,String province,String subject){
        SharedPreferences spf = context.getSharedPreferences("login_info",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString("score", score);
        editor.putString("province", province);
        editor.putString("subject", subject);
        editor.apply();
    }




    public static void recordUser(Context context,String uname,String upwd){
        SharedPreferences spf = context.getSharedPreferences("login_info",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString("uname", uname);
        editor.putString("upwd", upwd);
        editor.putBoolean("login", true);
        // editor.clear();
        editor.apply();
    }
    public static Boolean isFirst(Context context){
        SharedPreferences spf = context.getSharedPreferences("login_info",context.MODE_PRIVATE);
        String version=spf.getString("app_version",null);
        //可进入主页观看不登录模式
        if(version==null||!version.equals(getVersionName(context))){
           return true;
        }else{
          return false;
        }
    }

    public static void saveAppVersion(Context context){
        SharedPreferences spf = context.getSharedPreferences("login_info",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString("app_version", getVersionName(context));
        editor.apply();
    }
    public static String getUname(Context context){
        SharedPreferences spf = context.getSharedPreferences("login_info",context.MODE_PRIVATE);
        return spf.getString("uname",null);
    }
    public static String getUpwd(Context context){
        SharedPreferences spf = context.getSharedPreferences("login_info",context.MODE_PRIVATE);
        return spf.getString("upwd",null);
    }
    public static String getCollegeStr(Context context){
        SharedPreferences spf = context.getSharedPreferences("HistoryRecord",context.MODE_PRIVATE);
        return spf.getString("HistoryRecord","");
    }
    public static void setCollegeStr(Context context,String college){
        SharedPreferences spf = context.getSharedPreferences("HistoryRecord",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString("HistoryRecord", college);
        editor.apply();
    }
    //获取当前应用的版本号：
    private static String getVersionName(Context context)
    {
        String version="";
        try{
            // 获取packagemanager的实例
            PackageManager packageManager = context.getPackageManager();
// getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            version = packInfo.versionName;
            if (version == null || version.length() <= 0) {
                return "";
            }
        }catch(Exception e) {
            //	Log.e("VersionInfo", "Exception", e);
        }
        return version;
    }
}
