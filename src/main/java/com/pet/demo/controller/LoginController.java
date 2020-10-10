package com.pet.demo.controller;

import com.pet.demo.entity.Admin;
import com.pet.demo.entity.User;
import com.pet.demo.service.AdminService;
import com.pet.demo.service.UserService;
import com.pet.demo.utils.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(String code, HttpSession session,
                            @RequestParam("userAccount") String Account,
                            @RequestParam("userPassword") String Password,
                            @RequestParam("role") String role,
                            Model model){
        String sessionCode = (String) session.getAttribute("code");
        System.out.println(role);
        if(sessionCode.equalsIgnoreCase(code)){
//            if(role=="管理员"){
//                Admin login = adminService.login(Account,Password);
//            }
//            else{
                User login = userService.login(Account,Password);
                if(login!=null){
                    session.setAttribute("userName",login.getUserName());
                    session.setAttribute("userId",login.getUserId());
                    return "redirect:/index";
                }else {
                    model.addAttribute("error","用户名或密码错误，请检查后重试");
                    return "/login";
//                }
            }


        }else{
            model.addAttribute("error","验证码错误，请重试");
            return "/login";
        }

    }

    @GetMapping("logout")
    private String logout(HttpSession session){
        session.removeAttribute("userName");
        return "redirect:/index";
    }

    @GetMapping("/code")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
//    生成验证码
        String securityCode = ValidateImageCodeUtils.getSecurityCode();
        BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);
//        存入session作用域
        session.setAttribute("code",securityCode);
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(image,"png",os);
    }

}
