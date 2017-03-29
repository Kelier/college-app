package com.wan.college.model;

import java.util.List;

/**
 * Created by 万文杰 on 2017/2/4.
 */

public class XuanZhuanYeData {

    /**
     * status : 0
     * error_info :
     * papers : [{"paper_url":"http://paper.lexue.com/zhiyuan/wap/career_major.html","paper_title":"如何选专业","cover":{"url":"http://esfile.lexue.com/file/T1ryJTByDv1RCvBVdK.jpg","width":1024,"height":481}}]
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
         * paper_url : http://paper.lexue.com/zhiyuan/wap/career_major.html
         * paper_title : 如何选专业
         * cover : {"url":"http://esfile.lexue.com/file/T1ryJTByDv1RCvBVdK.jpg","width":1024,"height":481}
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

        public static class CoverBean {
            /**
             * url : http://esfile.lexue.com/file/T1ryJTByDv1RCvBVdK.jpg
             * width : 1024
             * height : 481
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
        }
    }
}
