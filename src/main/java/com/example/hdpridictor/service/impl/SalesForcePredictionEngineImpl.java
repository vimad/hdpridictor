package com.example.hdpridictor.service.impl;

import com.example.hdpridictor.dto.PredictionResultDto;
import com.example.hdpridictor.dto.SFPredictionRequest;
import com.example.hdpridictor.dto.SFPredictionResult;
import com.example.hdpridictor.dto.UserDataDto;
import com.example.hdpridictor.service.PredictionEngine;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class SalesForcePredictionEngineImpl implements PredictionEngine {
    @Override
    public PredictionResultDto getPredictionResult(UserDataDto userDataDto) {
        String accessToken = getAccessToken();
        return getPredictionResultFromSF(accessToken, userDataDto);
    }

    @Override
    public boolean isTheEngine(String engine) {
        return "sf".equals(engine);
    }

    private String getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type","password");
        map.add("client_id","3MVG9JEx.BE6yifN0Ail8p.YnkTCMn6S2mrrjG_wJRUTlaqPn0C5lVpPI6EPQPQe5K6H8liuOLX7hqA0bQPSR");
        map.add("client_secret","467101D478C0AD7215C3D055D98536A3A363734BA697026530071778CEFD8231");
        map.add("username","analyst@technosysds.com");
        map.add("password","Water@12344w5vtX3okFciVnGy944IUbxe");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<Map> response =
                restTemplate.exchange("https://login.salesforce.com/services/oauth2/token",
                        HttpMethod.POST,
                        entity,
                        Map.class);
        Map body = response.getBody();
        return (String) body.get("access_token");
    }

    private PredictionResultDto getPredictionResultFromSF(String accessToken, UserDataDto userDataDto) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + accessToken);

        SFPredictionRequest sfPredictionRequest = new SFPredictionRequest();
        sfPredictionRequest.setType("RawData");
        sfPredictionRequest.setPredictionDefinition("1OR3t000000PGhqGAG");
        sfPredictionRequest.setColumnNames(List.of(
                "Cholesterol",
                "Age",
                "Sex",
                "ChestPainType",
                "RestingBP"
        ));
        sfPredictionRequest.setRows(List.of(
                List.of(
                        userDataDto.getCholesterol().toString(),
                        userDataDto.getAge().toString(),
                        userDataDto.getSex(),
                        userDataDto.getChestPain(),
                        userDataDto.getRestingBP().toString()
                )
        ));

        HttpEntity<SFPredictionRequest> request = new HttpEntity<>(sfPredictionRequest, headers);
        ResponseEntity<SFPredictionResult> response = restTemplate
                .exchange("https://technosysds2-dev-ed.my.salesforce.com/services/data/v55.0/smartdatadiscovery/predict",
                        HttpMethod.POST,
                        request, SFPredictionResult.class);
        SFPredictionResult body = response.getBody();

        return PredictionResultDto.builder()
                .predictionResult(body.getPredictions().get(0).getPrediction().getTotal())
                .build();
    }
}
