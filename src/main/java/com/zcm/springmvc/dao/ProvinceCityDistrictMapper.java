package com.zcm.springmvc.dao;

import com.zcm.springmvc.entity.ProvinceCityDistrict;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProvinceCityDistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProvinceCityDistrict record);

    int insertSelective(ProvinceCityDistrict record);

    ProvinceCityDistrict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProvinceCityDistrict record);

    int updateByPrimaryKey(ProvinceCityDistrict record);

    /**
     * 根据pid查询所以的省市区
     * @param pid
     * @return
     */
    List<ProvinceCityDistrict> findList(Integer pid);
}