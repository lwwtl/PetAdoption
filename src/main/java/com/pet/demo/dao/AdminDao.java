package com.pet.demo.dao;

import com.pet.demo.entity.Admin;
import com.pet.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao {
    //CURD
    void save(Admin admin);
    void delete(String id);
    void update(Admin admin);
    List<Admin> findAll();
    Admin findOne(String id);
    //根据名称模糊查询
    List<Admin> findByName(String adminname);
}
