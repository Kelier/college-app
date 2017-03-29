package com.wan.college.model;

import java.util.List;

/**
 * Created by 万文杰 on 2017/3/9.
 */

public class CountryData {

    /**
     * code : 0
     * list : [{"id":"1","cname":"中国大陆","country_id":"86"},{"id":"2","cname":"澳门特区","country_id":"853"},{"id":"3","cname":"台湾","country_id":"886"},{"id":"4","cname":"美国","country_id":"1"},{"id":"5","cname":"香港","country_id":"852"},{"id":"6","cname":"比利时","country_id":"32"},{"id":"7","cname":"澳大利亚","country_id":"61"},{"id":"8","cname":"法国","country_id":"33"},{"id":"9","cname":"加拿大","country_id":"1"},{"id":"10","cname":"日本","country_id":"81"},{"id":"11","cname":"新加坡","country_id":"65"},{"id":"12","cname":"韩国","country_id":"82"}]
     */

    private int code;
    private List<ListBean> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * cname : 中国大陆
         * country_id : 86
         */

        private String id;
        private String cname;
        private String country_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getCountry_id() {
            return country_id;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }
    }
}
