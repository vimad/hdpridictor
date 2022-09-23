package com.example.hdpridictor.dao;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userDataSeqGen")
    @GenericGenerator(name = "userDataSeqGen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "userDataSeq"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1")
            }
    )
    private Long id;
    private Integer age;
    private String sex;
    private String chestPain;
    private Integer restingBP;
    private Integer cholesterol;
    private Integer fastingBS;
    private String restingECG;
    private Integer maxHR;
    private String exerciseAngina;
    private Double oldPeak;
    private String stSlope;
    private Integer heartDisease;
}
