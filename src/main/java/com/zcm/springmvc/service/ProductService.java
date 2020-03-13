package com.zcm.springmvc.service;

import com.zcm.springmvc.entity.TProduct;

import java.util.List;

public interface ProductService {
    Integer insertAll(TProduct tProduct,String username);
    List<TProduct> getHost();
    TProduct getByid(Integer id);
}
