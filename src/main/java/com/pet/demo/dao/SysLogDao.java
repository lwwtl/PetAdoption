package com.pet.demo.dao;

import com.pet.demo.entity.SysLog;
import com.pet.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysLogDao {
    int insertLog(SysLog sysLog);
    List<SysLog> findAll();
//    void updateLog(String msg,String adminAction);
}
