package com.example.hdpridictor.service;

import com.example.hdpridictor.dto.PredictionResultDto;
import com.example.hdpridictor.dto.UserDataDto;

public interface PredictionEngine {
    PredictionResultDto getPredictionResult(UserDataDto userDataDto);
    boolean isTheEngine(String engine);
}
