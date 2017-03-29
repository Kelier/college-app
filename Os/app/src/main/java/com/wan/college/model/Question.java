package com.wan.college.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万文杰 on 2017/1/15.
 */

public class Question{
    private String question_id;
    private String question_content;
    public List<String> answer_id=new ArrayList<>();
    public List<String> answer_content=new ArrayList<>();
    private String is_answer_id;

    public String getIs_answer_id() {
        return is_answer_id;
    }

    public void setIs_answer_id(String is_answer_id) {
        this.is_answer_id = is_answer_id;
    }

    private boolean answered=false;

    public String getQuestion_id() {
        return question_id;
    }

    public List<String> getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(List<String> answer_id) {
        this.answer_id = answer_id;
    }

    public List<String> getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(List<String> answer_content) {
        this.answer_content = answer_content;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public Question(String question_id, String question_content, List<String> answer_id, List<String> answer_content, boolean answered) {
        this.question_id = question_id;
        this.question_content = question_content;
        this.answer_id = answer_id;
        this.answer_content = answer_content;
        this.answered = answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public boolean isAnswered() {
        return answered;
    }

    public Question(){}

    public Question(String question_id, List<String> answer_content, List<String> answer_id, String question_content) {
        this.question_id = question_id;
        this.answer_content = answer_content;
        this.answer_id = answer_id;
        this.question_content = question_content;
    }
}
