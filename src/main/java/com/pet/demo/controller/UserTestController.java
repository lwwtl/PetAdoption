package com.pet.demo.controller;

import com.pet.demo.entity.User;
import com.pet.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/front")
public class UserTestController {
    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public String findAll(Model model){
        List<User> users=userService.findAll();
        model.addAttribute("users",users);
        return "user";
    }

    @GetMapping("/save")
    public String save( User user){
        //        判断添加还是修改操作
        if(StringUtils.isEmpty(user.getUserId())){
            userService.save(user);
        }else {
            userService.update(user);
        }
        return "redirect:/front/user";
    }

    @GetMapping("/findone")
    public String findone( Model model){
        User user=userService.findOne("fe7bdb28-dafb-4ea9-9add-9ba1210e8895");
        System.out.println(user);
        return "pet/success";
    }

//    @GetMapping("/update")
//    public String update( Model model){
//        User user=userService.findOne("fe7bdb28-dafb-4ea9-9add-9ba1210e8895");
//        user.setUserAccount("4444444444");
//        user.setUserPassword("444444444");
//        user.setUserName("4444444");
//        user.setUserAge("22");
//        user.setUserSex("4444444");
//        user.setUserTelephone("44");
//        user.setUserEmail("444444444");
//        user.setUserAddress("44444444");
//        user.setUserState("1111");
//        userService.update(user);
//        return "pet/success";
//    }

    @GetMapping("/findByName")
    public String findByName( Model model){
        List<User> users=userService.findByName("%狗%");
        for(User user:users){
            System.out.println(user);
        }
        return "pet/success";
    }

    @GetMapping("/delete")
    public String delete( String userId){
        userService.delete(userId);
        return "redirect:/front/user";
    }
}
