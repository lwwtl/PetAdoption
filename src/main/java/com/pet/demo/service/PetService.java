package com.pet.demo.service;

import com.pet.demo.entity.Admin;
import com.pet.demo.entity.Pet;

import java.util.List;

public interface PetService {
    List<Pet> findAll();
    void save(Pet pet);
    void delete(String id);
    void update(Pet pet);
    Pet findOne(String id);
    List<Pet> findByName(String petName);
}
