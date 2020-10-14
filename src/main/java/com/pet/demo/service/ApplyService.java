package com.pet.demo.service;

import com.pet.demo.entity.Apply;
import com.pet.demo.entity.User;

import java.util.List;

public interface ApplyService {
    //CURD
    void save(Apply apply);
    void delete(String id);
    void update(Apply apply);
    List<Apply> findAll(String applyState);
    Apply findOne(String id);
    List<Apply> findByName(String applyUserName,String applyState);
}
