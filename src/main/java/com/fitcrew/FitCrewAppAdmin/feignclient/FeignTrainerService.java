package com.fitcrew.FitCrewAppAdmin.feignclient;

import com.fitcrew.FitCrewAppAdmin.dto.TrainerDto;
import com.fitcrew.FitCrewAppModel.domain.model.TrainerModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "trainer-ws", path = "/trainer")
public interface FeignTrainerService {

	@GetMapping(value = "/getTrainers",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	List<TrainerModel> getAllTrainers();

	@DeleteMapping(value = "/deleteTrainer/{trainerEmail}/trainerEmail",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	TrainerModel deleteTrainer(@PathVariable String trainerEmail);

	@PutMapping(value = "/updateTrainer/{trainerEmail}/trainerEmail",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	TrainerModel updateTrainer(@RequestBody TrainerDto trainerDto,
							 @PathVariable String trainerEmail);

	@GetMapping(value = "/getTrainer/{trainerEmail}/trainerEmail",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	TrainerModel getTrainer(@PathVariable String trainerEmail);
}
