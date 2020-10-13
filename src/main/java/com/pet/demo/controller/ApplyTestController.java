package com.pet.demo.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("Apply")
public class ApplyTestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplyService applyService;


    @GetMapping("/findAll")
    public String findAll(Model model){
        List<Apply> applies=applyService.findAll();
        model.addAttribute("applies",applies);
        return "apply";
    }

    @GetMapping("/save/{id}/{pet}")
    public String save(@PathVariable(name = "id")String id,
                       @PathVariable(name = "pet")String petName)
    {
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

    @GetMapping("/findone")
    public String findone( Model model){
        Apply apply=applyService.findOne("1");
        System.out.println(apply);
        return "success";
    }

    @GetMapping("delete")
    public String delete(String id){
        applyService.delete("2");
        return "success";
    }

    @GetMapping("update")
    public String update(){
        Apply apply=applyService.findOne("1");
        apply.setApplyPetName("update");
        apply.setApplyTime("update");
        apply.setApplyUserAddress("update");
        apply.setApplyUserSex("update");
        apply.setApplyUserState("update");
        apply.setApplyUserTelephone("update");
        apply.setApplyUserName("update");
        applyService.update(apply);
        return "success";
    }
}
