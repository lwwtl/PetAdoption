package com.pet.demo.controller;

import com.pet.demo.entity.Admin;
import com.pet.demo.entity.Log;
import com.pet.demo.entity.Pet;
import com.pet.demo.entity.User;
import com.pet.demo.service.LogService;
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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/PetTest")
public class PetTestController {
    @Autowired
    private PetService petService;
    @Autowired
    private LogService logService;

    @GetMapping("/pet")
    public String findAll(Model model){
        List<Pet> pets=petService.findAll();
        model.addAttribute("pets",pets);
        return "pet";
    }

    @PostMapping("/save")
    public String save(@RequestParam(value = "petPic") MultipartFile file, HttpServletRequest request, HttpSession session){
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
            if(session.getAttribute("Name")!=null){
                String logs=session.getAttribute("Name")+"进行了宠物添加操作,添加了用户"+request.getParameter("petName");
                Log log=new Log();
                log.setLog_time(new Date());
                log.setLog_detail(logs);
                logService.save(log);
            }
            petService.save(pet);
        }else {
            pet.setPetId(request.getParameter("petId"));
            if(session.getAttribute("Name")!=null){
                String logs=session.getAttribute("Name")+"进行了更新操作,更新了"+request.getParameter("petName");
                Log log=new Log();
                log.setLog_time(new Date());
                log.setLog_detail(logs);
                logService.save(log);
            }
            petService.update(pet);
        }
        return "redirect:/PetTest/pet";
    }


    @GetMapping("/delete")
    public String delete(String petId,HttpSession session){
        if(session.getAttribute("Name")!=null){
            String logs=session.getAttribute("Name")+"进行了删除操作,删除了"+petService.findOne(petId).getPetName();
            Log log=new Log();
            log.setLog_time(new Date());
            log.setLog_detail(logs);
            logService.save(log);
        }
        petService.delete(petId);
        return "redirect:/PetTest/pet";
    }


    @GetMapping("/findByName")
    public String findByName(Model model,@RequestParam(name = "searchName",required = false) String searchName,HttpSession session){
        String name='%'+searchName+'%';
        List<Pet> pets=petService.findByName(name);
        model.addAttribute("pets",pets);
        if(session.getAttribute("Name")!=null){
            String logs=session.getAttribute("Name")+"进行了宠物按名称查询操作，查询了"+searchName;
            Log log=new Log();
            log.setLog_time(new Date());
            log.setLog_detail(logs);
            logService.save(log);
        }
        return "pet";
    }
}
