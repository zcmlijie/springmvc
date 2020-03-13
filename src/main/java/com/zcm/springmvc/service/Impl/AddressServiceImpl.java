package com.zcm.springmvc.service.Impl;

import com.zcm.springmvc.dao.TAddressMapper;
import com.zcm.springmvc.entity.TAddress;
import com.zcm.springmvc.service.AddressService;
import com.zcm.springmvc.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private TAddressMapper tAddressMapper;
    public Integer addAll(Integer uid,String username,TAddress tAddress) {
        if(uid!=null){
            //统计当前收货地址
            Integer row=tAddressMapper.countByUid(uid);
            //-128到127
            //设置为默认地址
            if(row.intValue()==0){
                tAddress.setIsDefault(1);
                insertAll(uid,username,tAddress);
            }else {
                tAddress.setIsDefault(0);
                insertAll(uid,username,tAddress);
            }

        }
        return null;
    }

    /**
     * 设置默认地址
     * @param uid
     * @param username
     * @param aid
     * @return
     */
    @Transactional
    public Integer updateAllDeful(Integer uid, String username, Integer aid) {
        //根据aid查询用户收货地址
        TAddress tAddress=tAddressMapper.selectByPrimaryKey(aid);
       if(tAddress!=null){
         if(tAddress.getUid().equals(uid)){
             Date now=new Date();
          Integer row=tAddressMapper.updageAllDeful(uid,username,now);
          if(row<0){
              throw new UpdateException("修改失败");
          }
          Integer row1=tAddressMapper.updateIsDeful(aid,username,now);
          if(row1<0){
              throw  new UpdateException("修改失败");
          }
         } else {
             throw new AccessDeniedException("非法访问");
         }
       }else {
           throw  new AddressNotFoundException("收货地址不存在");
       }
        return null;
    }

    /**
     * 删除收货地址
     * 1.根据aid是否有数据
     * 2.UId是否为登录的uid
     * 3.删除的该数据是否为最后一条
     * 4.是否为默认数据
     * 5.
     * @param aid
     * @param uid
     * @param username
     * @return
     */
    public Integer deleteAddress(Integer aid, Integer uid,String username) {
        if(aid!=null){
            TAddress tAddress=tAddressMapper.selectByPrimaryKey(aid);
            if(tAddress!=null){
                if(uid.equals(tAddress.getUid())){
                    //统计用户的地址数据量
                    Integer counts=tAddressMapper.countByUid(uid);
                   //判断是否删除的是默认地址
                   if(tAddress.getIsDefault().intValue()==1&&counts>1){
                       Integer row=tAddressMapper.deleteAddress(aid);
                       if(row<0){
                           throw new DeleteException("删除错误");
                       }
                       //重新设置新的默认地址
                       Date now=new Date();
                       TAddress address=tAddressMapper.selectnewAddress();
                       Integer count=tAddressMapper.updateIsDeful(address.getAid(),username,now);
                       if(count<0){
                           throw new UpdateException("更新数据出错");
                       }
                   }else {
                       Integer row=tAddressMapper.deleteAddress(aid);
                       if(row<0){
                           throw new DeleteException("删除错误");
                       }
                   }
                }else {
                    throw new AccessDeniedException("非法数据");
                }
            }else {
                throw  new AddressNotFoundException("数据不存在");
            }
        }
        return null;
    }

    private void insertAll(Integer uid,String username,TAddress tAddress){
        Date now=new Date();
        tAddress.setAid(null);
        tAddress.setUid(uid);
        tAddress.setAddress(tAddress.getAddress());
        tAddress.setAreaCode(tAddress.getAreaCode());
        tAddress.setAreaName(tAddress.getAreaName());
        tAddress.setCityCode(tAddress.getCityCode());
        tAddress.setCityName(tAddress.getCityName());
        tAddress.setName(username);
        //tAddress.setIsDefault(0);//默认地址
        tAddress.setCreatedTime(now);
        tAddress.setCreatedUser(username);
        tAddress.setModifiedTime(now);
        tAddress.setModifiedUser(username);
        tAddress.setPhone(tAddress.getPhone());
        tAddress.setZip(tAddress.getZip());
        tAddress.setTel(tAddress.getTel());
        tAddress.setTag(tAddress.getTag());
        tAddress.setProvinceCode(tAddress.getProvinceCode());
        tAddress.setProvinceName(tAddress.getProvinceName());
        Integer count=tAddressMapper.insertSelective(tAddress);
        if(count<0){
            throw  new InsertException("插入失败");
        }
    }


}
