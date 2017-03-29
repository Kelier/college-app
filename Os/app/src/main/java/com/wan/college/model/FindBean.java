package com.wan.college.model;

/**
 * Created by John Yan on 1/6/2017.
 */

public class FindBean {
    private int logo;
    private String text_title;
    private String text_content;

    public FindBean(){

    }

    public FindBean(int logo, String text_title, String text_content) {
        this.logo = logo;
        this.text_title = text_title;
        this.text_content = text_content;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getText_title() {
        return text_title;
    }

    public void setText_title(String text_title) {
        this.text_title = text_title;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }
}