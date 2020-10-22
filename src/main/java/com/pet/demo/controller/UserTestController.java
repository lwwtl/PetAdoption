package com.pet.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pet.demo.config.Log;
import com.pet.demo.entity.User;
import com.pet.demo.service.UserService;
import com.pet.demo.utils.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("url", "/front/user");
        if(StringUtils.isEmpty(searchName)){
            PageHelper.startPage(pageNum,5);
            List<User> userList=userService.findAll();
            PageInfo<User> pageInfo = new PageInfo<>(userList);
            model.addAttribute("pagelist",pageInfo);
            return "user";
        }
        else {
            String name='%'+searchName+'%';
            PageHelper.startPage(pageNum,5);
            List<User> users=userService.findByName(name);
            PageInfo<User> pageInfo = new PageInfo<>(users);
            model.addAttribute("pagelist",pageInfo);
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
//    RedirectAttributes 重定向之后仍能携带参数
    @PostMapping("/infoUpdate")
    public String infoUpdate(User user, HttpSession session, RedirectAttributes attributes){
        userService.update(user);
        session.setAttribute("user",user);
        attributes.addFlashAttribute("message","保存成功");
        return "redirect:/info";
    }
//   ajax的密码修改功能
    @PostMapping("/updatePsd")
    @ResponseBody
    public String updatePsd( String oldPassword,String newPassword,String psdId
                             )  {
        User user = userService.findOne(psdId);
        String msg = "0";
        if(oldPassword.isEmpty()){
            return msg;
        }
        if(oldPassword.equals(user.getUserPassword())){
            user.setUserPassword(newPassword);
            userService.update(user);
           msg="修改成功";
        }else {
            msg="修改失败";
        }
        return msg;
    }

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
