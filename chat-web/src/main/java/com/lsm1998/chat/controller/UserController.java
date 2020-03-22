/*
 * 作者：刘时明
 * 时间：2020/3/21-23:10
 * 作用：
 */
package com.lsm1998.chat.controller;

import com.lsm1998.chat.domain.User;
import com.lsm1998.chat.service.UserService;
import com.lsm1998.chat.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController
{
    @Autowired
    private UserService userService;

//    @PostMapping("login")
//    public Map<String, Object> login(String username, String password)
//    {
//        Query query = new Query(Criteria.where("username").is(username).and("password").is(password));
//        return ResultUtil.success(mongoTemplate.findOne(query, User.class));
//    }

    @PostMapping("/register")
    public Map<String, Object> registerUser(@RequestBody User user)
    {
        userService.saveUser(user);
        return ResultUtil.success("ok");
    }

    @GetMapping("find/{id}")
    public Map<String, Object> find(@PathVariable long id)
    {
        return ResultUtil.success(userService.find(id));
    }
}
