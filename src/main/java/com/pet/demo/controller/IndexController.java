package com.pet.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pet.demo.entity.Apply;
import com.pet.demo.entity.Log;
import com.pet.demo.entity.Pet;
import com.pet.demo.entity.User;
import com.pet.demo.service.LogService;
import com.pet.demo.service.PetService;
import com.pet.demo.service.UserService;
import com.pet.demo.utils.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private PetService petService;
    @Autowired
    private LogService logService;

    @GetMapping("/index")
    public String index(){

        return "index";
    }
    @GetMapping("/manage")
    public String manage(Model model, @RequestParam(defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum, 5);
        List<Log> logs = logService.findALL();
        PageInfo<Log> pageInfo = new PageInfo<>(logs);
        model.addAttribute("logs", pageInfo);
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
    @GetMapping("/adoption/{id}")
    public String petAdoption(@PathVariable(name = "id")String id,Model model)
    {
        Pet pet=petService.findOne(id);
        model.addAttribute("pet",pet);
        return "adoption";

    }

    @GetMapping("/show")
    public String showPet(Model model){
        List<Pet> pets=petService.findAll();
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

    @GetMapping("/more")
    public String findAll(Model model, @RequestParam(defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum, 10);
        List<Log> logs = logService.findALL();
        PageInfo<Log> pageInfo = new PageInfo<>(logs);
        model.addAttribute("logs", pageInfo);
        return "manage";
        }

}
