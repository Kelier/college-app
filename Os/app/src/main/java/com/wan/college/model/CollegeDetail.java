package com.wan.college.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万文杰 on 2017/2/1.
 */

public class CollegeDetail implements Parcelable{
    private String address;
    private String back_cover;
    private String belong_to;
    private String college_description;
    private String college_icon;
    private String college_name;
    private String college_type;
    private int doctor_num;
    private String employment;
    private String error_info;
    private int important_major_num;
    private int master_num;
    private String ranking;
    private String share_url;
    private int status;
    private String telnum;
    private List<String> important_majors;
    private List<Double> sex_rate;
    private List<String> special_majors;
    private List<String> tags;
    public CollegeDetail(){

    }
    public CollegeDetail(String address, String back_cover, String belong_to, String college_description, String college_icon, String college_name, String college_type, int doctor_num, String employment, String error_info, int important_major_num, int master_num, String ranking, String share_url, int status, String telnum, List<String> important_majors, List<Double> sex_rate, List<String> special_majors, List<String> tags) {
        this.address = address;
        this.back_cover = back_cover;
        this.belong_to = belong_to;
        this.college_description = college_description;
        this.college_icon = college_icon;
        this.college_name = college_name;
        this.college_type = college_type;
        this.doctor_num = doctor_num;
        this.employment = employment;
        this.error_info = error_info;
        this.important_major_num = important_major_num;
        this.master_num = master_num;
        this.ranking = ranking;
        this.share_url = share_url;
        this.status = status;
        this.telnum = telnum;
        this.important_majors = important_majors;
        this.sex_rate = sex_rate;
        this.special_majors = special_majors;
        this.tags = tags;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBack_cover() {
        return back_cover;
    }

    public void setBack_cover(String back_cover) {
        this.back_cover = back_cover;
    }

    public String getBelong_to() {
        return belong_to;
    }

    public void setBelong_to(String belong_to) {
        this.belong_to = belong_to;
    }

    public String getCollege_description() {
        return college_description;
    }

    public void setCollege_description(String college_description) {
        this.college_description = college_description;
    }

    public String getCollege_icon() {
        return college_icon;
    }

    public void setCollege_icon(String college_icon) {
        this.college_icon = college_icon;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public String getCollege_type() {
        return college_type;
    }

    public void setCollege_type(String college_type) {
        this.college_type = college_type;
    }

    public int getDoctor_num() {
        return doctor_num;
    }

    public void setDoctor_num(int doctor_num) {
        this.doctor_num = doctor_num;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public int getImportant_major_num() {
        return important_major_num;
    }

    public void setImportant_major_num(int important_major_num) {
        this.important_major_num = important_major_num;
    }

    public int getMaster_num() {
        return master_num;
    }

    public void setMaster_num(int master_num) {
        this.master_num = master_num;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
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

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public List<String> getImportant_majors() {
        return important_majors;
    }

    public void setImportant_majors(List<String> important_majors) {
        this.important_majors = important_majors;
    }

    public List<Double> getSex_rate() {
        return sex_rate;
    }

    public void setSex_rate(List<Double> sex_rate) {
        this.sex_rate = sex_rate;
    }

    public List<String> getSpecial_majors() {
        return special_majors;
    }

    public void setSpecial_majors(List<String> special_majors) {
        this.special_majors = special_majors;
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
        dest.writeString(this.address);
        dest.writeString(this.back_cover);
        dest.writeString(this.belong_to);
        dest.writeString(this.college_description);
        dest.writeString(this.college_icon);
        dest.writeString(this.college_name);
        dest.writeString(this.college_type);
        dest.writeInt(this.doctor_num);
        dest.writeString(this.employment);
        dest.writeString(this.error_info);
        dest.writeInt(this.important_major_num);
        dest.writeInt(this.master_num);
        dest.writeString(this.ranking);
        dest.writeString(this.share_url);
        dest.writeInt(this.status);
        dest.writeString(this.telnum);
        dest.writeStringList(this.important_majors);
        dest.writeList(this.sex_rate);
        dest.writeStringList(this.special_majors);
        dest.writeStringList(this.tags);
    }

    protected CollegeDetail(Parcel in) {
        this.address = in.readString();
        this.back_cover = in.readString();
        this.belong_to = in.readString();
        this.college_description = in.readString();
        this.college_icon = in.readString();
        this.college_name = in.readString();
        this.college_type = in.readString();
        this.doctor_num = in.readInt();
        this.employment = in.readString();
        this.error_info = in.readString();
        this.important_major_num = in.readInt();
        this.master_num = in.readInt();
        this.ranking = in.readString();
        this.share_url = in.readString();
        this.status = in.readInt();
        this.telnum = in.readString();
        this.important_majors = in.createStringArrayList();
        this.sex_rate = new ArrayList<Double>();
        in.readList(this.sex_rate, Double.class.getClassLoader());
        this.special_majors = in.createStringArrayList();
        this.tags = in.createStringArrayList();
    }

    public static final Creator<CollegeDetail> CREATOR = new Creator<CollegeDetail>() {
        @Override
        public CollegeDetail createFromParcel(Parcel source) {
            return new CollegeDetail(source);
        }

        @Override
        public CollegeDetail[] newArray(int size) {
            return new CollegeDetail[size];
        }
    };
}
