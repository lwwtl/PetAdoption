package com.pet.demo.dao;

import com.pet.demo.entity.Pet;

import java.util.List;

public interface PetDao {
    List<Pet> findAll();
    void save(Pet pet);
    void delete(String id);
    void update(Pet pet);
    Pet findOne(String id);
}
