package com.zcm.springmvc.service;

import com.zcm.springmvc.entity.TOrder;

import java.util.List;

public interface OrderService {
    /**
     * 创建订单
     * @param aid
     * @param cids
     * @param uid
     * @param username
     * @return
     */
    TOrder create(Integer aid, List<Integer> cids, Integer uid, String username);
}
