package com.zcm.springmvc.service;

import com.alibaba.fastjson.JSONObject;
import com.zcm.springmvc.entity.Tbdept;
import com.zcm.springmvc.entity.Tdemp;

import javax.servlet.http.HttpServletRequest;

public interface TdempService {
    int save (HttpServletRequest request);

    int saveAll(HttpServletRequest request);

}
