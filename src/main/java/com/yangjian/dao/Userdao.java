package com.yangjian.dao;

import com.yangjian.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Userdao {
    public User getUserByMassage(@Param("username") String username,@Param("password") String password);

}
