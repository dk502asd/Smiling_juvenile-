package com.offcn.service;

import com.offcn.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 查出用户
     * @param uname
     * @param upwd
     * @return
     */
    User selectUser(String uname,String upwd);
    /**
     * 分页查询
     * @param currentPageNo 当前页
     * @param pageSize 页量
     * @return
     */
    List<User> selectAllUserByPage(Integer currentPageNo, Integer pageSize);
    /**
     * 总记录数
     * @return
     */
    Integer selectCount();
    /**
     * 增加一个用户
     * @param user 新用户
     */
    boolean addUser(User user);
    /**
     * 根据id查询用户
     * @param uid 用户ID
     * @return
     */
    User getUserById(Integer uid);
    /**
     * 修改用户
     * @param user
     */
    boolean modifyUser(User user);
    /**
     * 根据id删除用户
     * @param uid
     */
    boolean deleteUserById(Integer uid);
}
