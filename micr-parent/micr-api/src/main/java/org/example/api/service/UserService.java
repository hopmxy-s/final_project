package org.example.api.service;

import org.example.api.model.User;
import org.example.api.pojo.UserAccountInfo;

public interface UserService {

    /**
     * search data according to phone number
     **/
    User queryByPhone(String phone);

    int userRegister(String phone, String password);

    User userLogin(String phone, String pword);

    UserAccountInfo queryUserAllInfo(Integer uid);

    User queryById(Integer uid);
}
