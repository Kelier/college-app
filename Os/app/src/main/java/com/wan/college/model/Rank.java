package com.wan.college.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 万文杰 on 2017/1/11.
 */

public class Rank implements Parcelable {
    private String paper_url;
    private String paper_title;
    private String paper_bg;

    public Rank(String paper_url, String paper_title, String paper_bg) {
        this.paper_url = paper_url;
        this.paper_title = paper_title;
        this.paper_bg = paper_bg;
    }

    public String getPaper_url() {
        return paper_url;
    }

    public void setPaper_url(String paper_url) {
        this.paper_url = paper_url;
    }

    public String getPaper_title() {
        return paper_title;
    }

    public void setPaper_title(String paper_title) {
        this.paper_title = paper_title;
    }

    public String getPaper_bg() {
        return paper_bg;
    }

    public void setPaper_bg(String paper_bg) {
        this.paper_bg = paper_bg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.paper_url);
        dest.writeString(this.paper_title);
        dest.writeString(this.paper_bg);
    }

    public Rank() {
    }

    protected Rank(Parcel in) {
        this.paper_url = in.readString();
        this.paper_title = in.readString();
        this.paper_bg = in.readString();
    }

    public static final Creator<Rank> CREATOR = new Creator<Rank>() {
        @Override
        public Rank createFromParcel(Parcel source) {
            return new Rank(source);
        }

        @Override
        public Rank[] newArray(int size) {
            return new Rank[size];
        }
    };
}
