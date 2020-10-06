package com.pet.demo.service.Impl;

import com.pet.demo.dao.AdminDao;
import com.pet.demo.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminDao {

    @Autowired
    private AdminDao adminDao;

    @Override
    public void save(Admin admin) {
        adminDao.save(admin);
    }

    @Override
    public void delete(String id) {
        adminDao.delete(id);
    }

    @Override
    public void update(Admin admin) {
        adminDao.update(admin);
    }

    @Override
    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    @Override
    public Admin findOne(String id) {
        return adminDao.findOne(id);
    }

    @Override
    public List<Admin> findByName(String adminname) {
        return adminDao.findByName(adminname);
    }
}
