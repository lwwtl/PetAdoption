package com.pet.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private String adminId;
    private String adminAccount;
    private String adminPassword;
    private String adminName;
    private Integer adminAge;
    private String adminSex;
    private String adminTelephone;
    private String adminEmail;
}
