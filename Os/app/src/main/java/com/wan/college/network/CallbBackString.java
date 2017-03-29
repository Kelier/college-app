package com.wan.college.network;

import org.xutils.common.Callback;

/**
 * Created by John Yan on 1/9/2017.
 */

public class CallbBackString implements Callback.CommonCallback<String> {
    private INetResult<String> netResult;

    public CallbBackString(INetResult<String> netResult) {
        this.netResult = netResult;
    }

    @Override

    public void onSuccess(String result) {
        netResult.getNetData(result) ;
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
    ex.printStackTrace();
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {
    InitConfig.log("end");
    }
}
