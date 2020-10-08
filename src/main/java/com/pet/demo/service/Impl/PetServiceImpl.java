package com.pet.demo.service.Impl;

import com.pet.demo.dao.PetDao;
import com.pet.demo.entity.Pet;
import com.pet.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class PetServiceImpl implements PetService {
    @Autowired
    private PetDao petDao;

    @Override
    public List<Pet> findAll() {
        return petDao.findAll();
    }

    @Override
    public void save(Pet pet) {
        pet.setPetId(UUID.randomUUID().toString());
        petDao.save(pet);
    }

    @Override
    public void delete(String id) {
        petDao.delete(id);
    }

    @Override
    public void update(Pet pet) {
        petDao.update(pet);

    }

    @Override
    public Pet findOne(String id) {
        return petDao.findOne(id);
    }
}
