package com.wan.college.network;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.wan.college.utils.NetUtils;

import java.util.ArrayList;

/**
 * Created by 万文杰 on 2017/2/17.
 */

public class NetBroadcastReceiver extends BroadcastReceiver {
    public static ArrayList<netEventHandler> mListeners = new ArrayList<netEventHandler>();
    private static String NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(NET_CHANGE_ACTION)) {
            InitConfig.mNetWorkState = NetUtils.getNetworkState(context);
            if (mListeners.size() > 0)// 通知接口完成加载
                Log.i("网络监听数量",String.valueOf(mListeners.size()));
                for (netEventHandler handler : mListeners) {
                    handler.onNetChange();
                }
        }
    }

    public static abstract interface netEventHandler {

        public abstract void onNetChange();
    }
}
