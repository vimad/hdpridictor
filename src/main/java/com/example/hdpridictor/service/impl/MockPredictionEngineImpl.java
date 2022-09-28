package com.example.hdpridictor.service.impl;

import com.example.hdpridictor.dto.PredictionResultDto;
import com.example.hdpridictor.dto.UserDataDto;
import com.example.hdpridictor.service.PredictionEngine;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MockPredictionEngineImpl implements PredictionEngine {
    @Override
    public PredictionResultDto getPredictionResult(UserDataDto userDataDto) {
        Random random = new Random();
        return PredictionResultDto.builder()
                .predictionResult(random.nextDouble() * 100)
                .build();
    }

    @Override
    public boolean isTheEngine(String engine) {
        return "mock".equals(engine);
    }
}
