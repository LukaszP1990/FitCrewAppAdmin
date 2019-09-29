package com.fitcrew.FitCrewAppAdmin.resources;

import com.fitcrew.FitCrewAppAdmin.dto.ClientDto;
import com.fitcrew.FitCrewAppAdmin.dto.TrainerDto;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;
import com.fitcrew.FitCrewAppAdmin.resolver.ResponseResolver;
import com.fitcrew.FitCrewAppAdmin.services.AdminCapabilitiesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Admin capabilities resource")
@Slf4j
@RestController
@RequestMapping("/admin")
class AdminCapabilitiesResource {

    private final AdminCapabilitiesService adminCapabilitiesService;

    AdminCapabilitiesResource(AdminCapabilitiesService adminCapabilitiesService) {
        this.adminCapabilitiesService = adminCapabilitiesService;
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
    private ResponseEntity getListsOfClients() {

        return ResponseResolver.resolve(adminCapabilitiesService.getClients());
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

        return ResponseResolver.resolve(adminCapabilitiesService.getClient(clientEmail));
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
    private ResponseEntity deleteClient(@PathVariable String clientEmail) {

        return ResponseResolver.resolve(adminCapabilitiesService.deleteClient(clientEmail));
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
    private ResponseEntity updateClient(@RequestBody ClientDto clientDto,
                                        @PathVariable String clientEmail) {

        Either<ErrorMsg, ClientDto> updatedClient =
                adminCapabilitiesService.updateClient(clientDto, clientEmail);
        return ResponseResolver.resolve(updatedClient);
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
    private ResponseEntity getListsOfTrainers() {

        return ResponseResolver.resolve(adminCapabilitiesService.getTrainers());
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

        return ResponseResolver.resolve(adminCapabilitiesService.getTrainer(trainerEmail));
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
    private ResponseEntity deleteTrainer(@PathVariable String trainerEmail) {

        return ResponseResolver.resolve(adminCapabilitiesService.deleteTrainer(trainerEmail));
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
    private ResponseEntity updateTrainer(@RequestBody TrainerDto trainerDto,
                                         @PathVariable String trainerEmail) {

        Either<ErrorMsg, TrainerDto> updatedTrainer =
                adminCapabilitiesService.updateTrainer(trainerDto, trainerEmail);
        return ResponseResolver.resolve(updatedTrainer);
    }
}
