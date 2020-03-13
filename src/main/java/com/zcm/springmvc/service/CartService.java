package com.zcm.springmvc.service;

import com.zcm.springmvc.entity.CartVo;
import com.zcm.springmvc.entity.PageBean;
import com.zcm.springmvc.entity.TCart;

import java.util.List;

public interface CartService {
    void addProCart(TCart tCart,String username);

    PageBean<CartVo> findByUidPage(Integer curr,Integer size,Integer uid);

    Integer updateNum(Integer cid,Integer uid,String username);

    PageBean<CartVo> getVOByCids(List<Integer> cids,Integer curr,Integer size,Integer uid);
}
