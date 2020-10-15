package com.pet.demo.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pet.demo.config.Log;
import com.pet.demo.entity.Apply;
import com.pet.demo.entity.Pet;
import com.pet.demo.entity.User;
import com.pet.demo.service.ApplyService;
import com.pet.demo.service.PetService;
import com.pet.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
@RequestMapping("Apply")
public class ApplyTestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplyService applyService;

    @Autowired
    private PetService petService;



    @GetMapping("/find")
    public String find(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(name = "searchName", required = false) String searchName) {
        if (StringUtils.isEmpty(searchName)) {
            PageHelper.startPage(pageNum, 5);
            List<Apply> applies = applyService.findAll("审核中");
            PageInfo<Apply> pageInfo = new PageInfo<>(applies);
            model.addAttribute("applies", pageInfo);
            return "apply";
        } else {
            String name = '%' + searchName + '%';
            PageHelper.startPage(pageNum, 5);
            List<Apply> applies = applyService.findByName(name,"审核中");
            PageInfo<Apply> pageInfo = new PageInfo<>(applies);
            model.addAttribute("applies", pageInfo);
            return "apply";
        }
    }
    @GetMapping("/agreePage")
    public String agreePage(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(name = "searchName", required = false) String searchName) {
        if (StringUtils.isEmpty(searchName)) {
            PageHelper.startPage(pageNum, 5);
            List<Apply> applies = applyService.findAll("同意领养");
            PageInfo<Apply> pageInfo = new PageInfo<>(applies);
            model.addAttribute("applies", pageInfo);
            return "agree";
        } else {
            String name = '%' + searchName + '%';
            PageHelper.startPage(pageNum, 5);
            List<Apply> applies = applyService.findByName(name,"同意领养");
            PageInfo<Apply> pageInfo = new PageInfo<>(applies);
            model.addAttribute("applies", pageInfo);
            return "agree";
        }
    }
    @GetMapping("/disagreePage")
    public String disagreePage(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(name = "searchName", required = false) String searchName) {
        if (StringUtils.isEmpty(searchName)) {
            PageHelper.startPage(pageNum, 5);
            List<Apply> applies = applyService.findAll("不同意领养");
            PageInfo<Apply> pageInfo = new PageInfo<>(applies);
            model.addAttribute("applies", pageInfo);
            return "disagree";
        } else {
            String name = '%' + searchName + '%';
            PageHelper.startPage(pageNum, 5);
            List<Apply> applies = applyService.findByName(name,"不同意领养");
            PageInfo<Apply> pageInfo = new PageInfo<>(applies);
            model.addAttribute("applies", pageInfo);
            return "disagree";
        }
    }


    @GetMapping("/save/{id}/{petId}")
    @ResponseBody
    public String save(@PathVariable(name = "id") String id,
                       @PathVariable(name = "petId") String petId) {
        User user = userService.findOne(id);
        Pet pet = petService.findOne(petId);
        Apply apply = new Apply();
        apply.setApplyUserName(user.getUserName());
        apply.setApplyUserSex(user.getUserSex());
        apply.setApplyUserAddress(user.getUserAddress());
        apply.setApplyUserTelephone(user.getUserTelephone());
        apply.setApplyUserState(user.getUserState());
        apply.setApplyUserId(id);
        apply.setApplyPetId(petId);
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        apply.setApplyTime(now);
        apply.setApplyPetName(pet.getPetName());
        apply.setApplyState("审核中");
        applyService.save(apply);
        String msg = "ok";
        return msg;
    }

    @Log
    @Transactional
    @GetMapping("/agree/{applyId}/{petId}")
    public String agree(@PathVariable(name = "applyId") String applyId,
                        @PathVariable(name = "petId") String petId) {
        Apply apply = applyService.findOne(applyId);
        apply.setApplyState("同意领养");
        applyService.update(apply);
        Pet pet = petService.findOne(petId);
        pet.setPetState("已被领养");
        petService.update(pet);
        applyService.modify(petId,"审核中");
        return "redirect:/Apply/find";
    }

    @Log
    @GetMapping("/disagree/{applyId}")
    public String disagree(@PathVariable(name = "applyId") String applyId) {
        Apply apply = applyService.findOne(applyId);
        apply.setApplyState("不同意领养");
        applyService.update(apply);
        return "redirect:/Apply/find";
    }
}
