package com.pet.demo.dao;

import com.pet.demo.entity.SysLog;
import com.pet.demo.entity.UserLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLogDao {
    int insertLog(UserLog userLog);
    List<UserLog> findAll();
}
