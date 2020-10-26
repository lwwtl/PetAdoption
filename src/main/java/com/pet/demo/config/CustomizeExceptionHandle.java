package com.pet.demo.config;

import com.pet.demo.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CustomizeExceptionHandle {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model,
                        HttpServletResponse response) {


            if(e instanceof CustomizeException){
                model.addAttribute("message",e.getMessage());
            }
            else{
                model.addAttribute("message","服务器冒烟了，稍后试试！");
            }

            return new ModelAndView("error");

    }
}
