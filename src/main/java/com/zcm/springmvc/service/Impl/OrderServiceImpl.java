package com.zcm.springmvc.service.Impl;

import com.zcm.springmvc.dao.TAddressMapper;
import com.zcm.springmvc.dao.TCartMapper;
import com.zcm.springmvc.dao.TOrderItemMapper;
import com.zcm.springmvc.dao.TOrderMapper;
import com.zcm.springmvc.entity.*;
import com.zcm.springmvc.service.AddressService;
import com.zcm.springmvc.service.CartService;
import com.zcm.springmvc.service.OrderService;
import com.zcm.springmvc.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private TOrderMapper orderMapper;
    @Autowired
    private TOrderItemMapper orderItemMapper;
    @Autowired
    private TAddressMapper addressMapper;
    @Autowired
    private CartService cartService;
    @Autowired
    private TCartMapper cartMapper;

    /**
     * 创建订单
     * @param aid
     * @param cids
     * @param uid
     * @param username
     * @return
     */
    @Transactional
    public TOrder create(Integer aid, List<Integer> cids, Integer uid, String username) {
        Long totalPrice=0L;
        //创建当前时间对象
        Date now=new Date();
        //查询出勾选购物车的总数量
        Integer coun=cartMapper.countFindByCids(cids);
        // 根据参数cids调用cartService的getVOByCids(cids)得到List<CartVO>类型的购物车数据列表
        PageBean<CartVo> cartVoList=cartService.getVOByCids(cids,1,coun,uid);
        //计算出勾选购物车的商品总价钱
        List<CartVo> lsit=cartVoList.getLists();
        for(CartVo cart:lsit){
            totalPrice+=cart.getPrice()*cart.getNum();
        }
        //根据选择的收货地址

        TAddress address=addressMapper.selectByPrimaryKey(aid);
        TOrder order=new TOrder();
        order.setUid(uid);
        order.setOid(null);
        order.setCreatedTime(now);
        order.setCreatedUser(username);
        order.setModifiedTime(now);
        order.setModifiedUser(username);
        order.setPayTime(now);
        order.setRecvName(username);
        order.setRecvPhone(address.getPhone());
        order.setPayTime(null);
        order.setOrderTime(now);
        order.setTotalPrice(totalPrice);
        order.setStatus(0);
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        Integer row=orderMapper.insert(order);
        Integer oid=order.getOid();
        if(row<=0){
            throw new InsertException("插入失败");
        }
        TOrderItem orderItem=new TOrderItem();
        for(CartVo vo:lsit){
           orderItem.setId(null);
           orderItem.setOid(oid);
           orderItem.setPid(vo.getPid());
           orderItem.setPrice(vo.getPrice());
           orderItem.setNum(vo.getNum());
           orderItem.setImage(vo.getImage());
           orderItem.setTitle(vo.getTitle());
           orderItem.setCreatedTime(now);
           orderItem.setCreatedUser(username);
           orderItem.setModifiedUser(username);
           orderItem.setModifiedTime(now);
           Integer row1=orderItemMapper.insert(orderItem);
           if(row1<=0){
               throw new InsertException("插入失败");
           }
        }
        return order;
    }
}
