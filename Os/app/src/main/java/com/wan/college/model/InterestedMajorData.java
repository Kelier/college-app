package com.wan.college.model;

import java.util.List;

/**
 * Created by asus on 2017/2/22.
 */

public class InterestedMajorData {

    /**
     * error_info :
     * major_count : 35
     * majors : [{"major_id":26,"major_name":"应用生物科学"},{"major_id":34,"major_name":"思想政治教育"},{"major_id":36,"major_name":"舞蹈表演"},{"major_id":48,"major_name":"音乐学"},{"major_id":57,"major_name":"历史学"},{"major_id":59,"major_name":"中国共产党历史"},{"major_id":146,"major_name":"治安学"},{"major_id":155,"major_name":"中国画"},{"major_id":185,"major_name":"海洋油气工程"},{"major_id":247,"major_name":"视觉传达设计"},{"major_id":266,"major_name":"监狱学"},{"major_id":270,"major_name":"非织造材料与工程"},{"major_id":280,"major_name":"金融工程"},{"major_id":299,"major_name":"警卫学"},{"major_id":356,"major_name":"国内安全保卫"},{"major_id":368,"major_name":"林学"},{"major_id":376,"major_name":"园艺教育"},{"major_id":422,"major_name":"书法学"},{"major_id":423,"major_name":"舞蹈学"},{"major_id":465,"major_name":"社会学"}]
     * status : 0
     */

    private String error_info;
    private int major_count;
    private int status;
    private List<MajorsBean> majors;

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public int getMajor_count() {
        return major_count;
    }

    public void setMajor_count(int major_count) {
        this.major_count = major_count;
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
         * major_id : 26
         * major_name : 应用生物科学
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
