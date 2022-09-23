package com.example.hdpridictor;

import com.example.hdpridictor.dao.UserData;
import com.example.hdpridictor.repository.UserDataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class FIleToDB implements CommandLineRunner {

    private final UserDataRepository userDataRepository;

    public FIleToDB(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }


    @Override
    public void run(String... args) throws Exception {
//        try {
//            InputStream resource = new ClassPathResource("heart.csv").getInputStream();
//            try ( BufferedReader reader = new BufferedReader(new InputStreamReader(resource)) ) {
//                reader.lines().forEach(this::saveToDB);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void saveToDB(String line) {
        String[] split = line.split(",");
        if (split[0].equals("Age")) return;
        UserData userData = UserData.builder()
                .age(Integer.parseInt(split[0]))
                .sex(split[1])
                .chestPain(split[2])
                .restingBP(Integer.parseInt(split[3]))
                .cholesterol(Integer.parseInt(split[4]))
                .fastingBS(Integer.parseInt(split[5]))
                .restingECG(split[6])
                .maxHR(Integer.parseInt(split[7]))
                .exerciseAngina(split[8])
                .oldPeak(Double.parseDouble(split[9]))
                .stSlope(split[10])
                .heartDisease(Integer.parseInt(split[11]))
                .build();
        userDataRepository.save(userData);
    }
}
