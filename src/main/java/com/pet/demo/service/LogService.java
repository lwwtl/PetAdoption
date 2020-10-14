package com.pet.demo.service;

import com.pet.demo.entity.Log;

import java.util.List;

public interface LogService {
    void save(Log log);
    List<Log> findALL();
    List<Log> findFive();
}
