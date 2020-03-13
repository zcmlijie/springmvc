package com.zcm.springmvc.controller;

import com.zcm.springmvc.service.ex.*;
import com.zcm.springmvc.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制层基类
 */
public class BaseController {
    /**
     * 操作成功的状态码
     */
    public static final int OK = 200;

    /**
     * 统一异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> jr = new JsonResult<Void>(e);

        if (e instanceof UsernameDuplicateException) {
            jr.setState(400);
        } else if (e instanceof InsertException) {
            jr.setState(500);
        }else if(e instanceof UserNotFoundException) {
            jr.setState(600);

        }else if(e instanceof PasswordNotMatchException){
            jr.setState(700);
        }else if(e instanceof ProductNotFoundException){
            jr.setState(800);
            jr.setMessage("无商品数据");
        }else if(e instanceof UpdateException){
            jr.setMessage("更新错误");
        }

        return jr;
    }

    /**
     * 从Session中获取uid
     * @param session HttpSession对象
     * @return 当前登录的用户的id
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(
                session.getAttribute("uid").toString());
    }

    /**
     * 从Session中获取用户名
     * @param session HttpSession对象
     * @return 当前登录的用户名
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }


}
