package com.fitcrew.FitCrewAppAdmin.feignclient;

import com.fitcrew.FitCrewAppAdmin.dto.ClientDto;
import com.fitcrew.FitCrewAppModel.domain.model.ClientModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "client-ws", path = "/client")
public interface FeignClientService {

	@GetMapping(value = "/getClients",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	List<ClientModel> getClients();

	@DeleteMapping(value = "/deleteClient/{clientEmail}/clientEmail",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	ClientModel deleteClient(@PathVariable String clientEmail);

	@PutMapping(value = "/updateClient/{clientEmail}/clientEmail",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	ClientModel updateClient(@RequestBody ClientDto clientDto,
						   @PathVariable String clientEmail);

	@GetMapping(value = "/getClient/{clientEmail}/clientEmail",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	ClientModel getClient(@PathVariable String clientEmail);
}
