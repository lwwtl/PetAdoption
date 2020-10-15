package com.pet.demo.service;

import com.pet.demo.entity.UserLog;

import java.util.List;

public interface UserLogService {
    int insertLog(UserLog userLog);
    List<UserLog> findAll();
}
