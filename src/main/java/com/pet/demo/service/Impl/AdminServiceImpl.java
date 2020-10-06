package com.pet.demo.service.Impl;

import com.pet.demo.dao.AdminDao;
import com.pet.demo.entity.Admin;
import com.pet.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDAO;

    @Override
    public List<Admin> findAll() {
        return adminDAO.findAll();
    }

    @Override
    public void save(Admin admin) {
        adminDAO.save(admin);
    }

    @Override
    public void delete(String id) {
        adminDAO.delete(id);
    }

    @Override
    public void update(Admin admin) {
        adminDAO.update(admin);
    }

    @Override
    public Admin findOne(String id) {
        return adminDAO.findOne(id);
    }
}
