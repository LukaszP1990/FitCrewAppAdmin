package com.fitcrew.FitCrewAppAdmin.feignclient;

import com.fitcrew.FitCrewAppModel.domain.model.ClientDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
	List<ClientDto> getClients();

	@DeleteMapping(value = "/deleteClient/{clientEmail}/clientEmail",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	ClientDto deleteClient(@PathVariable String clientEmail);

	@PutMapping(value = "/updateClient/{clientEmail}/clientEmail",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	ClientDto updateClient(@RequestBody ClientDto clientDto,
						   @PathVariable String clientEmail);

	@GetMapping(value = "/getClient/{clientEmail}/clientEmail",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	ClientDto getClient(@PathVariable String clientEmail);
}
