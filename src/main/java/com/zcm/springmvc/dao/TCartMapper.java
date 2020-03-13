package com.zcm.springmvc.dao;

import com.zcm.springmvc.entity.CartVo;
import com.zcm.springmvc.entity.TCart;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public interface TCartMapper {
    int deleteByPrimaryKey(Integer cid);

    /**
     * 添加商品到购物车
     * @param record
     * @return
     */
    int insert(TCart record);

    int insertSelective(TCart record);

    /**
     * 查询购物车信息
     * @param cid
     * @return
     */
    TCart selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(TCart record);

    int updateByPrimaryKey(TCart record);

    /**
     * 如果该商品已经添加到购物车，则修改购物车数量
     *
     * @param cid
     * @param num
     * @return
     */
    Integer updateByCid(@Param("cid") Integer cid,
                        @Param("num") Integer num,
                        @Param("modifiedTime")Date modifiedTime,
                        @Param("modifiedUser") String modifiedUser);

    /**
     * 查询该用户是否添加该商品
     * @param uid
     * @param pid
     * @return
     */
    TCart findByUidandPid(@Param("uid") Integer uid,@Param("pid") Integer pid);

    /**
     * 查询该用户购物车中的产品
     * @param cid
     * @return
     */
    List<CartVo> findByCidList(HashMap<String,Object> map);

    /**
     * 查询该用户购物车的总条数
     * @param uid
     * @return
     */
    Integer countRow(Integer uid);

    /**
     * 批量勾选购物车的商品
     * @param cids
     * @return
     */
    List<CartVo> findByCids(HashMap<String,Object> map);

    /**
     * 勾选的总数量
     * @param cids
     * @return
     */
    Integer countFindByCids(List<Integer> cids);

    /**
     * 不分页
     * @param cids
     * @return
     */
   List<CartVo> findBycidsCont(List<Integer> cids);
}