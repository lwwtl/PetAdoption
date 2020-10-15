package com.pet.demo.service;

import com.pet.demo.entity.Admin;
import com.pet.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminService {
    List<Admin> findAll();
    void save(Admin admin);
    void delete(String id);
    void update(Admin admin);
    List<Admin> findByName(String id);
    String findName(String id);
    Admin loading(String adminAccount,String adminPassword);
}
