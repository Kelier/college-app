package com.wan.college.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万文杰 on 2017/1/12.
 */

public class NewsData implements Parcelable {

    /**
     * error_info : ok
     * paper_count : 72
     * papers : [{"cover":{"height":100,"url":"http://superfile.baijiahulian.com/news/1481699868721kcaAy800637_1337254697d36D.jpg","width":100},"paper_id":285,"paper_snippet":"随着高考的临近，家长们也进入\u201c考前焦虑\u201d期，为了保证考生有良好的体力来应考，家长们想方设法地为孩子进补。","paper_title":"给高考家长们的饮食建议：三大进补误区需提防","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1481699918.html","paper_views":6040,"tags":["高考饮食"]},{"cover":{"height":75,"url":"http://superfile.baijiahulian.com/news/1481279558305TiKygU2996P352T1D463601F11DT20150401153515.jpg","width":100},"paper_id":282,"paper_snippet":"入12月中旬，期末考试的硝烟仿佛已经滚滚到来了，学霸给你总结经验。","paper_title":"学霸支招：期末考试全攻略","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1481279825.html","paper_views":5960,"tags":["学霸经验"]},{"cover":{"height":66,"url":"http://superfile.baijiahulian.com/news/1481012395148BjvKWp00223168.jpeg","width":100},"paper_id":278,"paper_snippet":"孩子升入高三，备战高考是这年的头等大事！很多学校都在抓紧教学进程，争取更多的复习时间。","paper_title":"高三家长6诀窍帮孩子提高成绩","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1481012402.html","paper_views":5712,"tags":["家长必读"]},{"cover":{"height":61,"url":"http://superfile.baijiahulian.com/news/1480731063146fRQZK3940e57d567e3bd4.jpg","width":100},"paper_id":272,"paper_snippet":"这是衡中2016届毕业生李江珊的一篇英语考试心得，她2016年高考英语分数是149分（全国卷英语满分150分）。","paper_title":"这位衡中学霸，高考英语149分！她的学习方法是这样的...","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1480731069.html","paper_views":6005,"tags":["学霸经验"]},{"cover":{"height":75,"url":"http://superfile.baijiahulian.com/news/1480579359476OsOsrmp56983921_1453967289270_3.jpeg","width":100},"paper_id":270,"paper_snippet":"分数至上的想法还是影响着很多的学生及家长，因此一些学生很容易因为成绩的好坏造成情绪的波动，严重的甚至产生心理问题。资料显示20%的高考存在考前焦虑综合征。","paper_title":"20%高考生存在考前焦虑综合征","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1480579364.html","paper_views":5556,"tags":["考生心理"]},{"cover":{"height":67,"url":"http://superfile.baijiahulian.com/news/1480387980344PgmYqp00208962.jpg","width":100},"paper_id":267,"paper_snippet":"高三的孩子们正值青春期，高考这一年还要耗费大量的脑力来面对学习，对营养的需求是非常大的","paper_title":"你给孩子准备了这十种食物补脑抗疲劳吗？","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1480388029.html","paper_views":5543,"tags":["高考饮食"]},{"cover":{"height":66,"url":"http://superfile.baijiahulian.com/news/1480128847304ENlrEu=1361054277,3071451113&fm=23&gp=0.jpg","width":100},"paper_id":261,"paper_snippet":"高中再怎么紧张也有三年，高一可以好好放松一下\u201c高一不必太紧张，高考高二高三再准备就行\u201d。！","paper_title":"高中三年规划：高一基础、高二积累、高三冲刺！","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1480128893.html","paper_views":5068,"tags":["高三备考"]},{"cover":{"height":74,"url":"http://superfile.baijiahulian.com/news/1479969159048rtAKO201361915630987.jpg","width":100},"paper_id":255,"paper_snippet":"如果你现在常常感到疲劳、压力直至精疲力竭，说明你的能量管理做得不好。","paper_title":"学霸的高效率秘籍：管理好你的能量","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1479969178.html","paper_views":4576,"tags":["高效学习"]},{"cover":{"height":71,"url":"http://superfile.baijiahulian.com/news/1479778969999EQIMK1440280137.jpg","width":100},"paper_id":252,"paper_snippet":"目前在校的高中生，参加的仍是文理分科的高考。","paper_title":"如何选择文理科？四大疑惑四大误区四大原则通通告诉你！","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1479778973.html","paper_views":4145,"tags":["文理分科"]},{"cover":{"height":78,"url":"http://superfile.baijiahulian.com/news/1479380862877pNFtuQQ截图20161117190722.png","width":100},"paper_id":248,"paper_snippet":"在高考复习中最容易犯的错误，提醒大家在复习中要避开哪些误区","paper_title":"高三全年复习，这十种错误不要犯！","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1479381141.html","paper_views":4277,"tags":["学习方法"]},{"cover":{"height":76,"url":"http://superfile.baijiahulian.com/news/1478483955055VeZmY日清周结学习方法.jpg","width":100},"paper_id":238,"paper_snippet":"高效学习方法","paper_title":"\u201c日清-周结-月考\u201d循环式学习法","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1478483997.html","paper_views":4388,"tags":["学习方法"]},{"cover":{"height":76,"url":"http://superfile.baijiahulian.com/news/1478142039249nPjtU高考复习中十种病.jpg","width":100},"paper_id":234,"paper_snippet":"高考复习","paper_title":"高考复习中容易患上的十种\u201c病\u201d","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1478142073.html","paper_views":4185,"tags":["高考复习"]},{"cover":{"height":76,"url":"http://superfile.baijiahulian.com/news/1477876715410xUwUH复习误区.jpg","width":100},"paper_id":233,"paper_snippet":"高考复习常见的误区及其对策","paper_title":"避开高考复习的五大误区","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1477876722.html","paper_views":4561,"tags":["学习技巧"]},{"cover":{"height":80,"url":"http://superfile.baijiahulian.com/news/1477619322739EeWWH新高三：状态差.jpg","width":100},"paper_id":229,"paper_snippet":"高三正当时，学习状态差有怎样的表现，又该如何作出调整？","paper_title":"新高三：状态很差怎么办？","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1477619326.html","paper_views":4309,"tags":["高三备考"]},{"cover":{"height":80,"url":"http://superfile.baijiahulian.com/news/1477446758268Sbdid奋战高考必须把握.jpg","width":100},"paper_id":223,"paper_snippet":"高考备考中应当学会的观念","paper_title":"奋战高考必须把握的观念","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1477446793.html","paper_views":4411,"tags":["学习策略"]},{"cover":{"height":76,"url":"http://superfile.baijiahulian.com/news/1477273475649irdIn如何面对模拟考试.jpg","width":100},"paper_id":219,"paper_snippet":"高三模拟考试应对策略","paper_title":"如何面对各种模拟考试","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1477273484.html","paper_views":4092,"tags":["高三考试"]},{"cover":{"height":80,"url":"http://superfile.baijiahulian.com/news/1477014160476vQKwu高三学生的小目标.jpg","width":100},"paper_id":215,"paper_snippet":"高三学生如何制定出切实可行的学习计划？","paper_title":"高三学生的\u201c小目标\u201d","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1477014175.html","paper_views":4466,"tags":["学习计划"]},{"cover":{"height":80,"url":"http://superfile.baijiahulian.com/news/1476840434791xQBXi高三必备：夜间.jpg","width":100},"paper_id":211,"paper_snippet":"高三如何提高夜间的学习效率","paper_title":"高三必备：10招提高夜间学习效率","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1476840442.html","paper_views":4191,"tags":["学习效率"]},{"cover":{"height":80,"url":"http://superfile.baijiahulian.com/news/1476667398668YMJHM一轮复习进行时.jpg","width":100},"paper_id":207,"paper_snippet":"高三一轮复习方法与要领","paper_title":"一轮复习进行时，你做对了吗？","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1476667400.html","paper_views":4311,"tags":["高三复习"]},{"cover":{"height":80,"url":"http://superfile.baijiahulian.com/news/1476408542124Whsno名师支招.jpg","width":100},"paper_id":202,"paper_snippet":"2017年高考考纲变动下的应对策略","paper_title":"高考考纲有变，成都名师支招","paper_url":"http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1476408549.html","paper_views":4360,"tags":["高考大纲"]}]
     * status : 0
     */

