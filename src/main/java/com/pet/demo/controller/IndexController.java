package com.pet.demo.controller;

import com.pet.demo.entity.*;
import com.pet.demo.exception.CustomizeErrorCode;
import com.pet.demo.exception.CustomizeException;
import com.pet.demo.service.*;
import com.pet.demo.utils.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private PetService petService;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private UserLogService userLogService;
//首页
    @GetMapping("/index")
    public String index(){

        return "index";
    }
//    后台管理页面
    @GetMapping("/manage")
    public String manage(Model model){
        List<SysLog> sysLogs=sysLogService.findAll();
        List<UserLog> userLogs=userLogService.findAll();
        model.addAttribute("logs",sysLogs);
        model.addAttribute("userLogs",userLogs);
        return "manage";
    }
//    导航条
    @GetMapping("/navigation")
    public String nav(){
        return "navigation";
    }
//    个人信息页面
    @GetMapping("/info")
    public String userInfo(){
        return "info";
    }
    @PostMapping("/getList")
    public String getList(String applyUserId,Model model){
        List<Apply> list = applyService.findUser(applyUserId);
        model.addAttribute("list",list);
        return "info::tb1";
    }
//    宠物信息展示
    @GetMapping("/adoption/{id}")
    public String petAdoption(@PathVariable(name = "id")String id,Model model)
    {
        Pet pet=petService.findOne(id);
        if(pet==null){
            throw new CustomizeException(CustomizeErrorCode.PET_NOT_FOUND);
        }
        model.addAttribute("pet",pet);
        return "adoption";

    }
//宠物列表
    @GetMapping("/show")
    public String showPet(Model model){
        List<Pet> pets=petService.findPet("未领养");
        model.addAttribute("pets",pets);
        return "show";
    }
//    宠物的搜索
    @GetMapping("/search")
    public String search(Model model,@RequestParam(name = "searchName",required = false) String key){
        String name='%'+key+'%';
        List<Pet> pets=petService.findByNameWithState(name);
        model.addAttribute("pets",pets);
        return "show";
    }



}
