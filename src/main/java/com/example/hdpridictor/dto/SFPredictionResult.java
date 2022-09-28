package com.example.hdpridictor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SFPredictionResult {

    private List<Result> predictions;

    @Getter
    @Setter
    public static class Result{
        private Prediction prediction;
    }

    @Getter
    @Setter
    public static class Prediction {
        private double total;
    }
}
