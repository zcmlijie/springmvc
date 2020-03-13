package com.zcm.springmvc.service;

import com.zcm.springmvc.entity.PageBean;
import com.zcm.springmvc.entity.TbdeptEmpVo;

import javax.servlet.http.HttpServletRequest;

public interface TbdeptService {
    int deleteBetch (HttpServletRequest request);
    PageBean<TbdeptEmpVo> pageAll(HttpServletRequest request);

}
