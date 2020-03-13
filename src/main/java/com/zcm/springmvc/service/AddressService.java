package com.zcm.springmvc.service;

import com.zcm.springmvc.entity.TAddress;

public interface AddressService {
    Integer addAll(Integer uid,String username,TAddress tAddress);
    Integer updateAllDeful(Integer uid,String username,Integer aid);
    Integer deleteAddress(Integer aid,Integer uid,String username);
}
