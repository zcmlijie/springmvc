package com.zcm.springmvc.dao;

import com.zcm.springmvc.entity.Message;
import com.zcm.springmvc.entity.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
  //模糊查询
   List<Message> selectByLikeContent(@Param("id")Integer id, @Param("content")String content);

    /**
     * findByPageLimit 分页
     * @param map
     * @return
     */

    List<Message> findByPage(HashMap<String,Object> map);

    /**
     *
     * @return
     */
    int countAll();
}