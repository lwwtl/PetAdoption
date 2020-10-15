package com.pet.demo.service.Impl;

import com.pet.demo.dao.UserLogDao;
import com.pet.demo.entity.UserLog;
import com.pet.demo.service.UserLogService;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogServiceImpl implements UserLogService {
    @Autowired
    private UserLogDao userLogDao;

    @Override
    public int insertLog(UserLog userLog) {
        return userLogDao.insertLog(userLog);
    }

    @Override
    public List<UserLog> findAll() {
        return userLogDao.findAll();
    }
}
