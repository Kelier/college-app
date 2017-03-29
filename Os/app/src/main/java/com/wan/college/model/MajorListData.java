package com.wan.college.model;

import android.os.Parcelable;

import java.util.List;

/**
 * Created by 万文杰 on 2017/2/14.
 */

public class MajorListData {

    /**
     * error_info : ok
     * majors : [{"major_id":3,"major_name":"医学影像技术"},{"major_id":196,"major_name":"医学实验技术"},{"major_id":259,"major_name":"医学检验技术"},{"major_id":855,"major_name":"眼视光学"},{"major_id":859,"major_name":"口腔医学技术"},{"major_id":867,"major_name":"康复治疗学"},{"major_id":1009,"major_name":"卫生检验与检疫"},{"major_id":1063,"major_name":"听力与言语康复学"}]
     * status : 0
     */

    private String error_info;
    private int status;
    private List<MajorsBean> majors;

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

    public List<MajorsBean> getMajors() {
        return majors;
    }

    public void setMajors(List<MajorsBean> majors) {
        this.majors = majors;
    }

    public static class MajorsBean {
        /**
         * major_id : 3
         * major_name : 医学影像技术
         */

        private int major_id;
        private String major_name;

        public int getMajor_id() {
            return major_id;
        }

        public void setMajor_id(int major_id) {
            this.major_id = major_id;
        }

        public String getMajor_name() {
            return major_name;
        }

        public void setMajor_name(String major_name) {
            this.major_name = major_name;
        }
    }

}
