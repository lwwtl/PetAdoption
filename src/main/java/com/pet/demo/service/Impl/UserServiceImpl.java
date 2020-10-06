package com.pet.demo.service.Impl;

import com.pet.demo.dao.UserDao;
import com.pet.demo.entity.User;
import com.pet.demo.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findOne(String id) {
        return userDao.findOne(id);
    }

    @Override
    public List<User> findByName(String userName) {
        return userDao.findByName(userName);
    }


}
