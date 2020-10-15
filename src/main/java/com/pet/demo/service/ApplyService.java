package com.pet.demo.service;

import com.pet.demo.entity.Apply;
import com.pet.demo.entity.User;

import java.util.List;

public interface ApplyService {
    //CURD
    void save(Apply apply);
    void delete(String id);
    void update(Apply apply);
    void modify(String applyPetId,String applyState);
    List<Apply> findAll(String applyState);
    List<Apply> findUser(String applyUserId);
    Apply findOne(String id);
    String findApply(String applyId);
    List<Apply> findByName(String applyUserName,String applyState);
}
