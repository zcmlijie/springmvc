package com.zcm.springmvc.dao;

import com.zcm.springmvc.entity.TOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface TOrderMapper {
    int deleteByPrimaryKey(Integer oid);

    /**
     * 创建订单
     * @param record
     * @return
     */
    int insert(TOrder record);

    int insertSelective(TOrder record);

    TOrder selectByPrimaryKey(Integer oid);

    int updateByPrimaryKeySelective(TOrder record);

    int updateByPrimaryKey(TOrder record);
}