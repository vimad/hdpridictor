package com.example.hdpridictor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDataDto {
    private Integer age;
    private String sex;
    private String chestPain;
    private Integer restingBP;
    private Integer cholesterol;
    private Integer fastingBS;
    private String restingECG;
    private Integer maxHR;
    private String exerciseAngina;
    private Integer oldPeak;
    private String stSlope;
}
