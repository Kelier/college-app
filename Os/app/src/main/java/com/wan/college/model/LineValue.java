package com.wan.college.model;

/**
 * Created by 万文杰 on 2017/2/22.
 */

public class LineValue {
    private int year;
    private int score;

    public LineValue(int year, int score) {
        this.year = year;
        this.score = score;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
