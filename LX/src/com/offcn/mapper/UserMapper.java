package com.offcn.mapper;

import com.offcn.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 根据用户名查出用户
     * @param uname
     * @return
     */
    User selectUser(@Param("uname") String uname);

    /**
     * 分页查询
     * @param currentPageNo 当前页
     * @param pageSize 页量
     * @return
     */
    List<User> selectAllUserByPage(@Param("currentPageNo") Integer currentPageNo,
                                   @Param("pageSize") Integer pageSize);

    /**
     * 总记录数
     * @return
     */
    Integer selectCount();

    /**
     * 增加一个用户
     * @param user 新用户
     */
    void addUser(User user)throws Exception;

    /**
     * 根据id查询用户
     * @param uid 用户ID
     * @return
     */
    User getUserById(@Param("uid")Integer uid);

    /**
     * 修改用户
     * @param user
     */
    int modifyUser(User user);

    /**
     * 根据id删除用户
     * @param uid
     */
    void deleteUserById(@Param("uid")Integer uid);
}
