package com.wan.college.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万文杰 on 2017/2/1.
 */

public class CollegeDetailData implements Parcelable{

    /**
     * address : 北京市海淀区清华园1号
     * back_cover : {"height":523,"url":"http://esfile.lexue.com/file/T1uaJTBCK_1RCvBVdK.jpg","width":750}
     * belong_to : 教育部
     * college_description : 清华大学（Tsinghua University）是中国著名高等学府，坐落于北京西北郊风景秀丽的清华园。是中国高层次人才培养和科学技术研究的重要基地之一。
     清华大学的前身是清华学堂，成立于1911年，当初是清政府建立的留美预备学校。1912 年更名为清华学校，为尝试人才的本地培养，1925 年设立大学部，同年开办研究院（国学门），1928年更名为“国立清华大学”，并于1929年秋开办研究院，各系设研究所。1937年抗日战争爆发后，南迁长沙，与北京大学、南开大学联合办学，组建国立长沙临时大学，1938年迁至昆明，改名为国立西南联合大学。1946年，清华大学迁回清华园原址复校，设有文、法、理、工、农等5 个学院，26 个系。
     1952年，全国高校院系调整后，清华大学成为一所多科性工业大学，重点为国家培养工程技术人才，被誉为“工程师的摇篮”。1978年以来，清华大学进入了一个蓬勃发展的新时期，逐步恢复了理科、经济、管理和文科类学科，并成立了研究生院和继续教育学院。1999 年，原中央工艺美术学院并入，成立清华大学美术学院。在国家和教育部的大力支持下，经过“211工程”建设和“985计划”的实施，清华大学在学科建设、人才培养、师资队伍、科学研究以及整体办学条件等方面均跃上了一个新的台阶。目前，清华大学设有13 个学院，54个系，已成为一所具有理学、工学、文学、艺术学、历史学、哲学、经济学、管理学、法学、教育学和医学等学科的综合性、研究型大学。
     清芬挺秀，华夏增辉。今天的清华大学面临前所未有的历史机遇，清华人继承“爱国、奉献”的优良传统，秉承“自强不息、厚德载物”的校训、“行胜于言”的校风以及“严谨、勤奋、求实、创新”的学风，为使清华大学跻身世界一流大学行列，为中华民族的伟大复兴而努力奋斗。
     * college_icon : {"height":200,"url":"http://esfile.lexue.com/file/T1DyJTBQxT1RCvBVdK.jpg","width":198}
     * college_name : 清华大学
     * college_type : 理工类
     * doctor_num : 253
     * employment : {"half_year_employment_ratio":94,"half_year_salary":11132,"salary_rank":[{"major_name":"会计学","salary":"15555"},{"major_name":"物理学","salary":"15076"},{"major_name":"汽车服务工程","salary":"15056"},{"major_name":"自动化","salary":"15040"},{"major_name":"电子信息工程","salary":"14928"},{"major_name":"计算机科学与技术","salary":"13923"},{"major_name":"机械工程","salary":"13874"},{"major_name":"土木工程","salary":"13807"},{"major_name":"电气工程及其自动化","salary":"13693"},{"major_name":"材料科学与工程","salary":"13078"}]}
     * error_info : ok
     * important_major_num : 37
     * important_majors : [{"major_id":210,"major_name":"车辆工程"},{"major_id":367,"major_name":"能源与动力工程"},{"major_id":797,"major_name":"水利水电工程"},{"major_id":462,"major_name":"核工程与核技术"},{"major_id":619,"major_name":"建筑学"},{"major_id":309,"major_name":"土木工程"},{"major_id":560,"major_name":"机械工程"},{"major_id":841,"major_name":"机械设计制造及其自动化"},{"major_id":559,"major_name":"测控技术与仪器"},{"major_id":710,"major_name":"自动化"},{"major_id":520,"major_name":"软件工程"},{"major_id":1272,"major_name":"电气工程及其自动化"},{"major_id":243,"major_name":"环境工程"},{"major_id":143,"major_name":"材料科学与工程"},{"major_id":377,"major_name":"数学与应用数学"},{"major_id":1304,"major_name":"信息与计算科学"},{"major_id":1220,"major_name":"物理学"},{"major_id":1297,"major_name":"化学"},{"major_id":8,"major_name":"生物科学"},{"major_id":1211,"major_name":"英语"},{"major_id":1144,"major_name":"法学"},{"major_id":1309,"major_name":"艺术设计学"},{"major_id":1070,"major_name":"新闻学"},{"major_id":16,"major_name":"广告学"}]
     * master_num : 271
     * ranking : 1
     * sex_rate : [66.54,33.46]
     * share_url : http://esfile.lexue.com/zhiyuan/share/college/data/1917.html
     * special_majors : ["建筑学","水利水电工程","土木工程","机械工程","电气工程"]
     * status : 0
     * tags : ["211","985","研究生院","国防生","卓越计划","综合评价","自主招生"]
     * telnum : 010-62782051
     */

