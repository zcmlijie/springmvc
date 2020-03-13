package com.zcm.springmvc.entity;

import java.io.Serializable;
import java.util.Date;

public class Tdemp implements Serializable {
    private static final long serialVersionUID = -8228626611824228869L;
    private Integer eid;

    private Integer did;

    private Integer age;

    private Boolean gende;

    private Date workdate;

    private String password;

    private String ename;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getGende() {
        return gende;
    }

    public void setGende(Boolean gende) {
        this.gende = gende;
    }

    public Date getWorkdate() {
        return workdate;
    }

    public void setWorkdate(Date workdate) {
        this.workdate = workdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }
}