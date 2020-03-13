package com.zcm.springmvc.service;

import com.zcm.springmvc.entity.Message;
import com.zcm.springmvc.entity.PageBean;
import net.sf.json.JSONObject;

public interface MessageService {
    Message selectMessage(Integer id);

    JSONObject selectByLikeContent(Integer id,String content);

     PageBean<Message> findByPage(int currentPage);


}
