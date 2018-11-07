package com.offcn.service.impl;

import com.offcn.mapper.UserMapper;
import com.offcn.pojo.User;
import com.offcn.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User selectUser(String uname, String upwd) {
        User user = userMapper.selectUser(uname);
        if (user.getUpwd().equals(upwd)){
            return user;
        }
        return null;
    }

    @Override
    public List<User> selectAllUserByPage(Integer currentPageNo, Integer pageSize) {
        List<User> userList = userMapper.selectAllUserByPage((currentPageNo-1)*pageSize,pageSize);
        return userList;
    }

    @Override
    public Integer selectCount() {
        Integer count = userMapper.selectCount();
        return count;
    }

    @Override
    public boolean addUser(User user) {
        try {
            userMapper.addUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserById(Integer uid) {
        return userMapper.getUserById(uid);
    }

    @Override
    public boolean modifyUser(User user) {
        int num = userMapper.modifyUser(user);
        if (num>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserById(Integer uid) {
        userMapper.deleteUserById(uid);
        return true;
    }
}
