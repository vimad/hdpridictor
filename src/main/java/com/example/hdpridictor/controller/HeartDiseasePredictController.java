package com.example.hdpridictor.controller;

import com.example.hdpridictor.dto.PredictionResultDto;
import com.example.hdpridictor.dto.UserDataDto;
import com.example.hdpridictor.service.impl.PredictionService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/predict")
public class HeartDiseasePredictController {

    private final PredictionService predictionService;

    public HeartDiseasePredictController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("")
    public PredictionResultDto predict(@RequestBody UserDataDto userDataDto,
                                       @RequestParam(name = "engine", defaultValue = "mock") String engine) {
        return predictionService.predict(userDataDto, engine);
    }
}
