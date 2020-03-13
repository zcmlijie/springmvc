package com.zcm.springmvc.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.zcm.springmvc.entity.CartVo;
import com.zcm.springmvc.entity.PageBean;
import com.zcm.springmvc.entity.TCart;
import com.zcm.springmvc.service.CartService;
import com.zcm.springmvc.service.ex.UpdateException;
import com.zcm.springmvc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class CartController extends BaseController{
    @Autowired
    private CartService cartService;

    /**
     * 添加购物车
     * @param cart
     * @param session
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Void> addCart(@RequestBody TCart cart, HttpSession session){
        String username=getUsernameFromSession(session);
        Integer uid=getUidFromSession(session);
        cart.setUid(uid);
        cartService.addProCart(cart,username);
        return new JsonResult<Void>(OK,"添加购物车成功",null);
    }

    /**
     * 分页查询该用户的购物车
     * @param session
     * @param curr
     * @param size
     * @return
     */
    @RequestMapping(value = "/findPage",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<PageBean<CartVo>> findCarePage(HttpSession session,Integer curr,Integer size){
        Integer uid=getUidFromSession(session);
        PageBean<CartVo> pageBean=cartService.findByUidPage(curr,size,uid);
        return new JsonResult<PageBean<CartVo>>(OK,"查询成功",pageBean);
    }

    /**
     * 增加购物车数量
     * @param session
     * @param cid
     * @return
     */
    @RequestMapping(value = "/updateNum",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> updateNum(HttpSession session,Integer cid){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        Integer num=cartService.updateNum(cid,uid,username);
        if(num>=30){
            return new JsonResult<Integer>(-100,"数量超过上限",null);
        }
        return new JsonResult<Integer>(OK,"增加成功",num);
    }

    /**
     * 显示勾选订单
     * @return
     */
    @RequestMapping(value = "/findPagelist",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<PageBean<CartVo>> findPage(HttpServletRequest request,HttpSession session){
        Integer curr=Integer.valueOf(request.getParameter("curr"));
        Integer size=Integer.valueOf(request.getParameter("size"));
        String cids=request.getParameter("cids");
        Integer uid=getUidFromSession(session);
        ArrayList<Integer> lista=new ArrayList<Integer>();
        for(String s:cids.split(",")){
            lista.add(Integer.valueOf(s));
        }
       PageBean<CartVo> list=cartService.getVOByCids(lista,curr,size,uid);
        return new JsonResult<PageBean<CartVo>>(OK,list);
    }
}
