package com.zcm.springmvc.controller;

import com.zcm.springmvc.dao.MessageMapper;
import com.zcm.springmvc.entity.Message;
import com.zcm.springmvc.entity.PageBean;
import com.zcm.springmvc.service.MessageService;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @ResponseBody
    @RequestMapping(value = "/login")
    public Message messageAll(Integer id){
       if(id!=null){
          Message message= messageService.selectMessage(id);
           return message;
       }else {
           return null;
       }


    }

    @ResponseBody
    @RequestMapping(value = "/findAll",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public ModelMap messageFindAll(Integer id, String content){
        try {
             ModelMap map =new ModelMap();
            JSONObject jsonObject=messageService.selectByLikeContent(id,content);
                map.put("aa",jsonObject);
            return  map;
        }catch (Exception e){
            e.printStackTrace();
        }
    return  null;
    }

    @RequestMapping(value="/findPage",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap findPagelimit(int curr){
        ModelMap map=new ModelMap();
        PageBean<Message> pageBean=messageService.findByPage(curr);
        map.put("aa",pageBean);
        return map;

    }

}


