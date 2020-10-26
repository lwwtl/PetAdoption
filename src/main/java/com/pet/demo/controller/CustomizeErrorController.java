package com.pet.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomizeErrorController implements ErrorController {
    //复写error控制器
    @Override
    public String getErrorPath() {
        return "error";
    }
//通用错误处理
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request,
                                  Model model){
        HttpStatus status=getStatus(request);

        if (!status.is4xxClientError()) {
        } else {
            model.addAttribute("message","请求错误，换个姿势");
        }
        if(status.is5xxServerError()){
            model.addAttribute("message","服务异常，请稍后再试");
        }
        return  new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
       Integer statusCode =  (Integer)request.getAttribute("javax.servlet.error.status_code");
       if(statusCode == null){
           return HttpStatus.INTERNAL_SERVER_ERROR;
       }
       try{
           return HttpStatus.valueOf(statusCode);
       }catch (Exception e){
           return HttpStatus.INTERNAL_SERVER_ERROR;
       }
    }
}
