package com.pet.demo.controller;

import com.pet.demo.entity.Pet;
import com.pet.demo.entity.User;
import com.pet.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("PetTest")
public class PetTestController {
    @Autowired
    private PetService petService;

    @GetMapping("findAll")
    public String findAll(){
        List<Pet> pets=petService.findAll();
        for(Pet pet:pets){
            System.out.println(pets);
        }
        return "success";
    }

    @GetMapping("save")
    public String save(){
        Pet pet=new Pet();
        pet.setPetId(UUID.randomUUID().toString());
        pet.setPetName("test");
        pet.setPetBir("test");
        pet.setPetDetail("test");
        pet.setPetPic("test");
        pet.setPetSex("test");
        pet.setPetState("test");
        pet.setPetSub("test");
        pet.setPetType("test");
        petService.save(pet);
        return "success";
    }

    @GetMapping("/findone")
    public String findone( Model model){
        Pet pet=petService.findOne("test2");
        System.out.println(pet);
        return "success";
    }

    @GetMapping("delete")
    public String delete(String id){
        petService.delete(id);
        return "success";
    }

    @GetMapping("update")
    public String update(){
        Pet pet=petService.findOne("test2");
        pet.setPetName("test");
        pet.setPetBir("test");
        pet.setPetDetail("test");
        pet.setPetPic("test");
        pet.setPetSex("test");
        pet.setPetState("test");
        pet.setPetSub("test");
        pet.setPetType("test");
        petService.update(pet);
        return "success";
    }

    @GetMapping("/findByName")
    public String findByName(Model model){
        List<Pet> pets=petService.findByName("%t%");
        model.addAttribute("pets",pets);
        return "success";
    }
}
