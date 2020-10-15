package com.pet.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pet.demo.config.Log;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/PetTest")
public class PetTestController {
    @Autowired
    private PetService petService;

    @GetMapping("/pet")
    public String findAll(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "searchName", required = false) String searchName){
        if(StringUtils.isEmpty(searchName)){
            PageHelper.startPage(pageNum,5);
            List<Pet> pets=petService.findAll();
            PageInfo<Pet> pageInfo = new PageInfo<>(pets);
            model.addAttribute("pets",pageInfo);
            return "pet";
        }
        else {
            String name='%'+searchName+'%';
            PageHelper.startPage(pageNum,5);
            List<Pet> pets=petService.findByName(name);
            PageInfo<Pet> pageInfo = new PageInfo<>(pets);
            model.addAttribute("pets",pageInfo);
            return "pet";
        }

    }

    @PostMapping("/save")
    public String savePet(@RequestParam(value = "petPic") MultipartFile file,HttpServletRequest request){
//        if (file.isEmpty()) {
//            System.out.println("文件为空空");
//        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "E://upload//"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/upload/" + fileName;
//        System.out.println(filename);
        Pet pet = new Pet();
        pet.setPetName(request.getParameter("petName"));
        pet.setPetDetail(request.getParameter("petDetail"));
        pet.setPetSex(request.getParameter("petSex"));
        pet.setPetState(request.getParameter("petState"));
        pet.setPetSub(request.getParameter("petSub"));
        pet.setPetType(request.getParameter("petType"));
        pet.setPetBir(request.getParameter("petBir"));
        pet.setPetPic(filename);
        //判断进行添加还是修改操作

        if(StringUtils.isEmpty(request.getParameter("petId"))){
            petService.save(pet);
        }else {
            pet.setPetId(request.getParameter("petId"));
            petService.update(pet);
        }
        return "redirect:/PetTest/pet";
    }


    @GetMapping("/delete")
    public String deletePet(String petId){
        petService.delete(petId);
        return "redirect:/PetTest/pet";
    }


    @GetMapping("/findByName")
    public String findByName(Model model,@RequestParam(name = "searchName",required = false) String searchName){
        String name='%'+searchName+'%';
        List<Pet> pets=petService.findByName(name);
        model.addAttribute("pets",pets);
        return "pet";
    }
}
