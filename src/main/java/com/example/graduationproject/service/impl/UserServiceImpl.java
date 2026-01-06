package com.example.graduationproject.service.impl;

import com.example.graduationproject.mapper.UserMapper;
import com.example.graduationproject.model.User;
import com.example.graduationproject.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
