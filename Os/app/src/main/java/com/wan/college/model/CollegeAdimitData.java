package com.wan.college.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 万文杰 on 2017/2/1.
 */

public class CollegeAdimitData {

    /**
     * error_info :
     * expect_line : 678
     * lines : [{"line":680,"year":2016},{"line":694,"year":2015},{"line":682,"year":2014}]
     * major_lines : [{"lines":[{"line":696,"year":2015},{"line":690,"year":2014},{"line":694,"year":2013}],"major_name":"临床医学"},{"lines":[{"year":2015},{"line":683,"year":2014},{"line":685,"year":2013}],"major_name":"化学"},{"lines":[{"line":691,"year":2015},{"line":672,"year":2014},{"line":692,"year":2013}],"major_name":"化学工程与工业生物工程"},{"lines":[{"year":2015},{"line":678,"year":2014},{"year":2013}],"major_name":"化学生物学"},{"lines":[{"line":694,"year":2015},{"line":690,"year":2014},{"line":689,"year":2013}],"major_name":"土木工程"},{"lines":[{"line":690,"year":2015},{"line":677,"year":2014},{"line":692,"year":2013}],"major_name":"工业工程"},{"lines":[{"line":698,"year":2015},{"year":2014},{"year":2013}],"major_name":"工商管理类"},{"lines":[{"year":2015},{"line":690,"year":2014},{"line":696,"year":2013}],"major_name":"工程力学"},{"lines":[{"year":2015},{"line":688,"year":2014},{"line":683,"year":2013}],"major_name":"工程物理"},{"lines":[{"line":704,"year":2015},{"year":2014},{"year":2013}],"major_name":"工程物理(工学)"},{"lines":[{"line":696,"year":2015},{"line":693,"year":2014},{"line":700,"year":2013}],"major_name":"建筑学"},{"lines":[{"year":2015},{"line":674,"year":2014},{"line":691,"year":2013}],"major_name":"建筑环境与能源应用工程"},{"lines":[{"line":699,"year":2015},{"line":681,"year":2014},{"line":700,"year":2013}],"major_name":"数学与应用数学"},{"lines":[{"line":695,"year":2015},{"line":693,"year":2014},{"line":697,"year":2013}],"major_name":"数理基础科学"},{"lines":[{"line":702,"year":2015},{"year":2014},{"year":2013}],"major_name":"新闻学"},{"lines":[{"line":699,"year":2015},{"line":687,"year":2014},{"line":682,"year":2013}],"major_name":"机械工程"},{"lines":[{"line":694,"year":2015},{"line":685,"year":2014},{"line":683,"year":2013}],"major_name":"材料科学与工程"},{"lines":[{"year":2015},{"line":674,"year":2014},{"line":673,"year":2013}],"major_name":"水利水电工程"},{"lines":[{"year":2015},{"line":674,"year":2014},{"line":685,"year":2013}],"major_name":"法学"},{"lines":[{"line":695,"year":2015},{"year":2014},{"line":674,"year":2013}],"major_name":"测控技术与仪器"},{"lines":[{"year":2015},{"line":681,"year":2014},{"line":696,"year":2013}],"major_name":"物理学"},{"lines":[{"line":697,"year":2015},{"line":693,"year":2014},{"line":697,"year":2013}],"major_name":"环境工程"},{"lines":[{"line":694,"year":2015},{"year":2014},{"line":679,"year":2013}],"major_name":"生物医学工程"},{"lines":[{"line":701,"year":2015},{"line":688,"year":2014},{"line":693,"year":2013}],"major_name":"生物科学"},{"lines":[{"line":689,"year":2015},{"year":2014},{"year":2013}],"major_name":"电子信息类"},{"lines":[{"line":697,"year":2015},{"line":688,"year":2014},{"line":687,"year":2013}],"major_name":"电气工程及其自动化"},{"lines":[{"line":702,"year":2015},{"year":2014},{"year":2013}],"major_name":"社会科学试验班"},{"lines":[{"line":706,"year":2015},{"line":695,"year":2014},{"line":700,"year":2013}],"major_name":"经济与金融"},{"lines":[{"line":696,"year":2015},{"year":2014},{"line":681,"year":2013}],"major_name":"能源与动力工程"},{"lines":[{"line":688,"year":2015},{"line":683,"year":2014},{"line":691,"year":2013}],"major_name":"自动化"},{"lines":[{"year":2015},{"year":2014},{"line":682,"year":2013}],"major_name":"航空航天工程"},{"lines":[{"line":695,"year":2015},{"year":2014},{"year":2013}],"major_name":"航空航天类"},{"lines":[{"line":696,"year":2015},{"year":2014},{"year":2013}],"major_name":"药学"},{"lines":[{"line":691,"year":2015},{"line":691,"year":2014},{"line":695,"year":2013}],"major_name":"计算机科学与技术"},{"lines":[{"line":694,"year":2015},{"line":671,"year":2014},{"line":679,"year":2013}],"major_name":"车辆工程"},{"lines":[{"line":697,"year":2015},{"line":686,"year":2014},{"line":683,"year":2013}],"major_name":"软件工程"},{"lines":[{"line":696,"year":2015},{"line":685,"year":2014},{"line":672,"year":2013}],"major_name":"高分子材料与工程"}]
     * pici : 一本预测线
     * possibility : 5
     * status : 0
     * yiben_expect_line : 678
     * yiben_lines : [{"line":680,"year":2016},{"line":694,"year":2015},{"line":682,"year":2014}]
     * yiben_possibility : 5
     */

