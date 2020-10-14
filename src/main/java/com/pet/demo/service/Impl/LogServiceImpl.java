package com.pet.demo.service.Impl;

import com.pet.demo.dao.LogDao;
import com.pet.demo.entity.Log;
import com.pet.demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void save(Log log) {
        logDao.save(log);
    }

    @Override
    public List<Log> findALL() {
        return logDao.findALL();
    }

    @Override
    public List<Log> findFive() {
        return logDao.findFive();
    }
}
