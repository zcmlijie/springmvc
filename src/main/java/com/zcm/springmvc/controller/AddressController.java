package com.zcm.springmvc.controller;

import com.zcm.springmvc.entity.TAddress;
import com.zcm.springmvc.service.AddressService;
import com.zcm.springmvc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "/address")
public class AddressController extends BaseController{
    @Autowired
    private AddressService addressService;

    /**
     * 增加收货地址
     * @return
     *
     * @RequestBody postman传递过来的json对象，row设置，必须使用此注解，否则后台接受参数为null
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Void> saveAll(@RequestBody TAddress tAddress, HttpSession session){
        if(tAddress!=null){
            Integer uid=getUidFromSession(session);
            String username=getUsernameFromSession(session);
            Integer row= addressService.addAll(uid,username,tAddress);
            return new JsonResult<Void>(OK,"添加成功",null);
        }
        return null;
    }

    /**
     * 修改默认地址
     * @param request
     * @param sessin
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer>updateDufal(HttpServletRequest request,HttpSession sessin){
        Integer aid=Integer.valueOf(request.getParameter("aid"));
        Integer uid=getUidFromSession(sessin);
        String username=getUsernameFromSession(sessin);
       Integer row= addressService.updateAllDeful(uid,username,aid);
        return new JsonResult<Integer>(OK,"修改成功",row);
    }

    /**
     * 删除收货地址
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/delet",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> deletAddress(HttpServletRequest request,HttpSession session){
        Integer aid=Integer.valueOf(request.getParameter("aid"));
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        Integer row =addressService.deleteAddress(aid,uid,username);
        return new JsonResult<Integer>(OK,"删除成功",row);
    }
}
