package com.pet.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysLog {
    private int id;
    private String aId;
    private String adminAction;
    private String createTime;
    private String object;
    private String url;
}
