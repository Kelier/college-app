package com.wan.college.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 万文杰 on 2017/2/4.
 */

public class NewsTagsData implements Parcelable{

    /**
     * error_info :
     * status : 0
     * tags : ["必读","政策","专业","院校"]
     */

    private String error_info;
    private int status;
    private List<String> tags;

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.error_info);
        dest.writeInt(this.status);
        dest.writeStringList(this.tags);
    }

    public NewsTagsData() {
    }

    protected NewsTagsData(Parcel in) {
        this.error_info = in.readString();
        this.status = in.readInt();
        this.tags = in.createStringArrayList();
    }

    public static final Creator<NewsTagsData> CREATOR = new Creator<NewsTagsData>() {
        @Override
        public NewsTagsData createFromParcel(Parcel source) {
            return new NewsTagsData(source);
        }

        @Override
        public NewsTagsData[] newArray(int size) {
            return new NewsTagsData[size];
        }
    };
}
