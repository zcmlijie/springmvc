package com.zcm.springmvc.controller;

import com.zcm.springmvc.entity.PageBean;
import com.zcm.springmvc.entity.TbdeptEmpVo;
import com.zcm.springmvc.service.TbdeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/tbdept")
public class TbdeptController {
    @Autowired
    private TbdeptService tbdeptService;
    @RequestMapping(value = "/deleteAll",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap deletAll(HttpServletRequest request){
        ModelMap map=new ModelMap();
        tbdeptService.deleteBetch(request);
        map.put("code",200);
        return map;
    }
    @RequestMapping(value = "/pageAll",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap PagefidnAll(HttpServletRequest request){
        ModelMap map=new ModelMap();
        PageBean<TbdeptEmpVo> pageBean=tbdeptService.pageAll(request);
        map.put("page",pageBean);
        map.put("start","分页成功");
        return map;
    }
}
