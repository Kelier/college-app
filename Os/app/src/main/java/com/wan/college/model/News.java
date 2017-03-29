package com.wan.college.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 万文杰 on 2017/1/12.
 */

public class News implements Parcelable {
    private String news_url;
    private String new_title;
    private String new_subtitle;
    private String new_thirdtitle;
    private String news_bg;
    private String news_read_num;

    public String getNews_url() {
        return news_url;
    }

    public void setNews_url(String news_url) {
        this.news_url = news_url;
    }

    public String getNew_title() {
        return new_title;
    }

    public void setNew_title(String new_title) {
        this.new_title = new_title;
    }

    public String getNew_subtitle() {
        return new_subtitle;
    }

    public void setNew_subtitle(String new_subtitle) {
        this.new_subtitle = new_subtitle;
    }

    public String getNew_thirdtitle() {
        return new_thirdtitle;
    }

    public void setNew_thirdtitle(String new_thirdtitle) {
        this.new_thirdtitle = new_thirdtitle;
    }

    public String getNews_bg() {
        return news_bg;
    }

    public void setNews_bg(String news_bg) {
        this.news_bg = news_bg;
    }

    public String getNews_read_num() {
        return news_read_num;
    }

    public void setNews_read_num(String news_read_num) {
        this.news_read_num = news_read_num;
    }

    public News(String news_url, String new_title, String new_subtitle, String new_thirdtitle, String news_bg, String news_read_num) {
        this.news_url = news_url;
        this.new_title = new_title;
        this.new_subtitle = new_subtitle;
        this.new_thirdtitle = new_thirdtitle;
        this.news_bg = news_bg;
        this.news_read_num = news_read_num;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.news_url);
        dest.writeString(this.new_title);
        dest.writeString(this.new_subtitle);
        dest.writeString(this.new_thirdtitle);
        dest.writeString(this.news_bg);
        dest.writeString(this.news_read_num);
    }

    protected News(Parcel in) {
        this.news_url = in.readString();
        this.new_title = in.readString();
        this.new_subtitle = in.readString();
        this.new_thirdtitle = in.readString();
        this.news_bg = in.readString();
        this.news_read_num = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
