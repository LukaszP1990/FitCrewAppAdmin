package com.fitcrew.FitCrewAppAdmin.resource;

import com.fitcrew.FitCrewAppAdmin.dto.ClientDto;
import com.fitcrew.FitCrewAppAdmin.dto.TrainerDto;
import com.fitcrew.FitCrewAppAdmin.resolver.ResponseResolver;
import com.fitcrew.FitCrewAppAdmin.services.capabilities.AdminCapabilitiesServiceFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Admin capabilities resource")
@Slf4j
@RestController
@RequestMapping("/admin")
class AdminCapabilitiesResource {

    private final AdminCapabilitiesServiceFacade adminCapabilitiesServiceFacade;

    AdminCapabilitiesResource(AdminCapabilitiesServiceFacade adminCapabilitiesServiceFacade) {
        this.adminCapabilitiesServiceFacade = adminCapabilitiesServiceFacade;
    }

    @ApiOperation(value = "Return all clients for admin")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successful all clients for admin response!"),
                    @ApiResponse(code = 400, message = "400 bad request, rest call is made with some invalid data!"),
                    @ApiResponse(code = 404, message = "404 not found, url is wrong")
            }
    )
    @GetMapping(value = "/getClients",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,})
    public ResponseEntity getListsOfClients() {

        return ResponseResolver.resolve(
                adminCapabilitiesServiceFacade.getClients()
        );
    }

    @ApiOperation(value = "Return single client for admin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful get single client for admin response!"),
            @ApiResponse(code = 400, message = "400 bad request, rest call is made with some invalid data!"),
            @ApiResponse(code = 404, message = "404 not found, url is wrong")
    })
    @GetMapping(value = "/getClient/{clientEmail}/clientEmail",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getClient(@PathVariable String clientEmail) {

        return ResponseResolver.resolve(
                adminCapabilitiesServiceFacade.getClient(clientEmail)
        );
    }

    @ApiOperation(value = "Return client who has been deleted")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successful client who has been deleted response!"),
                    @ApiResponse(code = 400, message = "400 bad request, rest call is made with some invalid data!"),
                    @ApiResponse(code = 404, message = "404 not found, url is wrong")
            }
    )
    @DeleteMapping(value = "/deleteClient/{clientEmail}/clientEmail",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,})
    public ResponseEntity deleteClient(@PathVariable String clientEmail) {
        log.debug("Delete client by client email address: {}", clientEmail);

        return ResponseResolver.resolve(
                adminCapabilitiesServiceFacade.deleteClient(clientEmail)
        );
    }

    @ApiOperation(value = "Return client who has been updated")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successful client who has been updated response!"),
                    @ApiResponse(code = 400, message = "400 bad request, rest call is made with some invalid data!"),
                    @ApiResponse(code = 404, message = "404 not found, url is wrong")
            }
    )
    @PutMapping(value = "/updateClient/{clientEmail}/clientEmail",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,})
    public ResponseEntity updateClient(@RequestBody ClientDto clientDto,
                                        @PathVariable String clientEmail) {
        log.debug("Update client: {} \n by client email address: {}", clientDto, clientEmail);

        return ResponseResolver.resolve(
                adminCapabilitiesServiceFacade.updateClient(clientDto, clientEmail)
        );
    }

    @ApiOperation(value = "Return all trainers for admin")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successful all trainers for admin response!"),
                    @ApiResponse(code = 400, message = "400 bad request, rest call is made with some invalid data!"),
                    @ApiResponse(code = 404, message = "404 not found, url is wrong")
            }
    )
    @GetMapping(value = "/getTrainers",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,})
    public ResponseEntity getListsOfTrainers() {

        return ResponseResolver.resolve(adminCapabilitiesServiceFacade.getTrainers());
    }

    @ApiOperation(value = "Return single trainer for admin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful get single trainer for admin response!"),
            @ApiResponse(code = 400, message = "400 bad request, rest call is made with some invalid data!"),
            @ApiResponse(code = 404, message = "404 not found, url is wrong")
    })
    @GetMapping(value = "/getTrainer/{trainerEmail}/trainerEmail",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getTrainer(@PathVariable String trainerEmail) {
        log.debug("Get trainer by trainer email address: {}", trainerEmail);

        return ResponseResolver.resolve(
                adminCapabilitiesServiceFacade.getTrainer(trainerEmail)
        );
    }

    @ApiOperation(value = "Return trainer who has been deleted")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successful trainer who has been deleted response!"),
                    @ApiResponse(code = 400, message = "400 bad request, rest call is made with some invalid data!"),
                    @ApiResponse(code = 404, message = "404 not found, url is wrong")
            }
    )
    @DeleteMapping(value = "/deleteTrainer/{trainerEmail}/trainerEmail",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,})
    public ResponseEntity deleteTrainer(@PathVariable String trainerEmail) {
        log.debug("Delete trainer by trainer email address: {}", trainerEmail);

        return ResponseResolver.resolve(
                adminCapabilitiesServiceFacade.deleteTrainer(trainerEmail)
        );
    }

    @ApiOperation(value = "Return trainer who has been updated")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successful trainer who has been updated response!"),
                    @ApiResponse(code = 400, message = "400 bad request, rest call is made with some invalid data!"),
                    @ApiResponse(code = 404, message = "404 not found, url is wrong")
            }
    )
    @PutMapping(value = "/updateTrainer/{trainerEmail}/trainerEmail",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,})
    public ResponseEntity updateTrainer(@RequestBody TrainerDto trainerDto,
                                         @PathVariable String trainerEmail) {
        log.debug("Update trainer: {} \n by trainer email address: {}", trainerDto, trainerEmail);

        return ResponseResolver.resolve(
                adminCapabilitiesServiceFacade.updateTrainer(trainerDto, trainerEmail)
        );
    }
}
