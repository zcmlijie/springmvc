package com.zcm.springmvc.controller;

import com.zcm.springmvc.entity.ProvinceCityDistrict;
import com.zcm.springmvc.service.ProvinceCityDistrictService;
import com.zcm.springmvc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/city")
public class ProvinceCityDistrictController extends BaseController{
    @Autowired
    private ProvinceCityDistrictService provinceCityDistrictService;

    /**
     * 根据pid 查询省市区
     * @param pid
     * @return
     */
    @RequestMapping(value = "/findCity",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<List<ProvinceCityDistrict>> findAllList(Integer pid){
        List<ProvinceCityDistrict> list=provinceCityDistrictService.findAll(pid);
        return new JsonResult<List<ProvinceCityDistrict>>(OK,list);
    }
}
