package com.zr0726.news.service.impl;

import com.zr0726.news.dao.UserRepository;
import com.zr0726.news.po.User;
import com.zr0726.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;


    @Override
    public User checkUsers(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }
}
