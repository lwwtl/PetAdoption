package com.pet.demo.controller;

import com.pet.demo.entity.*;
import com.pet.demo.service.ApplyService;
import com.pet.demo.service.PetService;
import com.pet.demo.service.SysLogService;
import com.pet.demo.service.UserService;
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

    @GetMapping("/index")
    public String index(){

        return "index";
    }
    @GetMapping("/manage")
    public String manage(Model model){
        List<SysLog> sysLogs=sysLogService.findAll();
        model.addAttribute("logs",sysLogs);
        return "manage";
    }
    @GetMapping("/navigation")
    public String nav(){

        return "navigation";
    }
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
    @GetMapping("/adoption/{id}")
    public String petAdoption(@PathVariable(name = "id")String id,Model model)
    {
        Pet pet=petService.findOne(id);
        model.addAttribute("pet",pet);
        return "adoption";

    }

    @GetMapping("/show")
    public String showPet(Model model){
        List<Pet> pets=petService.findPet("未领养");
        model.addAttribute("pets",pets);
        return "show";
    }
    @GetMapping("/search")
    public String search(Model model,@RequestParam(name = "searchName",required = false) String key){
        String name='%'+key+'%';
        List<Pet> pets=petService.findByName(name);
        model.addAttribute("pets",pets);
        return "show";
    }



}
