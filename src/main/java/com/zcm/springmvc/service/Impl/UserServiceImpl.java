package com.zcm.springmvc.service.Impl;

import com.zcm.springmvc.dao.TUserMapper;
import com.zcm.springmvc.entity.TUser;
import com.zcm.springmvc.service.UserService;
import com.zcm.springmvc.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
   private TUserMapper tUserMapper;
    public void reg(TUser user) {
        TUser tUser=tUserMapper.seletByName(user.getUsername());
        if(tUser==null){

            //盐值
            String salt= UUID.randomUUID().toString().toUpperCase();
            user.setSalt(salt);
            String password=getMd5Password(user.getPassword(),salt);
            user.setPassword(password);
            user.setIsDelete(0);
            //日志四项
            Date now=new Date();
            user.setCreatedTime(now);
            user.setCreatedUser(user.getUsername());
            user.setModifiedTime(now);
            user.setModifiedUser(user.getUsername());
            int row=tUserMapper.insert(user);
            if(row<0){
               throw new InsertException();
            }

        }else {
            throw new UsernameDuplicateException("用户名已经注册");
        }
    }

    public TUser login(String name, String password) {
        TUser tUser=tUserMapper.seletByName(name);
        TUser user=new TUser();
        if(tUser!=null){
          if(tUser.getIsDelete()==1) {
              throw new UserNotFoundException("用户名不存在");
          }
          String PasswordNew=getMd5Password(password,tUser.getSalt());
          if(!PasswordNew.equals(tUser.getPassword())){
              throw new PasswordNotMatchException("密码错误");
          }
          user.setUid(tUser.getUid());
          user.setUsername(tUser.getUsername());
          user.setAvatar(tUser.getAvatar());
        }else {
          throw new UserNotFoundException("用户不存在");
        }
        return user;
    }

    public TUser getByUid(Integer uid) {
        if (uid!=null){
            TUser user=  tUserMapper.selectByPrimaryKey(uid);
            if(user!=null||user.getIsDelete()!=1){
               TUser tuser=new TUser();
               tuser.setUsername(user.getUsername());
               tuser.setPhone(user.getPhone());
               tuser.setGender(user.getGender());
               tuser.setEmail(user.getEmail());
               return user;
            }else {
                throw new UserNotFoundException("用户不存在");
            }

        }

        return null;
    }

    /**
     * 修改用户信息
     * @param user
     */
    public void changeInfo(TUser user) {
        if(user!=null){
            TUser user1=tUserMapper.selectByPrimaryKey(user.getUid());
            if(user1!=null||user1.getIsDelete()!=1){
                user.setGender(user.getGender());
                user.setEmail(user.getEmail());
                user.setPhone(user.getPhone());
                Date date=new Date();
               user.setModifiedUser(user.getUsername());
               user.setModifiedTime(date);
               Integer row=tUserMapper.updateInfoByUid(user);
               if(row<0){
                   throw new UpdateException("修改失败");
               }
            }else {
                throw new UserNotFoundException("用户不存在");
            }
        }

    }

    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        if(uid!=null){
            TUser user=tUserMapper.selectByPrimaryKey(uid);
            if(user==null|| user.getIsDelete()==1){
                throw new UserNotFoundException("用户不存在");
            }
            String salt=user.getSalt();
            String pass=getMd5Password(oldPassword,salt);
            //判断原始密码是否输入正确
            if(pass.contentEquals(user.getPassword())){
                //修改的新密码
                String newPass=getMd5Password(newPassword,salt);
               Integer row= tUserMapper.updatePasswordByUid(uid,newPass,username,new Date());
               if(row<0){
                   throw new UpdateException("修改错误");
               }
            }else {
                throw new PasswordNotMatchException("密码输入错误");
            }
        }
    }

    /**
     * 修改图片
     * @param uid
     * @param username
     * @param avatar
     */
    public void changeAvatar(Integer uid, String username, String avatar) {
        if(uid!=null){
            TUser user=tUserMapper.selectByPrimaryKey(uid);
            if(user!=null||user.getIsDelete()==0){
                Date now=new Date();
                Integer row=tUserMapper.updateAvatarByUid(uid,avatar,username,now);
                if(row<0){
                    throw new UpdateException("修改失败");
                }
            }else {
                throw new UserNotFoundException("用户不存在");
            }
        }
    }

    /**
     * 执行密码加密
     * @param password 原始密码
     * @param salt 加盐值
     * @return 加密密码
     */
    private String  getMd5Password(String password, String salt){
        for(int i=0;i<3;i++){
            password=DigestUtils.md5DigestAsHex((salt+password+salt)
                    .getBytes())
                    .toUpperCase();
        }

        return password;
    }
}
