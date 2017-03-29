package com.wan.college.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万文杰 on 2017/1/13.
 */

public class PiciData implements Parcelable {


    /**
     * error_info :
     * li_pici : [{"lines":[360,-1,-1],"year":2016},{"lines":[414,348,-1],"year":2015},{"lines":[423,351,-1],"year":2014},{"lines":[405,331,-1],"year":2013},{"lines":[423,342,-1],"year":2012}]
     * status : 0
     * wen_pici : [{"lines":[368,-1,-1],"year":2016},{"lines":[434,372,-1],"year":2015},{"lines":[444,390,-1],"year":2014},{"lines":[448,403,-1],"year":2013},{"lines":[438,379,-1],"year":2012}]
     */

    private String error_info;
    private int status;
    private List<LiPiciBean> li_pici;
    private List<WenPiciBean> wen_pici;

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

    public List<LiPiciBean> getLi_pici() {
        return li_pici;
    }

    public void setLi_pici(List<LiPiciBean> li_pici) {
        this.li_pici = li_pici;
    }

    public List<WenPiciBean> getWen_pici() {
        return wen_pici;
    }

    public void setWen_pici(List<WenPiciBean> wen_pici) {
        this.wen_pici = wen_pici;
    }

    public static class LiPiciBean {
        /**
         * lines : [360,-1,-1]
         * year : 2016
         */

        private int year;
        private List<Integer> lines;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public List<Integer> getLines() {
            return lines;
        }

        public void setLines(List<Integer> lines) {
            this.lines = lines;
        }
    }

    public static class WenPiciBean {
        /**
         * lines : [368,-1,-1]
         * year : 2016
         */

        private int year;
        private List<Integer> lines;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public List<Integer> getLines() {
            return lines;
        }

        public void setLines(List<Integer> lines) {
            this.lines = lines;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.error_info);
        dest.writeInt(this.status);
        dest.writeList(this.li_pici);
        dest.writeList(this.wen_pici);
    }

    public PiciData() {
    }

    protected PiciData(Parcel in) {
        this.error_info = in.readString();
        this.status = in.readInt();
        this.li_pici = new ArrayList<LiPiciBean>();
        in.readList(this.li_pici, LiPiciBean.class.getClassLoader());
        this.wen_pici = new ArrayList<WenPiciBean>();
        in.readList(this.wen_pici, WenPiciBean.class.getClassLoader());
    }

    public static final Creator<PiciData> CREATOR = new Creator<PiciData>() {
        @Override
        public PiciData createFromParcel(Parcel source) {
            return new PiciData(source);
        }

        @Override
        public PiciData[] newArray(int size) {
            return new PiciData[size];
        }
    };
}
