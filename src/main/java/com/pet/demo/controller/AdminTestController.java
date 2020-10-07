package com.pet.demo.controller;

import com.pet.demo.entity.Admin;
import com.pet.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminTestController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/findAll")
    public String findAll(){
        List<Admin> admins=adminService.findAll();
        for(Admin admin:admins){
            System.out.println(admin);
        }
        return "pet/success";
    }

    @GetMapping("/save")
    public String save(){
        Admin admin=new Admin();
        admin.setAdminId(UUID.randomUUID().toString());
        admin.setAdminAccount("777");
        admin.setAdminPassword("777");
        admin.setAdminName("777");
        admin.setAdminAge(777);
        admin.setAdminSex("777");
        admin.setAdminTelephone("777");
        admin.setAdminEmail("777");
        adminService.save(admin);
        return "pet/success";
    }

    @GetMapping("/findOne")
    public String findOne(){
        Admin admin=adminService.findOne("1");
        System.out.println(admin);
        return "pet/success";
    }

    @GetMapping("/update")
    public String update(){
        Admin admin=adminService.findOne("2");
        admin.setAdminAccount("888");
        admin.setAdminPassword("888");
        admin.setAdminName("888");
        admin.setAdminAge(888);
        admin.setAdminSex("888");
        admin.setAdminTelephone("888");
        admin.setAdminEmail("888");
        adminService.update(admin);
        return "pet/success";
    }

    @GetMapping("/delete")
    public String findByName(){
        adminService.delete("1");
        return "pet/success";
    }
}
