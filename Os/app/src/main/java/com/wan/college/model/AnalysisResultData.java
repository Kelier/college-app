package com.wan.college.model;

/**
 * Created by 万文杰 on 2017/2/12.
 */

public class AnalysisResultData {

    /**
     * error_info : ok
     * result_url : http://paper.lexue.com/zhiyuan/wap/20151216/BA.html
     * share_url : http://paper.lexue.com/zhiyuan/wap/20151216/BA.html&type=share
     * status : 0
     */

    private String error_info;
    private String result_url;
    private String share_url;
    private int status;

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public String getResult_url() {
        return result_url;
    }

    public void setResult_url(String result_url) {
        this.result_url = result_url;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
