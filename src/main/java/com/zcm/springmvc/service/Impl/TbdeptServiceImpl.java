package com.zcm.springmvc.service.Impl;

import com.zcm.springmvc.dao.TbdeptMapper;
import com.zcm.springmvc.entity.PageBean;
import com.zcm.springmvc.entity.TbdeptEmpVo;
import com.zcm.springmvc.service.TbdeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbdeptServiceImpl implements TbdeptService {
   @Autowired
    private TbdeptMapper tbdeptMapper;
    public int deleteBetch(HttpServletRequest request) {
     String didStr=request.getParameter("did");
        List<Integer> list=new ArrayList<Integer>();
     if(didStr!=null&&!didStr.equals("")){
         for(String i:didStr.split(",")){
             if(i!=null&&!i.equals("")){
                 list.add(Integer.valueOf(i));
             }

         }
     }
      int count= tbdeptMapper.deleteBatch(list);

        return count;
    }

    public PageBean<TbdeptEmpVo> pageAll(HttpServletRequest request) {
        PageBean pageBean=new PageBean();
        HashMap<String,Object> map=new HashMap<String, Object>();
        pageBean.setCurrPage(Integer.valueOf(request.getParameter("curr")));
        int size=3;
        pageBean.setPageSize(size);
        int count=tbdeptMapper.pageCount(Integer.valueOf(request.getParameter("did")));
        pageBean.setTotalCount(count);
        //总页数
        double tc = count;
        Double num =Math.ceil(tc/size);//向上取整
        pageBean.setTotalPage(num.intValue());
        map.put("start",(pageBean.getCurrPage()-1)*size);
        map.put("size",pageBean.getPageSize());
        map.put("did",Integer.valueOf(request.getParameter("did")));
        List<TbdeptEmpVo> list=tbdeptMapper.findByPage(map);
        pageBean.setLists(list);
        return pageBean;
    }
}
