package com.pet.demo.controller;

import com.pet.demo.dao.AdminDao;
import com.pet.demo.entity.Admin;
import com.pet.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminTestController {

    @Autowired
    private AdminService adminService;

//    @GetMapping("/findAll")
//    public String findAll( Model model){
//        List<Admin> admins=adminService.findAll();
//        for(Admin admin:admins){
//            System.out.println(admin);
//        }
//        return "pet/success";
//    }

    @GetMapping("/save")
    public String save(){
        Admin admin=new Admin();
        admin.setAdminId(UUID.randomUUID().toString());
        admin.setAdminAccount("test");
        admin.setAdminPassword("test");
        admin.setAdminName("test");
        admin.setAdminAge(1);
        admin.setAdminSex("test");
        admin.setAdminTelephone("test");
        admin.setAdminEmail("test");
        adminService.save(admin);
        return "pet/success";
    }

//    @GetMapping("/findone")
//    public String findone( Model model){
//        Admin admin=adminService.findOne();
//        System.out.println(admin);
//        return "pet/success";
//    }

//    @GetMapping("/update")
//    public String update( Model model){
//        Admin admin=adminService.findOne("fe7bdb28-dafb-4ea9-9add-9ba1210e8895");
//        admin.setAdminAccount("test");
//        admin.setAdminPassword("test");
//        admin.setAdminName("test");
//        admin.setAdminAge(1);
//        admin.setAdminSex("test");
//        admin.setAdminTelephone("test");
//        admin.setAdminEmail("test");
//        adminService.update(admin);
//        return "pet/success";
//    }

//    @GetMapping("/findByName")
//    public String findByName( Model model){
//        List<Admin> admins=adminService.findByName("%t%");
//        for(Admin admin:admins){
//            System.out.println(admin);
//        }
//        return "pet/success";
//    }
//
//    @GetMapping("/delete")
//    public String delete( Model model){
//        adminService.delete("fe6f3471-715d-43db-9a33-f5ebda7a3f1b");
//        return "pet/success";
//    }
}