    private String error_info;
    private int paper_count;
    private int status;
    private List<PapersBean> papers;

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public int getPaper_count() {
        return paper_count;
    }

    public void setPaper_count(int paper_count) {
        this.paper_count = paper_count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<PapersBean> getPapers() {
        return papers;
    }

    public void setPapers(List<PapersBean> papers) {
        this.papers = papers;
    }

    public static class PapersBean {
        /**
         * cover : {"height":100,"url":"http://superfile.baijiahulian.com/news/1481699868721kcaAy800637_1337254697d36D.jpg","width":100}
         * paper_id : 285
         * paper_snippet : 随着高考的临近，家长们也进入“考前焦虑”期，为了保证考生有良好的体力来应考，家长们想方设法地为孩子进补。
         * paper_title : 给高考家长们的饮食建议：三大进补误区需提防
         * paper_url : http://app.chaojizhiyuan.com/wap/news/zhiyuanNews-1481699918.html
         * paper_views : 6040
         * tags : ["高考饮食"]
         */

        private CoverBean cover;
        private int paper_id;
        private String paper_snippet;
        private String paper_title;
        private String paper_url;
        private int paper_views;
        private List<String> tags;

        public CoverBean getCover() {
            return cover;
        }

        public void setCover(CoverBean cover) {
            this.cover = cover;
        }

        public int getPaper_id() {
            return paper_id;
        }

        public void setPaper_id(int paper_id) {
            this.paper_id = paper_id;
        }

        public String getPaper_snippet() {
            return paper_snippet;
        }

        public void setPaper_snippet(String paper_snippet) {
            this.paper_snippet = paper_snippet;
        }

        public String getPaper_title() {
            return paper_title;
        }

        public void setPaper_title(String paper_title) {
            this.paper_title = paper_title;
        }

        public String getPaper_url() {
            return paper_url;
        }

        public void setPaper_url(String paper_url) {
            this.paper_url = paper_url;
        }

        public int getPaper_views() {
            return paper_views;
        }

        public void setPaper_views(int paper_views) {
            this.paper_views = paper_views;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public static class CoverBean {
            /**
             * height : 100
             * url : http://superfile.baijiahulian.com/news/1481699868721kcaAy800637_1337254697d36D.jpg
             * width : 100
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
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.error_info);
        dest.writeInt(this.paper_count);
        dest.writeInt(this.status);
        dest.writeList(this.papers);
    }

    public NewsData() {
    }

    protected NewsData(Parcel in) {
        this.error_info = in.readString();
        this.paper_count = in.readInt();
        this.status = in.readInt();
        this.papers = new ArrayList<PapersBean>();
        in.readList(this.papers, PapersBean.class.getClassLoader());
    }

    public static final Creator<NewsData> CREATOR = new Creator<NewsData>() {
        @Override
        public NewsData createFromParcel(Parcel source) {
            return new NewsData(source);
        }

        @Override
        public NewsData[] newArray(int size) {
            return new NewsData[size];
        }
    };
}
