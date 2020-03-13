package com.zcm.springmvc.service.Impl;

import com.zcm.springmvc.dao.TProductMapper;
import com.zcm.springmvc.entity.TProduct;
import com.zcm.springmvc.service.ProductService;
import com.zcm.springmvc.service.ex.InsertException;
import com.zcm.springmvc.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private TProductMapper tProductMapper;
    public Integer insertAll(TProduct tProduct,String username) {
        if(tProduct!=null){
            Date now=new Date();
            tProduct.setId(tProduct.getId());
            tProduct.setCategoryid(tProduct.getCategoryid());
            tProduct.setImage(tProduct.getImage());
            tProduct.setItemType(tProduct.getItemType());
            tProduct.setNum(tProduct.getNum());
            tProduct.setPrice(tProduct.getPrice());
            tProduct.setPriority(tProduct.getPriority());
            tProduct.setTitle(tProduct.getTitle());
            tProduct.setStatus(tProduct.getStatus());
            tProduct.setSellpoint(tProduct.getSellpoint());
            tProduct.setCreatedTime(now);
            tProduct.setCreatedUser(username);
            tProduct.setModifiedTime(now);
            tProduct.setModifiedUser(username);
            Integer row=tProductMapper.insert(tProduct);
            if(row<0){
                throw  new InsertException("插入错误");
            }
        }
        return null;
    }

    /**
     * 查询热销产品

     * @return
     */
    public List<TProduct> getHost() {
        List<TProduct> list=findHostList();
        return list;
    }

    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    public TProduct getByid(Integer id) {
        TProduct tProduct=tProductMapper.selectByPrimaryKey(id);
        tProduct.setCreatedTime(null);
        tProduct.setCreatedUser(null);
        tProduct.setModifiedTime(null);
        tProduct.setModifiedUser(null);
        if(tProduct==null){
            throw new ProductNotFoundException("没有商品数据");
        }
        return tProduct;
    }

    private List<TProduct> findHostList(){
        return  tProductMapper.findHostList();
    }
}
