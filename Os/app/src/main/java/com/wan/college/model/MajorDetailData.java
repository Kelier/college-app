package com.wan.college.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 万文杰 on 2017/2/12.
 */

public class MajorDetailData implements Parcelable{

    /**
     * courses : 主干学科：基础医学、临床医学、医学影像学。主要课程：物理学、电子学基础、计算机原理与接口、影像设备结构与维修、医学成像技术、摄影学、人体 解剖学、诊断学、内科学、影像诊断学、介入放射学、影像物理、超声诊断、放射诊断、核素诊断、核医学、医学影像解剖学、肿瘤放疗治疗学、B超诊断学。
     * error_info :
     * followed : true
     * major_code : 101003
     * major_description : 医学影像技术专业简介医学影像学在医学诊断领域是一门新兴的学科，并且已经在科学研究的工业中获得了广泛的应用，现如今备受人们的关注。医学影像技术主要实践教学环节包括临床实习、毕业实习。医学影像技术培养目标本专业培养具有基础医学、临床医学和现代医学影像学的基本理论知识及能力，能在医疗卫生单位从事医学影像诊断、介入放射学和医学成像技术等方面工作的医学高级专门人才。医学影像技术专业培养要求本专业学生主要学习基础医学、临床医学、医学影像学的基本理论知识，受到常规放射学、CT、磁共振、超声学、DSA、核医学影像学等操作技能的基本训练，具有常见病的影像诊断和介入放射学操作基本能力。专业简介::: 医学影像技术专业简介医学影像学在医学诊断领域是一门新兴的学科，并且已经在科学研究的工业中获得了广泛的应用，现如今备受人们的关注。医学影像技术主要实践教学环节包括临床实习、毕业实习。医学影像技术培养目标本专业培养具有基础医学、临床医学和现代医学影像学的基本理论知识及能力，能在医疗卫生单位从事医学影像诊断、介入放射学和医学成像技术等方面工作的医学高级专门人才。医学影像技术专业培养要求本专业学生主要学习基础医学、临床医学、医学影像学的基本理论知识，受到常规放射学、CT、磁共振、超声学、DSA、核医学影像学等操作技能的基本训练，具有常见病的影像诊断和介入放射学操作基本能力。

     * major_name : 医学影像技术
     * share_url : http://123.57.146.211/zhiyuan/share/major/data/101003.html
     * status : 0
     * years : 五年
     */

    private String courses;
    private String error_info;
    private boolean followed;
    private String major_code;
    private String major_description;
    private String major_name;
    private String share_url;
    private int status;
    private String years;
    private List<RankingBean> ranking;

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public String getMajor_code() {
        return major_code;
    }

    public void setMajor_code(String major_code) {
        this.major_code = major_code;
    }

    public String getMajor_description() {
        return major_description;
    }

    public void setMajor_description(String major_description) {
        this.major_description = major_description;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
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

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }
    public List<RankingBean> getRanking() {
        return ranking;
    }

    public void setRanking(List<RankingBean> ranking) {
        this.ranking = ranking;
    }
    public static class RankingBean implements Parcelable{
        /**
         * college_id : 140
         * college_name : 上海外国语大学
         * rank : A+
         */

        private String college_id;
        private String college_name;
        private String rank;

        public String getCollege_id() {
            return college_id;
        }

        public void setCollege_id(String college_id) {
            this.college_id = college_id;
        }

        public String getCollege_name() {
            return college_name;
        }

        public void setCollege_name(String college_name) {
            this.college_name = college_name;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.college_id);
            dest.writeString(this.college_name);
            dest.writeString(this.rank);
        }

        public RankingBean() {
        }

        protected RankingBean(Parcel in) {
            this.college_id = in.readString();
            this.college_name = in.readString();
            this.rank = in.readString();
        }

        public static final Creator<RankingBean> CREATOR = new Creator<RankingBean>() {
            @Override
            public RankingBean createFromParcel(Parcel source) {
                return new RankingBean(source);
            }

            @Override
            public RankingBean[] newArray(int size) {
                return new RankingBean[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.courses);
        dest.writeString(this.error_info);
        dest.writeByte(this.followed ? (byte) 1 : (byte) 0);
        dest.writeString(this.major_code);
        dest.writeString(this.major_description);
        dest.writeString(this.major_name);
        dest.writeString(this.share_url);
        dest.writeInt(this.status);
        dest.writeString(this.years);
        dest.writeTypedList(this.ranking);
    }

    public MajorDetailData() {
    }

    protected MajorDetailData(Parcel in) {
        this.courses = in.readString();
        this.error_info = in.readString();
        this.followed = in.readByte() != 0;
        this.major_code = in.readString();
        this.major_description = in.readString();
        this.major_name = in.readString();
        this.share_url = in.readString();
        this.status = in.readInt();
        this.years = in.readString();
        this.ranking = in.createTypedArrayList(RankingBean.CREATOR);
    }

    public static final Creator<MajorDetailData> CREATOR = new Creator<MajorDetailData>() {
        @Override
        public MajorDetailData createFromParcel(Parcel source) {
            return new MajorDetailData(source);
        }

        @Override
        public MajorDetailData[] newArray(int size) {
            return new MajorDetailData[size];
        }
    };
}
