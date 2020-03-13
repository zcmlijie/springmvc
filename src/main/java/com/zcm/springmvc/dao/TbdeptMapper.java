package com.zcm.springmvc.dao;

import com.zcm.springmvc.entity.Tbdept;
import com.zcm.springmvc.entity.TbdeptEmpVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface TbdeptMapper {
    int deleteByPrimaryKey(Integer did);

    int insert(Tbdept record);

    int insertSelective(Tbdept record);

    Tbdept selectByPrimaryKey(Integer did);

    int updateByPrimaryKeySelective(Tbdept record);

    int updateByPrimaryKey(Tbdept record);

    /**
     * 批量删除
     * @param did
     * @return
     */

    int deleteBatch(List<Integer> did);

    /**
     * 多表分页查询
     * @param map
     * @return
     */

    List<TbdeptEmpVo> findByPage(HashMap<String,Object> map);

    int pageCount(@Param("did") Integer did);

}