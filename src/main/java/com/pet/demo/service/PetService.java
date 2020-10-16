package com.pet.demo.service;

import com.pet.demo.entity.Admin;
import com.pet.demo.entity.Pet;

import java.util.List;

public interface PetService {
    List<Pet> findAll();
    List<Pet> findPet(String petState);
    void save(Pet pet);
    void delete(String id);
    void update(Pet pet);
    Pet findOne(String id);
    String findName(String id);
    List<Pet> findByName(String petName);
    List<Pet> findByNameWithState(String petName);
}
