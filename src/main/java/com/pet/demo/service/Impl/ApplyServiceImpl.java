package com.pet.demo.service.Impl;

import com.pet.demo.dao.ApplyDao;
import com.pet.demo.entity.Apply;
import com.pet.demo.entity.User;
import com.pet.demo.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ApplyDao applyDao;

    @Override
    public void save(Apply apply) {
        applyDao.save(apply);
    }

    @Override
    public void delete(String id) {
        applyDao.delete(id);
    }

    @Override
    public void update(Apply apply) {
        applyDao.update(apply);
    }

    @Override
    public List<Apply> findAll() {
        return applyDao.findAll();
    }

    @Override
    public Apply findOne(String id) {
        return applyDao.findOne(id);
    }

}