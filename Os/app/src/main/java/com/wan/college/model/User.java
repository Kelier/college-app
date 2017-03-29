package com.wan.college.model;

/**
 * Created by 万文杰 on 2017/2/8.
 */

public class User {
    private String uid;
    private String nickname;
    private String score;
    private String province;
    private String subject;
    private String avatar;
    private String gender;

    public User(String uid, String nickname, String score, String province, String subject, String avatar, String gender) {
        this.uid = uid;
        this.nickname = nickname;
        this.score = score;
        this.province = province;
        this.subject = subject;
        this.avatar = avatar;
        this.gender = gender;
    }



    public String getUid() {
        return uid;
    }

    public void setUid(String sid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Object getString(String key) {
        switch (key) {
            case "uid":
                 return uid;
            case "nickname":
                 return nickname;
            case "score":
                return score;
            case "province":
                return province;
            case "subject":
                return nickname;
            case "gender":
                return nickname;
            case "avatar":
                return avatar;
            default:return null;
        }
    }


    public User() {
    }

/*
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + score +
                ", price=" + price +
                '}';
    }*/
}