    private String error_info;
    private int expect_line;
    private String pici;
    private int possibility;
    private int status;
    private int yiben_expect_line;
    private int yiben_possibility;
    private int erben_expect_line;
    private int erben_possibility;
    private List<LinesBean> lines;
    private List<MajorLinesBean> major_lines;
    private List<YibenLinesBean> yiben_lines;
    private List<ErbenLinesBean> erben_lines;
    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public int getExpect_line() {
        return expect_line;
    }

    public void setExpect_line(int expect_line) {
        this.expect_line = expect_line;
    }

    public String getPici() {
        return pici;
    }

    public void setPici(String pici) {
        this.pici = pici;
    }

    public int getPossibility() {
        return possibility;
    }

    public void setPossibility(int possibility) {
        this.possibility = possibility;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getYiben_expect_line() {
        return yiben_expect_line;
    }

    public void setYiben_expect_line(int yiben_expect_line) {
        this.yiben_expect_line = yiben_expect_line;
    }

    public int getYiben_possibility() {
        return yiben_possibility;
    }

    public void setYiben_possibility(int yiben_possibility) {
        this.yiben_possibility = yiben_possibility;
    }

    public int getErben_expect_line() {
        return erben_expect_line;
    }

    public void setErben_expect_line(int erben_expect_line) {
        this.erben_expect_line = erben_expect_line;
    }

    public int getErben_possibility() {
        return erben_possibility;
    }

    public void setErben_possibility(int erben_possibility) {
        this.erben_possibility = erben_possibility;
    }

    public List<LinesBean> getLines() {
        return lines;
    }

    public void setLines(List<LinesBean> lines) {
        this.lines = lines;
    }

    public List<MajorLinesBean> getMajor_lines() {
        return major_lines;
    }

    public void setMajor_lines(List<MajorLinesBean> major_lines) {
        this.major_lines = major_lines;
    }

    public List<YibenLinesBean> getYiben_lines() {
        return yiben_lines;
    }

    public void setYiben_lines(List<YibenLinesBean> yiben_lines) {
        this.yiben_lines = yiben_lines;
    }

    public static class LinesBean {
        /**
         * line : 680
         * year : 2016
         */

        private int line;
        private int year;

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }


    }

    public static class MajorLinesBean {
        /**
         * lines : [{"line":696,"year":2015},{"line":690,"year":2014},{"line":694,"year":2013}]
         * major_name : 临床医学
         */

        private String major_name;
        private List<LinesBeanX> lines;

        public String getMajor_name() {
            return major_name;
        }

        public void setMajor_name(String major_name) {
            this.major_name = major_name;
        }

        public List<LinesBeanX> getLines() {
            return lines;
        }

        public void setLines(List<LinesBeanX> lines) {
            this.lines = lines;
        }

        public static class LinesBeanX {
            /**
             * line : 696
             * year : 2015
             */

            private int line;
            private int year;

            public int getLine() {
                return line;
            }

            public void setLine(int line) {
                this.line = line;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }


            public LinesBeanX() {
            }


        }



        public MajorLinesBean() {
        }


    }

    public static class YibenLinesBean {
        /**
         * line : 680
         * year : 2016
         */

        private int line;
        private int year;

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public YibenLinesBean() {
        }

    }

    public static class ErbenLinesBean {
        /**
         * line : 680
         * year : 2016
         */

        private int line;
        private int year;

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public ErbenLinesBean() {
        }

    }

    public CollegeAdimitData() {
    }
}
