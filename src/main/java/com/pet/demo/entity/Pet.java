package com.pet.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pet {
    private String petId;
    private String petName;
    private String petSex;
    private String petSub;
    private String petType;
    private String petBir;
    private String petDetail;
    private String petPic;
    private String petState;
}
