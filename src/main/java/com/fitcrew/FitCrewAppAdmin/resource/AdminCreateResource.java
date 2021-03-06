package com.fitcrew.FitCrewAppAdmin.resource;

import com.fitcrew.FitCrewAppAdmin.dto.AdminDto;
import com.fitcrew.FitCrewAppAdmin.resolver.ResponseResolver;
import com.fitcrew.FitCrewAppAdmin.services.create.AdminCreateServiceFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Admin sign up resource")
@Slf4j
@RestController
@RequestMapping("/admin")
class AdminCreateResource {

    private final AdminCreateServiceFacade adminCreateServiceFacade;

    public AdminCreateResource(AdminCreateServiceFacade adminCreateServiceFacade) {
        this.adminCreateServiceFacade = adminCreateServiceFacade;
    }

    @ApiOperation(value = "Create new admin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful admin create response!"),
            @ApiResponse(code = 400, message = "400 bad request, rest call is made with some invalid data!"),
            @ApiResponse(code = 404, message = "404 not found, url is wrong")
    })
    @PostMapping(value = "/createAdmin",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,})
    public ResponseEntity createAdmin(@RequestBody AdminDto adminDto) {
        log.debug("Admin to save: {}", adminDto);

        return ResponseResolver.resolve(
                adminCreateServiceFacade.createAdmin(adminDto)
        );

    }
}
