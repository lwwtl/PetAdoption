package com.pet.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {
    private String adminId;
    private String adminAccount;
    private String adminPassword;
    private String adminName;
    private String adminAge;
    private String adminSex;
    private String adminTelephone;
    private String adminEmail;
}
