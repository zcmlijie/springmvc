package com.zcm.springmvc.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.zcm.springmvc.dao.TbdeptMapper;
import com.zcm.springmvc.dao.TdempMapper;
import com.zcm.springmvc.entity.Tbdept;
import com.zcm.springmvc.entity.Tdemp;
import com.zcm.springmvc.service.TdempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service

public class TdempServiceImpl implements TdempService {
    @Autowired
    private TdempMapper tdempMapper;
    @Autowired
    private TbdeptMapper tbdeptMapper;
    @Transactional(rollbackFor=Exception.class)
    public int save(HttpServletRequest request) {
        Integer did=null;
        Tbdept tbdept=new Tbdept();
        Tdemp tdemp=new Tdemp();
        tbdept.setDid(null);
        tbdept.setDname(request.getParameter("dname"));
        int deptcont=tbdeptMapper.insert(tbdept);
        if(deptcont>0){
           did =tbdept.getDid();
        }else {
            throw  new RuntimeException();
        }

        tdemp.setDid(did);
        tdemp.setEid(null);
        try {
            String workDate=request.getParameter("workdate");
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            tdemp.setWorkdate(simpleDateFormat.parse(workDate));
        }catch (Exception e){
            e.printStackTrace();
        }

        tdemp.setPassword(request.getParameter("password"));
        tdemp.setEname(request.getParameter("ename"));
        tdemp.setAge(Integer.valueOf(request.getParameter("age")));
        tdemp.setGende(Boolean.valueOf(request.getParameter("gende")));
        int tdempcount=tdempMapper.insert(tdemp);
        if(tdempcount<0){
            throw new RuntimeException();
        }
        return tdempcount;
    }
    @Transactional(rollbackFor=RuntimeException.class)
    public int saveAll(HttpServletRequest request)  {
        List<Tdemp> list=new ArrayList<Tdemp>();
        for (int i=0;i<5;i++) {
            Tdemp tdemp = new Tdemp();
            tdemp.setEid(null);
            tdemp.setDid(null);
            tdemp.setGende(request.getParameter("gende").equals("1")?Boolean.valueOf("true"):Boolean.valueOf(request.getParameter("gende")));
            tdemp.setAge(Integer.valueOf(request.getParameter("age")));
            tdemp.setPassword(request.getParameter("password"));
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                tdemp.setWorkdate(simpleDateFormat.parse(request.getParameter("workdate")));

            } catch (Exception e) {
                e.printStackTrace();
            }
            tdemp.setEname(request.getParameter("ename"));
            list.add(tdemp);
        }
       if(list.size()>2){
           List<Integer> list1=new ArrayList<Integer>();
           tdempMapper.insertList(list);
           for (Tdemp li:list) {
             list1.add(li.getEid());
           }
         System.out.println(list1);
       }else {
           throw new RuntimeException("插入条数多了");
       }
        return 0;
    }
}
