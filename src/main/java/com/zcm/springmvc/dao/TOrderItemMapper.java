package com.zcm.springmvc.dao;

import com.zcm.springmvc.entity.TOrderItem;
import org.springframework.stereotype.Repository;

@Repository
public interface TOrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TOrderItem record);

    int insertSelective(TOrderItem record);

    TOrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TOrderItem record);

    int updateByPrimaryKey(TOrderItem record);
}