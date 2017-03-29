package com.wan.college.network;

/**
 * Created by John Yan on 1/9/2017.
 * 该接口中的方法在网络请求的onSuccess进行调用，目的是传出网络请求的结果数据
 * Activity或者Fragment中需要网络请求时实现该接口
 */

public interface INetResult <T>{
    void getNetData(T data) ;
}
