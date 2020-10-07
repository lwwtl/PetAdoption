package com.pet.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
        private String userId;
        private String userAccount;
        private String userPassword;
        private String userName;
        private String userAge;
        private String userSex;
        private String userTelephone;
        private String userEmail;
        private String userAddress;
        private String userState;
}
