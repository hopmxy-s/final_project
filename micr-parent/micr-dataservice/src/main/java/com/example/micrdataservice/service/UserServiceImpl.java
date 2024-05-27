package com.example.micrdataservice.service;

import com.example.micrdataservice.mapper.FinanceAccountMapper;
import com.example.micrdataservice.mapper.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.api.model.FinanceAccount;
import org.example.api.model.User;
import org.example.api.pojo.UserAccountInfo;
import org.example.api.service.UserService;
import org.example.common.util.CommonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@DubboService(interfaceClass = UserService.class, version = "1.0")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private FinanceAccountMapper financeAccountMapper;
    @Value("${ylb.config.password-salt}")
    private String passwordSalt;
    @Override
    public User queryByPhone(String phone) {
        User user = null;
        if (CommonUtil.checkPhone(phone)){
            user = userMapper.selectByPhone(phone);
        }
        return user;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized int userRegister(String phone, String password) {
        int result = 0;
        if (CommonUtil.checkPhone(phone) && (password != null && password.length() == 32)){

            User queryUser = userMapper.selectByPhone(phone);
            if (queryUser == null){
                // password md5 encryption

                String newPassword = DigestUtils.md5Hex(password + passwordSalt);

                User user = new User();
                user.setPhone(phone);
                user.setLoginPassword(newPassword);
                user.setAddTime(new Date());
                userMapper.insertReturnPrimaryKey(user);

                FinanceAccount account = new FinanceAccount();
                account.setUid(user.getId());
                account.setAvailableMoney(new BigDecimal("0"));
                financeAccountMapper.insertSelective(account);

                result = 1;
            } else {
                // phone number exists
                result = 2;
            }


        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User userLogin(String phone, String password) {


        User user = null;
        if (CommonUtil.checkPhone(phone) && (password != null && password.length() == 32)){
            String newPassword = DigestUtils.md5Hex(password + passwordSalt);
            user = userMapper.selectLogin(phone, newPassword);
            if (user != null) {
                user.setLastLoginTime(new Date());
                userMapper.updateByPrimaryKeySelective(user);
            }
        }
        return user;
    }

    @Override
    public UserAccountInfo queryUserAllInfo(Integer uid) {
        UserAccountInfo info = null;
        if (uid != null && uid > 0) {
            info = userMapper.selectUserAccountById(uid);
        }

        return info;
    }

    @Override
    public User queryById(Integer uid) {

        User user = null;
        if (uid != null && uid > 0) {
            user = userMapper.selectByPrimaryKey(uid);
        }

        return user;
    }
}
