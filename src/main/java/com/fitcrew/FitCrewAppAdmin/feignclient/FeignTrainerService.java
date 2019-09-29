package com.fitcrew.FitCrewAppAdmin.feignclient;

import com.fitcrew.FitCrewAppAdmin.dto.TrainerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "trainer-ws", path = "/trainer")
public interface FeignTrainerService {

    @GetMapping(value = "/getTrainers")
    List<TrainerDto> getAllTrainers();

    @DeleteMapping(value = "/deleteTrainer/{trainerEmail}/trainerEmail")
    TrainerDto deleteTrainer(@PathVariable String trainerEmail);

    @PutMapping(value = "/updateTrainer/{trainerEmail}/trainerEmail")
    TrainerDto updateTrainer(TrainerDto trainerDto,
                             String trainerEmail);
}
