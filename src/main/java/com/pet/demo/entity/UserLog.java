package com.pet.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLog {
    private int id;
    private String userId;
    private String userAction;
    private String createTime;
    private String petId;
    private String url;
}
