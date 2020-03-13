package com.zcm.springmvc.service;

import com.zcm.springmvc.entity.ProvinceCityDistrict;

import java.util.List;

public interface ProvinceCityDistrictService {
    List<ProvinceCityDistrict> findAll(Integer pid);
}
