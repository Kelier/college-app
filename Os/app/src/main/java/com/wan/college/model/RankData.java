package com.wan.college.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万文杰 on 2017/1/11.
 */

public class RankData implements Parcelable {

    /**
     * status : 0
     * error_info :
     * papers : [{"paper_url":"http://paper.lexue.com/zhiyuan/rank/salary_major.html","paper_title":"高薪专业排行榜","cover":{"url":"http://esfile.lexue.com/file/T11aJTBCKT1RCvBVdK.jpg","width":702,"height":330}},{"paper_url":"http://paper.lexue.com/zhiyuan/rank/salary_career.html","paper_title":"高薪职业排行榜","cover":{"url":"http://esfile.lexue.com/file/T19yJTBmLT1RCvBVdK.jpg","width":702,"height":330}},{"paper_url":"http://paper.lexue.com/zhiyuan/rank/employment.html","paper_title":"就业率TOP50榜","cover":{"url":"http://esfile.lexue.com/file/T19tJTBmLT1RCvBVdK.jpg","width":702,"height":330}},{"paper_url":"http://paper.lexue.com/zhiyuan/rank/master.html","paper_title":"读研比例高专业排行榜","cover":{"url":"http://esfile.lexue.com/file/T13tJTBCKT1RCvBVdK.jpg","width":702,"height":330}},{"paper_url":"http://paper.lexue.com/zhiyuan/rank/boys_salary.html","paper_title":"男生薪资五星榜","cover":{"url":"http://esfile.lexue.com/file/T1ItJTBjKT1RCvBVdK.png","width":702,"height":330}},{"paper_url":"http://paper.lexue.com/zhiyuan/rank/girls_salary.html","paper_title":"女生薪资五星榜","cover":{"url":"http://esfile.lexue.com/file/T1IaJTBmKT1RCvBVdK.jpg","width":702,"height":330}},{"paper_url":"http://paper.lexue.com/zhiyuan/rank/boys_employment.html","paper_title":"男生就业率五星榜","cover":{"url":"http://esfile.lexue.com/file/T1otJTBsKT1RCvBVdK.png","width":702,"height":330}},{"paper_url":"http://paper.lexue.com/zhiyuan/rank/girls_employment.html","paper_title":"女生就业率五星榜","cover":{"url":"http://esfile.lexue.com/file/T11tJTBCYT1RCvBVdK.jpg","width":702,"height":330}},{"paper_url":"http://paper.lexue.com/zhiyuan/rank/science.html","paper_title":"中国大学理学排行榜","cover":{"url":"http://esfile.lexue.com/file/T19RJTBsdT1RCvBVdK.png","width":702,"height":330}},{"paper_url":"http://paper.lexue.com/zhiyuan/rank/tech.html","paper_title":"中国大学工学排行榜","cover":{"url":"http://esfile.lexue.com/file/T1htJTBsDT1RCvBVdK.png","width":702,"height":330}},{"paper_url":"http://paper.lexue.com/zhiyuan/rank/economy.html","paper_title":"中国大学经济学排行榜","cover":{"url":"http://esfile.lexue.com/file/T19tJTBsZT1RCvBVdK.png","width":702,"height":330}},{"paper_url":"http://paper.lexue.com/zhiyuan/rank/law.html","paper_title":"中国大学法学排行榜","cover":{"url":"http://esfile.lexue.com/file/T11RJTBQxT1RCvBVdK.png","width":702,"height":330}},{"paper_url":"http://paper.lexue.com/zhiyuan/rank/education.html","paper_title":"中国大学教育学排行榜","cover":{"url":"http://esfile.lexue.com/file/T13tJTB_WT1RCvBVdK.png","width":702,"height":330}},{"paper_url":"http://paper.lexue.com/zhiyuan/rank/manager.html","paper_title":"中国大学管理学排行榜","cover":{"url":"http://esfile.lexue.com/file/T19yJTBsZT1RCvBVdK.png","width":702,"height":330}},{"paper_url":"http://paper.lexue.com/zhiyuan/rank/medical.html","paper_title":"中国大学医学排行榜","cover":{"url":"http://esfile.lexue.com/file/T13yJTBQhT1RCvBVdK.png","width":702,"height":330}}]
     */

    private int status;
    private String error_info;
    private List<PapersBean> papers;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public List<PapersBean> getPapers() {
        return papers;
    }

    public void setPapers(List<PapersBean> papers) {
        this.papers = papers;
    }

    public static class PapersBean {
        /**
         * paper_url : http://paper.lexue.com/zhiyuan/rank/salary_major.html
         * paper_title : 高薪专业排行榜
         * cover : {"url":"http://esfile.lexue.com/file/T11aJTBCKT1RCvBVdK.jpg","width":702,"height":330}
         */

        private String paper_url;
        private String paper_title;
        private CoverBean cover;

        public String getPaper_url() {
            return paper_url;
        }

        public void setPaper_url(String paper_url) {
            this.paper_url = paper_url;
        }

        public String getPaper_title() {
            return paper_title;
        }

        public void setPaper_title(String paper_title) {
            this.paper_title = paper_title;
        }

        public CoverBean getCover() {
            return cover;
        }

        public void setCover(CoverBean cover) {
            this.cover = cover;
        }

        public static class CoverBean implements Parcelable {
            /**
             * url : http://esfile.lexue.com/file/T11aJTBCKT1RCvBVdK.jpg
             * width : 702
             * height : 330
             */

            private String url;
            private int width;
            private int height;

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

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.url);
                dest.writeInt(this.width);
                dest.writeInt(this.height);
            }

            public CoverBean() {
            }

            protected CoverBean(Parcel in) {
                this.url = in.readString();
                this.width = in.readInt();
                this.height = in.readInt();
            }

            public static final Creator<CoverBean> CREATOR = new Creator<CoverBean>() {
                @Override
                public CoverBean createFromParcel(Parcel source) {
                    return new CoverBean(source);
                }

                @Override
                public CoverBean[] newArray(int size) {
                    return new CoverBean[size];
                }
            };
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeString(this.error_info);
        dest.writeList(this.papers);
    }

    public RankData() {
    }

    protected RankData(Parcel in) {
        this.status = in.readInt();
        this.error_info = in.readString();
        this.papers = new ArrayList<PapersBean>();
        in.readList(this.papers, PapersBean.class.getClassLoader());
    }

    public static final Creator<RankData> CREATOR = new Creator<RankData>() {
        @Override
        public RankData createFromParcel(Parcel source) {
            return new RankData(source);
        }

        @Override
        public RankData[] newArray(int size) {
            return new RankData[size];
        }
    };
}
