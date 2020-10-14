package com.pet.demo.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            return "agree";
        } else {
            String name = '%' + searchName + '%';
            PageHelper.startPage(pageNum, 5);
            List<Apply> applies = applyService.findByName(name,"不同意领养");
            PageInfo<Apply> pageInfo = new PageInfo<>(applies);
            model.addAttribute("applies", pageInfo);
            return "agree";
        }
    }


    @GetMapping("/save/{id}/{pet}")
    public String save(@PathVariable(name = "id") String id,
                       @PathVariable(name = "pet") String petName) {
        User user = userService.findOne(id);
        Apply apply = new Apply();
        apply.setApplyUserName(user.getUserName());
        apply.setApplyUserSex(user.getUserSex());
        apply.setApplyUserAddress(user.getUserAddress());
        apply.setApplyUserTelephone(user.getUserTelephone());
        apply.setApplyUserState(user.getUserState());
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        apply.setApplyTime(now);
        apply.setApplyPetName(petName);
        apply.setApplyState("审核中");
        applyService.save(apply);
        return "redirect:/index";
    }


    @GetMapping("/agree/{applyId}")
    public String agree(@PathVariable(name = "applyId") String applyId) {
        Apply apply = applyService.findOne(applyId);
        apply.setApplyState("同意领养");
        applyService.update(apply);
        return "redirect:/Apply/find";
    }

    @GetMapping("/disagree/{applyId}")
    public String disagree(@PathVariable(name = "applyId") String applyId) {
        Apply apply = applyService.findOne(applyId);
        apply.setApplyState("不同意领养");
        applyService.update(apply);
        return "redirect:/Apply/find";
    }
}
