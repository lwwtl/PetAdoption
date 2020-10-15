package com.pet.demo.service;

import com.pet.demo.entity.SysLog;

import java.util.List;

public interface SysLogService {
    int insertLog(SysLog sysLog);
    List<SysLog> findAll();
//    void updateLog(String msg,String adminAction);
}
