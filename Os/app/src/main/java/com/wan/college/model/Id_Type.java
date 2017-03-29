package com.wan.college.model;

/**
 * Created by 万文杰 on 2017/2/8.
 */

public class Id_Type {
    private String id;
    private String datatype;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public Id_Type() {

    }
    public Id_Type(String id, String datatype) {
        this.id = id;
        this.datatype = datatype;
    }
}
