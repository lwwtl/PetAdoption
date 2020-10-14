package com.pet.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pet.demo.entity.Log;
import com.pet.demo.entity.User;
import com.pet.demo.service.LogService;
import com.pet.demo.service.UserService;
import com.pet.demo.utils.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.naming.Name;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/front")
public class UserTestController {
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    @GetMapping("/user")
    public String findAll(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "searchName",required = false) String searchName,HttpSession session){
        if(StringUtils.isEmpty(searchName)){
            PageHelper.startPage(pageNum,5);
            List<User> userList=userService.findAll();
            PageInfo<User> pageInfo = new PageInfo<>(userList);
            model.addAttribute("users",pageInfo);
            return "user";
        }
        else {
            if(session.getAttribute("Name")!=null){
                String logs=session.getAttribute("Name")+"进行了用户按名称查询操作，查询了"+searchName;
                Log log=new Log();
                log.setLog_time(new Date());
                log.setLog_detail(logs);
                logService.save(log);
            }
            String name='%'+searchName+'%';
            PageHelper.startPage(pageNum,5);
            List<User> users=userService.findByName(name);
            PageInfo<User> pageInfo = new PageInfo<>(users);
            model.addAttribute("users",pageInfo);
            return "user";
        }
    }

    @PostMapping("/save")
    public String save( User user,HttpSession session){
        //        判断添加还是修改操作
        if(StringUtils.isEmpty(user.getUserId())){
            if(session.getAttribute("Name")!=null){
                String logs=session.getAttribute("Name")+"进行了用户添加操作,添加了用户"+user.getUserName();
                Log log=new Log();
                log.setLog_time(new Date());
                log.setLog_detail(logs);
                logService.save(log);
            }
                userService.save(user);
        }else {
            if(session.getAttribute("Name")!=null){
                String logs=session.getAttribute("Name")+"进行了用户更新操作,更新了"+user.getUserName();
                Log log=new Log();
                log.setLog_time(new Date());
                log.setLog_detail(logs);
                logService.save(log);
            }
            userService.update(user);
        }
        return "redirect:/front/user";
    }
    @PostMapping("/infoUpdate")
    public String infoUpdate(User user, HttpSession session, RedirectAttributes attributes){
        userService.update(user);
        session.setAttribute("user",user);
        attributes.addFlashAttribute("msg","保存成功");
        return "redirect:/info";
    }

//    @GetMapping("/findone")
//    public String findone( Model model){
//        User user=userService.findOne("fe7bdb28-dafb-4ea9-9add-9ba1210e8895");
//        System.out.println(user);
//        return "pet/success";
//    }


    @GetMapping("/findByName")
    public String findByName( Model model, @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(name = "searchName",required = false) String searchName){
        String name="%"+searchName+"%";
        PageHelper.startPage(pageNum,5);
        List<User> users=userService.findByName(name);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        model.addAttribute("users",pageInfo);
        return "user";

    }

    @GetMapping("/delete")
    public String delete( String userId,HttpSession session){
        if(session.getAttribute("Name")!=null){
            String logs=session.getAttribute("Name")+"进行了用户删除操作,删除了"+userService.findOne(userId).getUserName();
            Log log=new Log();
            log.setLog_time(new Date());
            log.setLog_detail(logs);
            logService.save(log);
        }
        userService.delete(userId);
        return "redirect:/front/user";
    }


}
