package com.pet.demo.controller;

import com.pet.demo.entity.User;
import com.pet.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserTestController {
    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public String findAll(Model model){
        List<User> users=userService.findAll();
        for(User user:users){
            System.out.println(user);
        }
        return "success";
    }

    @GetMapping("/save")
    public String save( Model model){
        User user=new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUserAccount("test");
        user.setUserPassword("test");
        user.setUserName("付狗妮");
        user.setUserAge("22");
        user.setUserSex("test");
        user.setUserTelephone("test");
        user.setUserEmail("test");
        user.setUserAddress("test");
        user.setUserState("付狗妮");
        userService.save(user);
        return "success";
    }

    @GetMapping("/findone")
    public String findone( Model model){
        User user=userService.findOne("fe7bdb28-dafb-4ea9-9add-9ba1210e8895");
        System.out.println(user);
        return "success";
    }

    @GetMapping("/update")
    public String update( Model model){
        User user=userService.findOne("fe7bdb28-dafb-4ea9-9add-9ba1210e8895");
        user.setUserAccount("4444444444");
        user.setUserPassword("444444444");
        user.setUserName("4444444");
        user.setUserAge("22");
        user.setUserSex("4444444");
        user.setUserTelephone("44");
        user.setUserEmail("444444444");
        user.setUserAddress("44444444");
        user.setUserState("1111");
        userService.update(user);
        return "success";
    }

    @GetMapping("/findByName")
    public String findByName( Model model){
        List<User> users=userService.findByName("%狗%");
        for(User user:users){
            System.out.println(user);
        }
        return "success";
    }

    @GetMapping("/delete")
    public String delete( Model model){
        userService.delete("fe6f3471-715d-43db-9a33-f5ebda7a3f1b");
        return "success";
    }
}
