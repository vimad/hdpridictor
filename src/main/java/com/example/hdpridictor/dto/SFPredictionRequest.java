package com.example.hdpridictor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SFPredictionRequest {
    private String predictionDefinition;
    private String type;
    private List<String> columnNames;
    private List<List<String>> rows;
}
