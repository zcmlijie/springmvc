package com.zcm.springmvc.controller;

import com.zcm.springmvc.dao.TUserMapper;
import com.zcm.springmvc.entity.TUser;
import com.zcm.springmvc.service.UserService;
import com.zcm.springmvc.service.ex.InsertException;
import com.zcm.springmvc.service.ex.UsernameDuplicateException;
import com.zcm.springmvc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private TUserMapper tUserMapper;
    @Autowired
    private  ExcleImpl excle;
    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Void> regist(@RequestBody  TUser tUser){
        userService.reg(tUser);
        return  new JsonResult<Void>(OK);
       /* try {
            userService.reg(tUser);
            jsonResult.setState(1);


        }catch (UsernameDuplicateException e){
            jsonResult.setState(2);
            jsonResult.setMessage("用户名已经注册");
        }catch (InsertException e){
            jsonResult.setState(3);
            jsonResult.setMessage("插入错误");
        }
        return  jsonResult;*/
    }

    /**
     * 用户登录
     * @param name
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method =RequestMethod.POST)
    @ResponseBody
    public JsonResult<TUser> userLogin(String name,String password,HttpServletRequest request){
        TUser user=tUserMapper.seletByName(name);
        HttpSession session=request.getSession(true);
        session.setAttribute("uid",user.getUid());
        session.setAttribute("username",user.getUsername());
        TUser data= userService.login(name,password);
        return  new JsonResult<TUser>(OK,data);
    }

    /**
     * 修改密码
     * @param newPassword
     * @param oldPassword
     * @param session
     * @return
     */
    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    @ResponseBody
    public  JsonResult<Void> changePassword(String newPassword,String oldPassword,HttpSession session){
        Integer uid=getUidFromSession(session);
        String  name=getUsernameFromSession(session);
        userService.changePassword(uid,name,oldPassword,newPassword);
        return new JsonResult<Void>(OK);
    }

    /**
     *根据UID查询用户信息
     * @param session
     * @return
     */
    @RequestMapping(value = "/getByUid",method = RequestMethod.GET)
    @ResponseBody
    public  JsonResult<TUser> getByUid(HttpSession session){
        // 从Session中获取uid
        Integer uid = getUidFromSession(session);
        TUser user=userService.getByUid(uid);
        return new JsonResult<TUser>(OK,user);
    }

    /**
     * 修改用户信息
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value = "/changeInfo",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Void> changeInfo(TUser user,HttpSession session){
        user.setUid(Integer.valueOf(session.getAttribute("uid").toString()));
        user.setUsername(session.getAttribute("username").toString());
        userService.changeInfo(user);
        return new JsonResult<Void>(OK) ;
    }

    /**
     * 文件上传
     * @param file
     * @param session
     * @param req
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping(value = "/uplode",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Void> upload(@RequestParam("file") MultipartFile file,HttpSession session,HttpServletRequest req)
            throws IllegalStateException, IOException {
        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
            return new JsonResult<Void>(-200);
        }
        // 将上传的文件保存到哪个位置
        String path =req.getServletContext().getRealPath("/WEB-INF/file");
        //将文件名保存到数据库
        userService.changeAvatar(getUidFromSession(session),getUsernameFromSession(session),path);
        // 获取原文件名
        String fileName = file.getOriginalFilename();

        // 保存上传的文件时使用的文件名
        String filename = UUID.randomUUID().toString();
        // 保存上传的文件时使用的扩展名
        String suffix = "";
        int beginIndex = fileName.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = fileName.substring(beginIndex);
        }

        String child = filename + suffix;
        // 创建文件实例
        File filePath = new File(path, child);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }
        // 写入文件
        file.transferTo(filePath);
        return new JsonResult<Void>(OK);
    }

    /**
     * poi excel下载
     * @param response
     * @param request
     * @return
     */
   @RequestMapping(value = "/down",method = RequestMethod.POST)
   @ResponseBody
    public String down(HttpServletResponse response,HttpServletRequest request){
       response.setContentType("application/binary;charset=UTF-8");
       try{
           ServletOutputStream out=response.getOutputStream();
           try {
               //设置文件头：最后一个参数是设置下载文件名(这里我们叫：张三.pdf)
               String  name=request.getParameter("name");
               response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(name+".xls", "UTF-8"));
           } catch (UnsupportedEncodingException e1) {
               e1.printStackTrace();
           }

           String[] titles = { "部门id", "年龄", "性别", "工作时间","工作编号","员工名字" };
           excle.export(titles, out,request);
           return "success";
       } catch(Exception e){
           e.printStackTrace();
           return "导出信息失败";
       }
   }


}
