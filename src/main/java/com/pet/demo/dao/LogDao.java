package com.pet.demo.dao;

import com.pet.demo.entity.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogDao {
    void save(Log log);
    List<Log> findALL();
    List<Log> findFive();
}
