//package com.pet.demo.controller;
//
//
//import com.pet.demo.entity.File;
//import com.pet.demo.provider.UCloudProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@Controller
//public class FileController {
//
//    @Autowired(required = false)
//    private UCloudProvider uCloudProvider;
//
//    @RequestMapping("/file/upload")
//    @ResponseBody
//    public File upload(HttpServletRequest request){
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        MultipartFile file = multipartRequest.getFile("editormd-image-file");
//        try {
//            String fileName = uCloudProvider.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
//            File fileDTO = new File();
//            fileDTO.setSuccess(1);
//            fileDTO.setUrl(fileName);
//            return fileDTO;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        File fileDTO = new File();
//        fileDTO.setSuccess(1);
//        fileDTO.setUrl("/images/bg.jpg");
//        return fileDTO;
//    }
//}
