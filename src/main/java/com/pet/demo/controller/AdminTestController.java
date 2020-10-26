package com.pet.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pet.demo.entity.Admin;
import com.pet.demo.entity.Apply;
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

    //后台管理员模块的搜索和展示
    @GetMapping("/admin")
    public String findAll(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(name = "searchName",required = false) String searchName){
        //required：是否包含该参数，默认为true，表示该请求路径中必须包含该参数，如果不包含就报错
        //defaultValue:默认参数
        model.addAttribute("url", "/backstage/admin");
        if (StringUtils.isEmpty(searchName)) {
            PageHelper.startPage(pageNum, 5);
            List<Admin> admins=adminService.findAll();
//            将所有结果放在admins，再把admins放入pageInfo
            PageInfo<Admin> pageInfo = new PageInfo<>(admins);
            model.addAttribute("pagelist", pageInfo);
            return "admin";
        } else {
//            模糊查找
            String name = '%' + searchName + '%';
            PageHelper.startPage(pageNum, 5);
            List<Admin> admins=adminService.findByName(name);
            PageInfo<Admin> pageInfo = new PageInfo<>(admins);
            model.addAttribute("pagelist", pageInfo);
            return "admin";
        }
    }

//管理员的增加和修改
//    待优化---修改的回显功能可以优化为查出对应id放入model回显，而不是通过前端js回显、
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

//删除管理员
    @GetMapping("/delete")
    public String findByName(String adminId){
        adminService.delete(adminId);
        return "redirect:/backstage/admin";
    }
}
