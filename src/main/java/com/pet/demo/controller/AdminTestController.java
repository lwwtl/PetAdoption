package com.pet.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pet.demo.entity.Admin;
import com.pet.demo.entity.Log;
import com.pet.demo.entity.User;
import com.pet.demo.service.AdminService;
import com.pet.demo.service.LogService;
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
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/backstage")
public class AdminTestController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private LogService logService;


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
    public String save(HttpServletRequest request, Admin admin, HttpSession session){
        admin.setAdminAccount(request.getParameter("adminAccount"));
        admin.setAdminPassword(request.getParameter("adminPassword"));
        admin.setAdminName(request.getParameter("adminName"));
        admin.setAdminAge(request.getParameter("adminAge"));
        admin.setAdminSex(request.getParameter("adminSex"));
        admin.setAdminTelephone(request.getParameter("adminTelephone"));
        admin.setAdminEmail(request.getParameter("adminEmail"));
//        判断添加还是修改操作
        if(StringUtils.isEmpty(admin.getAdminId())){
            if(session.getAttribute("Name")!=null){
                String logs=session.getAttribute("Name")+"进行了管理员添加操作,添加了管理员"+request.getParameter("adminName");
                Log log=new Log();
                log.setLog_time(new Date());
                log.setLog_detail(logs);
                logService.save(log);
            }
            adminService.save(admin);
        }else {
            if(session.getAttribute("Name")!=null){
                String logs=session.getAttribute("Name")+"进行了管理员更新操作,更新了"+request.getParameter("adminName");
                Log log=new Log();
                log.setLog_time(new Date());
                log.setLog_detail(logs);
                logService.save(log);
            }
            adminService.update(admin);
        }
        return "redirect:/backstage/admin";

    }


    @GetMapping("/findByName")
    public String findByName(Model model,@RequestParam(name = "searchName",required = false) String searchName,HttpSession session){
        String name='%'+searchName+'%';
        List<Admin> admins=adminService.findByName(name);
        if(session.getAttribute("Name")!=null){
            String logs=session.getAttribute("Name")+"进行了管理员按名称查询操作，查询了"+searchName;
            Log log=new Log();
            log.setLog_time(new Date());
            log.setLog_detail(logs);
            logService.save(log);
        }
        model.addAttribute("admins",admins);
        return "admin";
    }
    @GetMapping("/delete")
    public String findByName(String adminId,HttpSession session){
        if(session.getAttribute("Name")!=null){
            String logs=session.getAttribute("Name")+"进行了管理员删除操作,删除了"+adminService.findOne(adminId).getAdminName();
            Log log=new Log();
            log.setLog_time(new Date());
            log.setLog_detail(logs);
            logService.save(log);
        }
        adminService.delete(adminId);
        return "redirect:/backstage/admin";
    }
}
