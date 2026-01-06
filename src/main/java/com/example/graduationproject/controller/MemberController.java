package com.example.graduationproject.controller;

import com.example.graduationproject.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/mem")
public class MemberController {
    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    //查询所有用户列表
    @GetMapping ("/findList")
    @CrossOrigin
    public List findAll(){
        return userService.findAll();
    }
}
