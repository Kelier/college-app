package com.wan.college.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 万文杰 on 2017/1/16.
 */

public class CollegeList implements Parcelable{
    private String college_icon;
    private int college_id;
    private String college_name;
    private int expect_line;
    private int pici;
    private int possibility;
    private boolean followed;
    private List<String> tags;

    public CollegeList(String college_icon, int college_id, String college_name, int expect_line, int pici, int possibility, List<String>  tags) {
        this.college_icon = college_icon;
        this.college_id = college_id;
        this.college_name = college_name;
        this.expect_line = expect_line;
        this.pici = pici;
        this.possibility = possibility;
        this.tags = tags;
    }
    public CollegeList(String college_icon, int college_id, String college_name, int expect_line, int pici, int possibility,Boolean followed, List<String>  tags) {
        this.college_icon = college_icon;
        this.college_id = college_id;
        this.college_name = college_name;
        this.expect_line = expect_line;
        this.pici = pici;
        this.possibility = possibility;
        this.followed=followed;
        this.tags = tags;
    }

    public String getCollege_icon() {
        return college_icon;
    }

    public void setCollege_icon(String college_icon) {
        this.college_icon = college_icon;
    }

    public int getCollege_id() {
        return college_id;
    }

    public void setCollege_id(int college_id) {
        this.college_id = college_id;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public int getExpect_line() {
        return expect_line;
    }

    public void setExpect_line(int expect_line) {
        this.expect_line = expect_line;
    }

    public int getPici() {
        return pici;
    }

    public void setPici(int pici) {
        this.pici = pici;
    }

    public int getPossibility() {
        return possibility;
    }

    public void setPossibility(int possibility) {
        this.possibility = possibility;
    }

    public List<String>  getTags() {
        return tags;
    }

    public void setTags(List<String>  tags) {
        this.tags = tags;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.college_icon);
        dest.writeInt(this.college_id);
        dest.writeString(this.college_name);
        dest.writeInt(this.expect_line);
        dest.writeInt(this.pici);
        dest.writeInt(this.possibility);
        dest.writeByte(this.followed ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.tags);
    }

    protected CollegeList(Parcel in) {
        this.college_icon = in.readString();
        this.college_id = in.readInt();
        this.college_name = in.readString();
        this.expect_line = in.readInt();
        this.pici = in.readInt();
        this.possibility = in.readInt();
        this.followed = in.readByte() != 0;
        this.tags = in.createStringArrayList();
    }

    public static final Creator<CollegeList> CREATOR = new Creator<CollegeList>() {
        @Override
        public CollegeList createFromParcel(Parcel source) {
            return new CollegeList(source);
        }

        @Override
        public CollegeList[] newArray(int size) {
            return new CollegeList[size];
        }
    };
}