    private String address;
    private BackCoverBean back_cover;
    private String belong_to;
    private String college_description;
    private CollegeIconBean college_icon;
    private String college_name;
    private String college_type;
    private int doctor_num;
    private EmploymentBean employment;
    private String error_info;
    private int important_major_num;
    private int master_num;
    private String ranking;
    private String share_url;
    private int status;
    private String telnum;
    private List<ImportantMajorsBean> important_majors;
    private List<Double> sex_rate;
    private List<String> special_majors;
    private List<String> tags;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BackCoverBean getBack_cover() {
        return back_cover;
    }

    public void setBack_cover(BackCoverBean back_cover) {
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

    public CollegeIconBean getCollege_icon() {
        return college_icon;
    }

    public void setCollege_icon(CollegeIconBean college_icon) {
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

    public EmploymentBean getEmployment() {
        return employment;
    }

    public void setEmployment(EmploymentBean employment) {
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

    public List<ImportantMajorsBean> getImportant_majors() {
        return important_majors;
    }

    public void setImportant_majors(List<ImportantMajorsBean> important_majors) {
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

    public static class BackCoverBean implements Parcelable{
        /**
         * height : 523
         * url : http://esfile.lexue.com/file/T1uaJTBCK_1RCvBVdK.jpg
         * width : 750
         */

        private int height;
        private String url;
        private int width;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.height);
            dest.writeString(this.url);
            dest.writeInt(this.width);
        }

        public BackCoverBean() {
        }

        protected BackCoverBean(Parcel in) {
            this.height = in.readInt();
            this.url = in.readString();
            this.width = in.readInt();
        }

        public static final Creator<BackCoverBean> CREATOR = new Creator<BackCoverBean>() {
            @Override
            public BackCoverBean createFromParcel(Parcel source) {
                return new BackCoverBean(source);
            }

            @Override
            public BackCoverBean[] newArray(int size) {
                return new BackCoverBean[size];
            }
        };
    }

    public static class CollegeIconBean implements Parcelable{
        /**
         * height : 200
         * url : http://esfile.lexue.com/file/T1DyJTBQxT1RCvBVdK.jpg
         * width : 198
         */

        private int height;
        private String url;
        private int width;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.height);
            dest.writeString(this.url);
            dest.writeInt(this.width);
        }

        public CollegeIconBean() {
        }

        protected CollegeIconBean(Parcel in) {
            this.height = in.readInt();
            this.url = in.readString();
            this.width = in.readInt();
        }

        public static final Creator<CollegeIconBean> CREATOR = new Creator<CollegeIconBean>() {
            @Override
            public CollegeIconBean createFromParcel(Parcel source) {
                return new CollegeIconBean(source);
            }

            @Override
            public CollegeIconBean[] newArray(int size) {
                return new CollegeIconBean[size];
            }
        };
    }

    public static class EmploymentBean implements Parcelable{
        /**
         * half_year_employment_ratio : 94
         * half_year_salary : 11132
         * salary_rank : [{"major_name":"会计学","salary":"15555"},{"major_name":"物理学","salary":"15076"},{"major_name":"汽车服务工程","salary":"15056"},{"major_name":"自动化","salary":"15040"},{"major_name":"电子信息工程","salary":"14928"},{"major_name":"计算机科学与技术","salary":"13923"},{"major_name":"机械工程","salary":"13874"},{"major_name":"土木工程","salary":"13807"},{"major_name":"电气工程及其自动化","salary":"13693"},{"major_name":"材料科学与工程","salary":"13078"}]
         */

        private int half_year_employment_ratio;
        private int half_year_salary;
        private List<SalaryRankBean> salary_rank;

        public int getHalf_year_employment_ratio() {
            return half_year_employment_ratio;
        }

        public void setHalf_year_employment_ratio(int half_year_employment_ratio) {
            this.half_year_employment_ratio = half_year_employment_ratio;
        }

        public int getHalf_year_salary() {
            return half_year_salary;
        }

        public void setHalf_year_salary(int half_year_salary) {
            this.half_year_salary = half_year_salary;
        }

        public List<SalaryRankBean> getSalary_rank() {
            return salary_rank;
        }

        public void setSalary_rank(List<SalaryRankBean> salary_rank) {
            this.salary_rank = salary_rank;
        }

