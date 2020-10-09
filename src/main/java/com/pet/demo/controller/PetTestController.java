package com.pet.demo.controller;

import com.pet.demo.entity.Admin;
import com.pet.demo.entity.Pet;
import com.pet.demo.entity.User;
import com.pet.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/PetTest")
public class PetTestController {
    @Autowired
    private PetService petService;

    @GetMapping("/pet")
    public String findAll(Model model){
        List<Pet> pets=petService.findAll();
        model.addAttribute("pets",pets);
        return "pet";
    }

    @PostMapping("/save")
    public String save(HttpServletRequest request,Pet pet){
        if(StringUtils.isEmpty(pet.getPetId())){
            petService.save(pet);
        }else {
            petService.update(pet);
        }
        return "redirect:/PetTest/pet";
    }

//    @GetMapping("/findone")
//    public String findone( Model model){
//        Pet pet=petService.findOne("test2");
//        System.out.println(pet);
//        return "success";
//    }

    @GetMapping("/delete")
    public String delete(String petId){
        petService.delete(petId);
        return "redirect:/PetTest/pet";
    }

//    @GetMapping("update")
//    public String update(){
//        Pet pet=petService.findOne("test2");
//        pet.setPetName("test");
//        pet.setPetBir("test");
//        pet.setPetDetail("test");
//        pet.setPetPic("test");
//        pet.setPetSex("test");
//        pet.setPetState("test");
//        pet.setPetSub("test");
//        pet.setPetType("test");
//        petService.update(pet);
//        return "success";
//    }

    @GetMapping("/findByName")
    public String findByName(Model model,@RequestParam(name = "searchName",required = false) String searchName){
        String name='%'+searchName+'%';
        List<Pet> pets=petService.findByName(name);
        model.addAttribute("pets",pets);
        return "pet";
    }
}
