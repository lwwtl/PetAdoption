package com.pet.demo.service.Impl;

import com.pet.demo.dao.ApplyDao;
import com.pet.demo.entity.Apply;
import com.pet.demo.entity.User;
import com.pet.demo.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ApplyDao applyDao;

    @Override
    public void save(Apply apply) {
        apply.setApplyId(UUID.randomUUID().toString());
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
    public void modify(String applyPetId, String applyState) {
        applyDao.modify(applyPetId,applyState);
    }

    @Override
    public List<Apply> findAll(String applyState) {
        return applyDao.findAll(applyState);
    }

    @Override
    public List<Apply> findUser(String applyUserId) {
        return applyDao.findUser(applyUserId);
    }


    @Override
    public Apply findOne(String id) {
        return applyDao.findOne(id);
    }

    @Override
    public String findApply(String applyId) {
        return applyDao.findApply(applyId);
    }

    @Override
    public List<Apply> findByName(String applyUserName,String applyState) {
        return applyDao.findByName(applyUserName,applyState);
    }

}
