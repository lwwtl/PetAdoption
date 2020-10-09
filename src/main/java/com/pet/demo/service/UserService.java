package com.pet.demo.service;

import com.pet.demo.entity.User;

import java.util.List;

public interface UserService {
    void save(User user);
    void delete(String id);
    void update(User user);
    List<User> findAll();
//    User findOne(String id);
    List<User> findByName(String userName);
}
