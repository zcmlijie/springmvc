package com.zcm.springmvc.entity;

import java.io.Serializable;

public class Tbdept implements Serializable {
    private static final long serialVersionUID = 3837278781104872825L;
    private Integer did;

    private String dname;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}