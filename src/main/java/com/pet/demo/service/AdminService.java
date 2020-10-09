package com.pet.demo.service;

import com.pet.demo.entity.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> findAll();
    void save(Admin admin);
    void delete(String id);
    void update(Admin admin);
    List<Admin> findByName(String id);
}
