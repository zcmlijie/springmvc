package com.zcm.springmvc.dao;

import com.zcm.springmvc.entity.Tdemp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TdempMapper {
    int deleteByPrimaryKey(Integer eid);

    int insert(Tdemp record);

    int insertSelective(Tdemp record);

    Tdemp selectByPrimaryKey(Integer eid);

    int updateByPrimaryKeySelective(Tdemp record);

    int updateByPrimaryKey(Tdemp record);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int insertList (List<Tdemp> list);
}