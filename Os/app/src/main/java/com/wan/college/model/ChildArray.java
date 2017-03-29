package com.wan.college.model;

/**
 * Created by 万文杰 on 2017/1/15.
 */

public class ChildArray {
    public String[] answer=new String[2];

    public String[] getAnswer() {
        return answer;
    }

    public ChildArray(String answer1, String answer2) {
        this.answer[0] = answer1;
        this.answer[1] = answer2;
    }
}
