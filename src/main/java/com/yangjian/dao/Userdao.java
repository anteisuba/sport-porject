package com.yangjian.dao;

import com.yangjian.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Userdao {

    //通过用户名和密码获取当前用户信息
    public User getUserByMassage(@Param("username") String username,@Param("password") String password);

    //获取所有的用户信息
    public List<User> getAllUser(@Param("username")String username,@Param("pageStart") int PageStart,@Param("pageSize") int  pageSize);
    //获取所有的用户的个数
    public int getUserCounts(@Param("username") String username);

    //更新状态栏
    public int updateState(Integer id,Boolean state);
    //添加用户
    public int addUser(User user);
    //删除用户,通过主键id
    public int deleteUser(int id);
    //改
    public User getUpdateUser(int id);
    public int editUser(User user);
}
