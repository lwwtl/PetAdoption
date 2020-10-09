package com.pet.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pet.demo.entity.Admin;
import com.pet.demo.entity.User;
import com.pet.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.events.Event;

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
//        PageHelper.startPage(pageNum,5);
        List<Admin> admins=adminService.findAll();
//        PageInfo<Admin> pageInfo = new PageInfo<>(admins);
//        model.addAttribute("admins",pageInfo);
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


    @GetMapping("/findByName")
    public String findByName(Model model,@RequestParam(name = "searchName",required = false) String searchName){
        String name='%'+searchName+'%';
        List<Admin> admins=adminService.findByName(name);
        model.addAttribute("admins",admins);
        return "admin";
    }
    @GetMapping("/delete")
    public String findByName(String adminId){
        adminService.delete(adminId);
        return "redirect:/backstage/admin";
    }
}
