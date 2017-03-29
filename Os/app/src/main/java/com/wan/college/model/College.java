package com.wan.college.model;

/**
 * Created by asus on 2017/2/27.
 */

public class College {

    /**
     * icon : 2017/02/24/1025287793.jpg
     * name : 清华大学
     * rank : 1
     * pid : 1
     * id : 1001
     * introduction : http://www.tsinghua.edu.cn/publish/newthu/newthu_cnt/students/students-1.html
     */

    private String icon;
    private String name;
    private int rank;
    private int pid;
    private int id;
    private String introduction;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
