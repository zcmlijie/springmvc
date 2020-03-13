package com.zcm.springmvc.entity;

import java.io.Serializable;

public class CartVo implements Serializable {
    private static final long serialVersionUID = -5093779122108434274L;
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long  price;
    private Integer num;
    private Long realPrice;
    private String image;
    private String title;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartVo)) return false;

        CartVo cartVo = (CartVo) o;

        return cid != null ? cid.equals(cartVo.cid) : cartVo.cid == null;
    }

    @Override
    public int hashCode() {
        return cid != null ? cid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CartVo{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", price=" + price +
                ", num=" + num +
                ", realPrice=" + realPrice +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
