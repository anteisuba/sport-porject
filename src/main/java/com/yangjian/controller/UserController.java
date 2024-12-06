package com.yangjian.controller;

import com.alibaba.fastjson.JSON;
import com.yangjian.bean.QueryInfo;
import com.yangjian.bean.User;
import com.yangjian.dao.Userdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private Userdao udao;
    //分页所需的参数以及模糊查询所需的参数,封装到实体里,然后在service层进行分页和模糊查询
    @RequestMapping("/alluser")
    public String getUserList(QueryInfo queryInfo) {
        //%modle%模糊查询,结果给变量numbers,获取最大列表数和当前编号
        int numbers = udao.getUserCounts("%" + queryInfo.getQuery() + "%");
        int pageStart = (queryInfo.getPageNum() - 1) * queryInfo.getPageSize();
        List<User> users = udao.getAllUser("%" + queryInfo.getQuery() + "%",pageStart,queryInfo.getPageSize());

        //存放结果
        HashMap<String, Object> res = new HashMap<>();
        res.put("numbers",numbers);
        res.put("data",users);
        //返回json字符串
        String res_string = JSON.toJSONString(res);
        return res_string;
    }

    @RequestMapping("/userstate")
    public String updateUserState(@RequestParam("id") Integer id,
                                  @RequestParam("state")Boolean state) {
        int i = udao.updateState(id, state);

        return i > 0 ? "success" : "error";
    }

    @RequestMapping("/addUser")
    public String addUser(@RequestBody User user) {
        user.setRole("普通用户");
        user.setState(false);
        int i = udao.addUser(user);
        return i > 0 ? "success":"error";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(int id) {
        int i = udao.deleteUser(id);
        return i > 0 ? "success":"error";
    }

    @RequestMapping("/getupdate")
    public String getUpdateUser(int id) {
        User user = udao.getUpdateUser(id);
        String string = JSON.toJSONString(user);
        return string;
    }
    //更改用户信息
    @RequestMapping("/edituser")
    public String editUser(@RequestBody User user) {
        int i = udao.editUser(user);
        return i > 0 ? "success":"error";
    }

}
