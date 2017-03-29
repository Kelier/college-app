package com.wan.college.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 万文杰 on 2017/1/10.
 */

public class MajorLines implements Parcelable{

    private String major_name;
    private String major_line1;
    private String major_line2;
    private String major_line3;

    public MajorLines(String major_name, String major_line1, String major_line2, String major_line3) {
        this.major_name = major_name;
        this.major_line1 = major_line1;
        this.major_line2 = major_line2;
        this.major_line3 = major_line3;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public String getMajor_line1() {
        return major_line1;
    }

    public void setMajor_line1(String major_line1) {
        this.major_line1 = major_line1;
    }

    public String getMajor_line2() {
        return major_line2;
    }

    public void setMajor_line2(String major_line2) {
        this.major_line2 = major_line2;
    }

    public String getMajor_line3() {
        return major_line3;
    }

    public void setMajor_line3(String major_line3) {
        this.major_line3 = major_line3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.major_name);
        dest.writeString(this.major_line1);
        dest.writeString(this.major_line2);
        dest.writeString(this.major_line3);
    }

    protected MajorLines(Parcel in) {
        this.major_name = in.readString();
        this.major_line1 = in.readString();
        this.major_line2 = in.readString();
        this.major_line3 = in.readString();
    }

    public static final Creator<MajorLines> CREATOR = new Creator<MajorLines>() {
        @Override
        public MajorLines createFromParcel(Parcel source) {
            return new MajorLines(source);
        }

        @Override
        public MajorLines[] newArray(int size) {
            return new MajorLines[size];
        }
    };
}
