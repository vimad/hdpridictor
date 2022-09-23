package com.example.hdpridictor.controller;

import com.example.hdpridictor.dto.PredictionResultDto;
import com.example.hdpridictor.dto.UserDataDto;
import com.example.hdpridictor.service.PredictionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/predict")
public class HeartDiseasePredictController {

    private final PredictionService predictionService;

    public HeartDiseasePredictController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("")
    public PredictionResultDto predict(@RequestBody UserDataDto userDataDto) {
        return predictionService.predict(userDataDto);
    }
}
