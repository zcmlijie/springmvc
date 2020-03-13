package com.zcm.springmvc.dao;

import com.zcm.springmvc.entity.TAddress;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TAddressMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(TAddress record);

    /**
     * 增加用户的收货地址
     * @param record
     * @return
     */
    int insertSelective(TAddress record);

    TAddress selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(TAddress record);

    int updateByPrimaryKey(TAddress record);

    /**
     * 根据id统计数据
     * @param uid
     * @return
     */
    Integer countByUid(Integer uid);

    /**
     * 修改默认地址
     * @param aid
     * @return
     */
    Integer updateIsDeful(@Param("aid") Integer aid,
                          @Param("modifiedUser") String modifiedUser,
                          @Param("modifiedTime") Date modifiedTime);

    /**
     * 把用户的所有地址改成非默认
     * @param uid
     * @return
     */
    Integer updageAllDeful(@Param("uid") Integer uid,
                           @Param("modifiedUser") String modifiedUser,
                           @Param("modifiedTime") Date modifiedTime);

    /**
     * 删除收货地址
     * @param aid
     * @return
     */
    Integer deleteAddress(Integer aid);

    /**
     * 查询出最近修改的收货地址设置成默认地址
     *
     * @return
     */
    TAddress selectnewAddress();
}