package com.zcm.springmvc.controller;

import com.zcm.springmvc.entity.TProduct;
import com.zcm.springmvc.service.ProductService;
import com.zcm.springmvc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Void> inserAll(@RequestBody  TProduct product, HttpSession session){
        productService.insertAll(product,getUsernameFromSession(session));
        return new JsonResult<Void>(OK);
    }

    /**
     * 查找热门商品
     * @return
     */
    @RequestMapping(value = "/findHost",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<List<TProduct>> getHostList(){
        List<TProduct> list=productService.getHost();
      return  new JsonResult<List<TProduct>>(OK,"热门商品",list);
    }

    /**
     * 查询商品详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/findByid",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<TProduct> findByID(Integer id){
        TProduct tProduct=productService.getByid(id);
        return new JsonResult<TProduct>(OK,tProduct);
    }
}
