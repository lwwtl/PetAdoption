package com.pet.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pet.demo.entity.User;
import com.pet.demo.service.UserService;
import com.pet.demo.utils.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/front")
public class UserTestController {
    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public String findAll(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "searchName",required = false) String searchName){
        if(StringUtils.isEmpty(searchName)){
            PageHelper.startPage(pageNum,5);
            List<User> userList=userService.findAll();
            PageInfo<User> pageInfo = new PageInfo<>(userList);
            model.addAttribute("users",pageInfo);
            return "user";
        }
        else {
            String name='%'+searchName+'%';
            PageHelper.startPage(pageNum,5);
            List<User> users=userService.findByName(name);
            PageInfo<User> pageInfo = new PageInfo<>(users);
            model.addAttribute("users",pageInfo);
            return "user";
        }
    }

    @PostMapping("/save")
    public String save( User user){
        //        判断添加还是修改操作
        if(StringUtils.isEmpty(user.getUserId())){
                userService.save(user);
        }else {
            userService.update(user);
        }
        return "redirect:/front/user";
    }

//    @GetMapping("/findone")
//    public String findone( Model model){
//        User user=userService.findOne("fe7bdb28-dafb-4ea9-9add-9ba1210e8895");
//        System.out.println(user);
//        return "pet/success";
//    }


    @GetMapping("/findByName")
    public String findByName( Model model, @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(name = "searchName",required = false) String searchName){
        String name="%"+searchName+"%";
        PageHelper.startPage(pageNum,5);
        List<User> users=userService.findByName(name);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        model.addAttribute("users",pageInfo);
        return "user";

    }

    @GetMapping("/delete")
    public String delete( String userId){
        userService.delete(userId);
        return "redirect:/front/user";
    }


}
