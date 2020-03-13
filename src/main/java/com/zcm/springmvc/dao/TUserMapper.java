package com.zcm.springmvc.dao;

import com.zcm.springmvc.entity.TUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
@Repository
public interface TUserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    /**
     * 根据用户名查询用户数据
     * @param name
     * @return
     */
    TUser seletByName(@Param("name") String name);

    /**
     * 根据用户id修改用户信息
     * @param user
     * @return
     */
    Integer updateInfoByUid(TUser user);

    /**
     * 修改密码
     * @param uid
     * @param password
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updatePasswordByUid(
            @Param("uid") Integer uid,
            @Param("password") String password,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    /**
     * 上传图片
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */

 Integer  updateAvatarByUid(@Param("uid") Integer uid,
                            @Param("avatar") String avatar,
                            @Param("modifiedUser") String modifiedUser,
                            @Param("modifiedTime") Date modifiedTime);
}