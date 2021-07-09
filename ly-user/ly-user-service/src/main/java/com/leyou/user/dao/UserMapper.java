package com.leyou.user.dao;


import com.leyou.pojo.User;

public interface UserMapper {

    int selectCount(User user);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);
}