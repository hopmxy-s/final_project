package com.example.micrdataservice.mapper;

import org.example.api.model.User;

public interface UserMapper {

//    count register number
    int selectCountUsers();

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}