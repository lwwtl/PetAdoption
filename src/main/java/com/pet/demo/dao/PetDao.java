package com.pet.demo.dao;

import com.pet.demo.entity.Pet;
import com.pet.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetDao {
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
