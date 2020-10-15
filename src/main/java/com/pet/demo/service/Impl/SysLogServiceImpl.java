package com.pet.demo.service.Impl;

import com.pet.demo.dao.SysLogDao;
import com.pet.demo.entity.SysLog;
import com.pet.demo.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public int insertLog(SysLog sysLog) {
        return sysLogDao.insertLog(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }

//    @Override
//    public void updateLog(String msg,String adminAction ) {
//        sysLogDao.updateLog(msg, adminAction);
//    }
}
