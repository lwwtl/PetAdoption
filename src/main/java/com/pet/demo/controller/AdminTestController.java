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
//保存和修改用户
    @PostMapping("/save")
    public String save(Admin admin){

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

//    @GetMapping("/update")
//    public String update(){
//        Admin admin=adminService.findOne("2");
//        admin.setAdminAccount("888");
//        admin.setAdminPassword("888");
//        admin.setAdminName("888");
//        admin.setAdminAge("888");
//        admin.setAdminSex("888");
//        admin.setAdminTelephone("888");
//        admin.setAdminEmail("888");
//        adminService.update(admin);
//        return "pet/success";
//    }

//根据id删除用户
    @GetMapping("/delete")
    public String findByName(String adminId){
        adminService.delete(adminId);
        return "redirect:/backstage/admin";
    }
}
