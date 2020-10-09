package com.pet.demo.controller;

import com.pet.demo.entity.Admin;
import com.pet.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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

        model.addAttribute("admins",admins);
        return "admin";

    }

    @PostMapping("/save")
    public String save(HttpServletRequest request,Admin admin){
        admin.setAdminAccount(request.getParameter("adminAccount"));
        admin.setAdminPassword(request.getParameter("adminPassword"));
        admin.setAdminName(request.getParameter("adminName"));
        admin.setAdminAge(request.getParameter("adminAge"));
        admin.setAdminSex(request.getParameter("adminSex"));
        admin.setAdminTelephone(request.getParameter("adminTelephone"));
        admin.setAdminEmail(request.getParameter("adminEmail"));
//        判断添加还是修改操作
        if(StringUtils.isEmpty(admin.getAdminId())){
            adminService.save(admin);
        }else {
            adminService.update(admin);
        }
        return "redirect:/backstage/admin";

    }

    @GetMapping("/findOne")
    public String findOne(){
        Admin admin=adminService.findOne("1");
        System.out.println(admin);
        return "pet/success";
    }


    @GetMapping("/delete")
    public String findByName(String adminId){
        adminService.delete(adminId);
        return "redirect:/backstage/admin";
    }
}
