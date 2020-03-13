package com.zcm.springmvc.service.Impl;

import com.zcm.springmvc.dao.ProvinceCityDistrictMapper;
import com.zcm.springmvc.entity.ProvinceCityDistrict;
import com.zcm.springmvc.service.ProvinceCityDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProvinceCityDistrictServiceImpl implements ProvinceCityDistrictService {
    @Autowired
    private ProvinceCityDistrictMapper provinceCityDistrictMapper;
    public List<ProvinceCityDistrict> findAll(Integer pid) {
        List<ProvinceCityDistrict> lsit=provinceCityDistrictMapper.findList(pid);

        return lsit;
    }
}
