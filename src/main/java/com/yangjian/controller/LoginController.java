package com.yangjian.controller;

import com.alibaba.fastjson.JSON;
import com.yangjian.bean.User;
import com.yangjian.dao.Userdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {
    @Autowired
    Userdao userdao;

    @RequestMapping("/login")
    public String login(@RequestBody User user) {

        String flag = "error";
        //根据用户名和密码查询用户
        User us = userdao.getUserByMassage(user.getUsername(),user.getPassword());

        //放入Map集合里,键是String类型,值为Object类型
        HashMap<String,Object> res = new HashMap<>();
        //判断用户是否存在
        if(us!=null) {
            flag = "ok";
        }
        //封装返回数据
        res.put("flag",flag);
        res.put("user",user);
        //将HashMap转换为json字符串
        String res_json = JSON.toJSONString(res);
        //返回json字符串
        return res_json;
    }
}
