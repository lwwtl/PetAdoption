package com.pet.demo.controller;

import com.pet.demo.entity.Admin;
import com.pet.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/backstage")
public class AdminTestController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/admin")
    public String findAll(Model model){
        List<Admin> admins=adminService.findAll();
//        for(Admin admin:admins){
//            System.out.println(admin);
//        }
        model.addAttribute("admins",admins);
        return "admin";
    }

    @PostMapping("/save")
    public String save(HttpServletRequest request){
        Admin admin=new Admin();
        admin.setAdminId(UUID.randomUUID().toString());
        admin.setAdminAccount(request.getParameter("adminAccount"));
        admin.setAdminPassword(request.getParameter("adminPassword"));
        admin.setAdminName(request.getParameter("adminName"));
        admin.setAdminAge(request.getParameter("adminAge"));
        admin.setAdminSex(request.getParameter("adminSex"));
        admin.setAdminTelephone(request.getParameter("adminTelephone"));
        admin.setAdminEmail(request.getParameter("adminEmail"));
        adminService.save(admin);
        return "redirect:/backstage/admin";
    }

    @GetMapping("/findOne")
    public String findOne(){
        Admin admin=adminService.findOne("1");
        System.out.println(admin);
        return "success";
    }

    @GetMapping("/update")
    public String update(){
        Admin admin=adminService.findOne("2");
        admin.setAdminAccount("888");
        admin.setAdminPassword("888");
        admin.setAdminName("888");
        admin.setAdminAge("888");
        admin.setAdminSex("888");
        admin.setAdminTelephone("888");
        admin.setAdminEmail("888");
        adminService.update(admin);
        return "success";
    }

    @GetMapping("/delete")
    public String findByName(){
        adminService.delete("1");
        return "success";
    }
}
