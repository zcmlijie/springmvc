package com.zcm.springmvc.controller;

import com.zcm.springmvc.entity.TOrder;
import com.zcm.springmvc.service.OrderService;
import com.zcm.springmvc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController{
    @Autowired
    private OrderService orderService;
    /**
     * 创建订单
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<TOrder> createOrder(HttpServletRequest request, HttpSession session){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        String cids=request.getParameter("cids");
        ArrayList<Integer> list=new ArrayList<Integer>();
        for(String cid:cids.split(",")){
            list.add(Integer.valueOf(cid));
        }
        Integer aid=Integer.valueOf(request.getParameter("aid"));
        TOrder order=orderService.create(aid,list,uid,username);
        return new JsonResult<TOrder>(OK,order);
    }
}
