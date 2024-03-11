package com.luokuans.controller;

import com.luokuans.pojo.Emp;
import com.luokuans.pojo.Result;
import com.luokuans.service.EmpService;
import com.luokuans.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        Emp empLogin = empService.login(emp);

        if (empLogin != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", empLogin.getId());
            claims.put("name", empLogin.getName());
            claims.put("username", empLogin.getUsername());
            String jwt = JwtUtils.generateJwt(claims); //jwt包含了当前登录的员工信息
            return Result.success(jwt);
        }
        //登录失败, 返回错误信息
        return Result.error("用户名或密码错误");
        // return empLogin != null?Result.success():Result.error("用户名或者密码错误！");
    }
}
