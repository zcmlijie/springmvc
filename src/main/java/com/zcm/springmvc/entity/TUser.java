package com.zcm.springmvc.entity;



public class TUser extends BaseEntity {

    private static final long serialVersionUID = 8970204813995994837L;
    private Integer uid;

    private String username;

    private String password;

    private String salt;

    private String phone;

    private String email;

    private Integer gender;

    private String avatar;

    private Integer isDelete;



    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }


    @Override
    public String toString() {
        return "TUser{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", avatar='" + avatar + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TUser)) return false;

        TUser tUser = (TUser) o;

        if (uid != null ? !uid.equals(tUser.uid) : tUser.uid != null) return false;
        if (username != null ? !username.equals(tUser.username) : tUser.username != null) return false;
        if (password != null ? !password.equals(tUser.password) : tUser.password != null) return false;
        if (salt != null ? !salt.equals(tUser.salt) : tUser.salt != null) return false;
        if (phone != null ? !phone.equals(tUser.phone) : tUser.phone != null) return false;
        if (email != null ? !email.equals(tUser.email) : tUser.email != null) return false;
        if (gender != null ? !gender.equals(tUser.gender) : tUser.gender != null) return false;
        if (avatar != null ? !avatar.equals(tUser.avatar) : tUser.avatar != null) return false;
        return isDelete != null ? isDelete.equals(tUser.isDelete) : tUser.isDelete == null;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        return result;
    }
    public TUser(){

    }
}