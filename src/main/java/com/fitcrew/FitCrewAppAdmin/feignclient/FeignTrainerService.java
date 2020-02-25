package com.fitcrew.FitCrewAppAdmin.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fitcrew.FitCrewAppModel.domain.model.TrainerDto;

@FeignClient(name = "trainer-ws", path = "/trainer")
public interface FeignTrainerService {

	@GetMapping(value = "/getTrainers",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	List<TrainerDto> getAllTrainers();

	@DeleteMapping(value = "/deleteTrainer/{trainerEmail}/trainerEmail",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	TrainerDto deleteTrainer(@PathVariable String trainerEmail);

	@PutMapping(value = "/updateTrainer/{trainerEmail}/trainerEmail",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	TrainerDto updateTrainer(@RequestBody TrainerDto trainerDto,
							 @PathVariable String trainerEmail);

	@GetMapping(value = "/getTrainer/{trainerEmail}/trainerEmail",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	TrainerDto getTrainer(@PathVariable String trainerEmail);
}