        public static class SalaryRankBean implements Parcelable{
            /**
             * major_name : 会计学
             * salary : 15555
             */

            private String major_name;
            private String salary;

            public String getMajor_name() {
                return major_name;
            }

            public void setMajor_name(String major_name) {
                this.major_name = major_name;
            }

            public String getSalary() {
                return salary;
            }

            public void setSalary(String salary) {
                this.salary = salary;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.major_name);
                dest.writeString(this.salary);
            }

            public SalaryRankBean() {
            }

            protected SalaryRankBean(Parcel in) {
                this.major_name = in.readString();
                this.salary = in.readString();
            }

            public static final Creator<SalaryRankBean> CREATOR = new Creator<SalaryRankBean>() {
                @Override
                public SalaryRankBean createFromParcel(Parcel source) {
                    return new SalaryRankBean(source);
                }

                @Override
                public SalaryRankBean[] newArray(int size) {
                    return new SalaryRankBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.half_year_employment_ratio);
            dest.writeInt(this.half_year_salary);
            dest.writeTypedList(this.salary_rank);
        }

        public EmploymentBean() {
        }

        protected EmploymentBean(Parcel in) {
            this.half_year_employment_ratio = in.readInt();
            this.half_year_salary = in.readInt();
            this.salary_rank = in.createTypedArrayList(SalaryRankBean.CREATOR);
        }

        public static final Creator<EmploymentBean> CREATOR = new Creator<EmploymentBean>() {
            @Override
            public EmploymentBean createFromParcel(Parcel source) {
                return new EmploymentBean(source);
            }

            @Override
            public EmploymentBean[] newArray(int size) {
                return new EmploymentBean[size];
            }
        };
    }

    public static class ImportantMajorsBean implements Parcelable{
        /**
         * major_id : 210
         * major_name : 车辆工程
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.major_id);
            dest.writeString(this.major_name);
        }

        public ImportantMajorsBean() {
        }

        protected ImportantMajorsBean(Parcel in) {
            this.major_id = in.readInt();
            this.major_name = in.readString();
        }

        public static final Creator<ImportantMajorsBean> CREATOR = new Creator<ImportantMajorsBean>() {
            @Override
            public ImportantMajorsBean createFromParcel(Parcel source) {
                return new ImportantMajorsBean(source);
            }

            @Override
            public ImportantMajorsBean[] newArray(int size) {
                return new ImportantMajorsBean[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeParcelable(this.back_cover, flags);
        dest.writeString(this.belong_to);
        dest.writeString(this.college_description);
        dest.writeParcelable(this.college_icon, flags);
        dest.writeString(this.college_name);
        dest.writeString(this.college_type);
        dest.writeInt(this.doctor_num);
        dest.writeParcelable(this.employment, flags);
        dest.writeString(this.error_info);
        dest.writeInt(this.important_major_num);
        dest.writeInt(this.master_num);
        dest.writeString(this.ranking);
        dest.writeString(this.share_url);
        dest.writeInt(this.status);
        dest.writeString(this.telnum);
        dest.writeTypedList(this.important_majors);
        dest.writeList(this.sex_rate);
        dest.writeStringList(this.special_majors);
        dest.writeStringList(this.tags);
    }

    public CollegeDetailData() {
    }

    protected CollegeDetailData(Parcel in) {
        this.address = in.readString();
        this.back_cover = in.readParcelable(BackCoverBean.class.getClassLoader());
        this.belong_to = in.readString();
        this.college_description = in.readString();
        this.college_icon = in.readParcelable(CollegeIconBean.class.getClassLoader());
        this.college_name = in.readString();
        this.college_type = in.readString();
        this.doctor_num = in.readInt();
        this.employment = in.readParcelable(EmploymentBean.class.getClassLoader());
        this.error_info = in.readString();
        this.important_major_num = in.readInt();
        this.master_num = in.readInt();
        this.ranking = in.readString();
        this.share_url = in.readString();
        this.status = in.readInt();
        this.telnum = in.readString();
        this.important_majors = in.createTypedArrayList(ImportantMajorsBean.CREATOR);
        this.sex_rate = new ArrayList<Double>();
        in.readList(this.sex_rate, Double.class.getClassLoader());
        this.special_majors = in.createStringArrayList();
        this.tags = in.createStringArrayList();
    }

    public static final Creator<CollegeDetailData> CREATOR = new Creator<CollegeDetailData>() {
        @Override
        public CollegeDetailData createFromParcel(Parcel source) {
            return new CollegeDetailData(source);
        }

        @Override
        public CollegeDetailData[] newArray(int size) {
            return new CollegeDetailData[size];
        }
    };
}
