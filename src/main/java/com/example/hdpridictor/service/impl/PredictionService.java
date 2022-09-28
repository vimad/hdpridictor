package com.example.hdpridictor.service.impl;

import com.example.hdpridictor.dto.PredictionResultDto;
import com.example.hdpridictor.dto.UserDataDto;
import com.example.hdpridictor.service.PredictionEngine;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PredictionService {

    private final List<PredictionEngine> predictionEngines;

    public PredictionService(List<PredictionEngine> predictionEngines) {
        this.predictionEngines = predictionEngines;
    }

    public PredictionResultDto predict(UserDataDto userDataDto, String engine) {
        PredictionEngine predictionEngine = getEngine(engine);
        return predictionEngine.getPredictionResult(userDataDto);
    }

    private PredictionEngine getEngine(String engineName) {
        return predictionEngines.stream()
                .filter(engine -> engine.isTheEngine(engineName))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Engine Not Configured"));
    }
}
