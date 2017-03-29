package com.wan.college.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 万文杰 on 2017/1/16.
 */

public class SuggestData implements Parcelable{

    /**
     * error_info :
     * high_risk_count : 14
     * high_risk_possibility : 40
     * low_risk_count : 9
     * low_risk_possibility : 90
     * mid_risk_count : 6
     * mid_risk_possibility : 70
     * status : 0
     */

    private String error_info;
    private int high_risk_count;
    private int high_risk_possibility;
    private int low_risk_count;
    private int low_risk_possibility;
    private int mid_risk_count;
    private int mid_risk_possibility;
    private int status;

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public int getHigh_risk_count() {
        return high_risk_count;
    }

    public void setHigh_risk_count(int high_risk_count) {
        this.high_risk_count = high_risk_count;
    }

    public int getHigh_risk_possibility() {
        return high_risk_possibility;
    }

    public void setHigh_risk_possibility(int high_risk_possibility) {
        this.high_risk_possibility = high_risk_possibility;
    }

    public int getLow_risk_count() {
        return low_risk_count;
    }

    public void setLow_risk_count(int low_risk_count) {
        this.low_risk_count = low_risk_count;
    }

    public int getLow_risk_possibility() {
        return low_risk_possibility;
    }

    public void setLow_risk_possibility(int low_risk_possibility) {
        this.low_risk_possibility = low_risk_possibility;
    }

    public int getMid_risk_count() {
        return mid_risk_count;
    }

    public void setMid_risk_count(int mid_risk_count) {
        this.mid_risk_count = mid_risk_count;
    }

    public int getMid_risk_possibility() {
        return mid_risk_possibility;
    }

    public void setMid_risk_possibility(int mid_risk_possibility) {
        this.mid_risk_possibility = mid_risk_possibility;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.error_info);
        dest.writeInt(this.high_risk_count);
        dest.writeInt(this.high_risk_possibility);
        dest.writeInt(this.low_risk_count);
        dest.writeInt(this.low_risk_possibility);
        dest.writeInt(this.mid_risk_count);
        dest.writeInt(this.mid_risk_possibility);
        dest.writeInt(this.status);
    }

    public SuggestData() {
    }

    protected SuggestData(Parcel in) {
        this.error_info = in.readString();
        this.high_risk_count = in.readInt();
        this.high_risk_possibility = in.readInt();
        this.low_risk_count = in.readInt();
        this.low_risk_possibility = in.readInt();
        this.mid_risk_count = in.readInt();
        this.mid_risk_possibility = in.readInt();
        this.status = in.readInt();
    }

    public static final Creator<SuggestData> CREATOR = new Creator<SuggestData>() {
        @Override
        public SuggestData createFromParcel(Parcel source) {
            return new SuggestData(source);
        }

        @Override
        public SuggestData[] newArray(int size) {
            return new SuggestData[size];
        }
    };
}
