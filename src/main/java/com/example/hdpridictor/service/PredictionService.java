package com.example.hdpridictor.service;

import com.example.hdpridictor.dto.PredictionResultDto;
import com.example.hdpridictor.dto.UserDataDto;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PredictionService {

    public PredictionResultDto predict(UserDataDto userDataDto) {
        Random random = new Random();
        return PredictionResultDto.builder()
                .predictionResult(random.nextDouble())
                .build();
    }
}
