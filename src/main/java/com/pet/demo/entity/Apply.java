package com.pet.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Apply {
    private String applyId;
    private String applyUserName;
    private String applyPetName;
    private String applyUserSex;
    private String applyUserAddress;
    private String applyUserTelephone;
    private String applyUserState;
    private String applyTime;
    private String applyState;
    private String applyUserId;
    private String applyPetId;
}
