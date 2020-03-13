package com.zcm.springmvc.service.Impl;

import com.zcm.springmvc.dao.TCartMapper;
import com.zcm.springmvc.dao.TProductMapper;
import com.zcm.springmvc.entity.CartVo;
import com.zcm.springmvc.entity.PageBean;
import com.zcm.springmvc.entity.TCart;
import com.zcm.springmvc.entity.TProduct;
import com.zcm.springmvc.service.CartService;
import com.zcm.springmvc.service.ex.AccessDeniedException;
import com.zcm.springmvc.service.ex.CartNotFoundException;
import com.zcm.springmvc.service.ex.InsertException;
import com.zcm.springmvc.service.ex.UpdateException;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
   @Autowired
    private TCartMapper cartMapper;
   @Autowired
   private TProductMapper productMapper;
    public void addProCart(TCart tCart,String username) {
        TProduct product=productMapper.selectByPrimaryKey(tCart.getPid());
        Long price=product.getPrice();
        Date now=new Date();
       //根据pid 和uid 查询购物车的数据
        if(tCart!=null){
           TCart cart= cartMapper.findByUidandPid(tCart.getUid(),tCart.getPid());
            if(cart!=null){
                //则修改数量，从查询结果中抽取数量增加
                Integer num=cart.getNum();
                num++;
                Integer row=cartMapper.updateByCid(cart.getCid(),num,now,username);
                if(row<=0){
                    throw  new UpdateException("修改数据失败");
                }
            }else {
                //增加数据

                tCart.setCid(null);
                tCart.setUid(tCart.getUid());
                tCart.setPid(tCart.getPid());
                tCart.setNum(1);
                tCart.setPrice(price);
                tCart.setCreatedTime(now);
                tCart.setCreatedUser(username);
                tCart.setModifiedTime(now);
                tCart.setModifiedUser(username);
                Integer row=cartMapper.insert(tCart);
                if(row<0){
                    throw new InsertException("插入失败");
                }
            }
        }
    }

    /**
     * 分页获取该用户购物车
     * @param curr
     * @param size
     * @param uid
     * @return
     */
    public PageBean<CartVo> findByUidPage(Integer curr, Integer size, Integer uid) {
        PageBean<CartVo> pageBean=new PageBean<CartVo>();
        HashMap<String,Object> map=new HashMap<String, Object>();
        pageBean.setCurrPage(curr);
        pageBean.setPageSize(size);
        Integer countRow=cartMapper.countRow(uid);
        pageBean.setTotalCount(countRow);
        //总页数
        double tc = countRow;
        Double num =Math.ceil(tc/size);//向上取整
        pageBean.setTotalPage(num.intValue());
        map.put("start",(pageBean.getCurrPage()-1)*size);
        map.put("size",pageBean.getPageSize());
        map.put("uid",uid);
        List<CartVo> list=cartMapper.findByCidList(map);
        pageBean.setLists(list);
        return pageBean;
    }

    /**
     * 增加购物车商品数量
     * @param cid
     * @param uid
     * @param username
     */
    public Integer updateNum(Integer cid, Integer uid, String username) {
       if(cid!=null){
           //查询购物车数据
           TCart cart=cartMapper.selectByPrimaryKey(cid);
           if(cart!=null){
             //是否是该用户登录
               if(cart.getUid().equals(uid)){
                   //获取原购物车数量
                   Integer num=cart.getNum();
                   Date now=new Date();
                   if(num<30){
                       num=cart.getNum()+1;
                       Integer row= cartMapper.updateByCid(cid,num,now,username);
                       if(row<=0){
                           throw new UpdateException("更新错误");
                       }
                       return num;
                   }else {
                       num=30;
                       Integer row= cartMapper.updateByCid(cid,num,now,username);
                       if(row<=0){
                           throw new UpdateException("更新错误");
                       }
                       return num;
                   }

               }else {
                   throw new AccessDeniedException("用户非法登录");
               }
           }else {
               throw new CartNotFoundException("购物车无数据");
           }
       }
       return null;
    }

    /**
     * 批量勾选购物车
     * @param cids
     * @param curr
     * @param size
     * @return
     */
    public PageBean<CartVo> getVOByCids(List<Integer> cids, Integer curr, Integer size,Integer uid) {
        PageBean pageBean=new PageBean();
        HashMap<String,Object> map=new HashMap<String, Object>();
        pageBean.setCurrPage(curr);
        pageBean.setPageSize(size);
        Integer count=cartMapper.countFindByCids(cids);
        pageBean.setTotalCount(count);
        //总页数
        double tc = count;
        Double num =Math.ceil(tc/size);//向上取整
        pageBean.setTotalPage(num.intValue());
        map.put("start",(pageBean.getCurrPage()-1)*size);
        map.put("size",pageBean.getPageSize());
        map.put("cids",cids);
        List<CartVo> list=cartMapper.findByCids(map);
        Iterator<CartVo> it = list.iterator();
        //删除list中的不合法数据
        while (it.hasNext()) {
            CartVo cart = it.next();
            if (!cart.getUid().equals(uid)) {
                it.remove();
            }
        }
        pageBean.setLists(list);
        return pageBean;
    }

}
