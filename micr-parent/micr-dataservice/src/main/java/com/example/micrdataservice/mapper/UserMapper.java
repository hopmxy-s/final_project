package com.example.micrdataservice.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.api.model.User;
import org.example.api.pojo.UserAccountInfo;

public interface UserMapper {

//    count register number
    int selectCountUsers();
    /**search user through phone number**/
    User selectByPhone(@Param("phone") String phone);

    int insertReturnPrimaryKey(User user);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectLogin(@Param("phone") String phone, @Param("loginPassword") String newPassword);

    UserAccountInfo selectUserAccountById(@Param("uid") Integer uid);
}