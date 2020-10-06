package com.pet.demo.service;

import com.pet.demo.entity.Admin;

import java.util.List;

public interface AdminService {
    void save(Admin admin);
    void delete(String id);
    void update(Admin admin);
    List<Admin> findAll();
    Admin findOne(String id);
    //根据名称模糊查询
    List<Admin> findByName(String adminname);
}
