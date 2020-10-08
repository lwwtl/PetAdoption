package com.pet.demo.controller;

import com.pet.demo.entity.Apply;
import com.pet.demo.entity.Pet;
import com.pet.demo.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("ApplyTest")
public class ApplyTestController {

    @Autowired
    private ApplyService applyService;

    @GetMapping("findAll")
    public String findAll(){
        List<Apply> applies=applyService.findAll();
        for(Apply apply:applies){
            System.out.println(apply);
        }
        return "success";
    }

    @GetMapping("save")
    public String save(){
        Apply apply=new Apply();
        apply.setApplyId(UUID.randomUUID().toString());
        apply.setApplyPetName("test");
        apply.setApplyTime("test");
        apply.setApplyUserAddress("test");
        apply.setApplyUserSex("test");
        apply.setApplyUserState("test");
        apply.setApplyUserTelephone("test");
        apply.setApplyUserName("test");
        applyService.save(apply);
        return "success";
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
