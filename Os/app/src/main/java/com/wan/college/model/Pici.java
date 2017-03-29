package com.wan.college.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 万文杰 on 2017/1/13.
 */

public class Pici implements Parcelable {
    private String year;
    private String first_line;
    private String second_line;
    private String third_line;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFirst_line() {
        return first_line;
    }

    public void setFirst_line(String first_line) {
        this.first_line = first_line;
    }

    public String getSecond_line() {
        return second_line;
    }

    public void setSecond_line(String second_line) {
        this.second_line = second_line;
    }

    public String getThird_line() {
        return third_line;
    }

    public void setThird_line(String third_line) {
        this.third_line = third_line;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.year);
        dest.writeString(this.first_line);
        dest.writeString(this.second_line);
        dest.writeString(this.third_line);
    }

    public Pici() {
    }

    public Pici(String year, String first_line, String second_line, String third_line) {
        this.year = year;
        this.first_line = first_line;
        this.second_line = second_line;
        this.third_line = third_line;
    }

    protected Pici(Parcel in) {
        this.year = in.readString();
        this.first_line = in.readString();
        this.second_line = in.readString();
        this.third_line = in.readString();
    }

    public static final Creator<Pici> CREATOR = new Creator<Pici>() {
        @Override
        public Pici createFromParcel(Parcel source) {
            return new Pici(source);
        }

        @Override
        public Pici[] newArray(int size) {
            return new Pici[size];
        }
    };
}
