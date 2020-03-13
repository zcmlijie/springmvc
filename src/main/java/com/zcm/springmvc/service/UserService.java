package com.zcm.springmvc.service;

import com.zcm.springmvc.entity.TUser;

public interface UserService {
    void reg(TUser user);

    TUser login(String name,String password);

    TUser getByUid(Integer uid);

    void changeInfo(TUser user);

    void changePassword(Integer uid,String username,String oldPassword,String newPassword);

    void changeAvatar(Integer uid, String username, String avatar);
}
