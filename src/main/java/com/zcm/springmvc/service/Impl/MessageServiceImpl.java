package com.zcm.springmvc.service.Impl;

import com.zcm.springmvc.dao.MessageMapper;
import com.zcm.springmvc.entity.Message;
import com.zcm.springmvc.entity.PageBean;
import com.zcm.springmvc.service.MessageService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
   private MessageMapper mapper;
    public Message selectMessage(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public JSONObject selectByLikeContent(Integer id, String content) {
        JSONObject jsonObject=new JSONObject();
        if(id!=null&&content!=null&&!content.equals("") ){
           List<Message> message=mapper.selectByLikeContent(id,content);
           jsonObject.put("message",message);
           jsonObject.put("code",1);
           jsonObject.put("state","成功");
        }
        return jsonObject;
    }

    public PageBean<Message> findByPage(int currentPage) {
        HashMap<String,Object> map=new HashMap<String, Object>();
        PageBean<Message> pageBean=new PageBean<Message>();
        //封装当前的页数
        pageBean.setCurrPage(currentPage);
        //每页显示数据
        int pagesize=2;
        pageBean.setPageSize(pagesize);
        //总条数
        int count=mapper.countAll();
        pageBean.setTotalCount(count);
        //总页数
        double tc = count;
        Double num =Math.ceil(tc/pagesize);//向上取整
        pageBean.setTotalPage(num.intValue());
        map.put("start",(currentPage-1)*pagesize);
        map.put("size",pageBean.getPageSize());
       //每页的数据
        List<Message> lsits=mapper.findByPage(map);
        pageBean.setLists(lsits);
        return pageBean;
    }
}
