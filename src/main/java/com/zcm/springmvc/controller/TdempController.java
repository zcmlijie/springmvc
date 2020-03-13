package com.zcm.springmvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.zcm.springmvc.entity.Tbdept;
import com.zcm.springmvc.entity.Tdemp;
import com.zcm.springmvc.service.TdempService;
import com.zcm.springmvc.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/tdemp")
public class TdempController {
    @Autowired
    private TdempService tdempService;
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap save(HttpServletRequest request){
        String aa=request.getParameter("dname");
        /*String data= JsonUtils.toJSON(request);
        JSONObject jsonObject=JSONObject.parseObject(data);*/
        ModelMap map=new ModelMap();
       int tdempcoutn=tdempService.save(request);
       map.put("lsit",tdempcoutn);
       map.put("code",200);
        return map;
    }
    @RequestMapping(value = "/saveAll",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap saveTransactional(HttpServletRequest request){
        ModelMap map=new ModelMap();
        int tdempcoutn=tdempService.saveAll(request);
        map.put("aa",tdempcoutn);
        map.put("code",200);
        map.put("start","成功");
        return map;
    }

}
