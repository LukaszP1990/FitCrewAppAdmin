package com.fitcrew.FitCrewAppAdmin.feignclient;

import com.fitcrew.FitCrewAppAdmin.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "client-ws", path = "/client")
public interface FeignClientService {

    @GetMapping(value = "/getClients")
    List<ClientDto> getClients();

    @DeleteMapping(value = "/deleteClient/{clientEmail}/clientEmail")
    ClientDto deleteClient(@PathVariable String clientEmail);

    @PutMapping(value = "/updateClient/{clientEmail}/clientEmail")
    ClientDto updateClient(ClientDto clientDto,
                           String clientEmail);

    @GetMapping(value = "/getClient/{clientEmail}/clientEmail")
    ClientDto getClient(@PathVariable String clientEmail);
}
