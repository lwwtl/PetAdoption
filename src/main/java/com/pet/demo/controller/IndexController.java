package com.pet.demo.controller;

import com.pet.demo.entity.User;
import com.pet.demo.service.UserService;
import com.pet.demo.utils.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class IndexController {


    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/manage")
    public String manage(){
        return "manage";
    }
    @GetMapping("/navigation")
    public String nav(){
        return "navigation";
    }

}
