package com.wan.college.network;

import org.xutils.common.Callback;

import java.io.File;



/**
 * Created by John Yan on 1/9/2017.
 */

public class CallBackFile implements Callback.CommonCallback<File>{
    /**
     * @param result
     * onSuccess调用接口方法
     */
    @Override
    public void onSuccess(File result) {

    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}
