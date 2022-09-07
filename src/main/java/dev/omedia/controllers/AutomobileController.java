package dev.omedia.controllers;

import dev.omedia.domains.Automobile;
import dev.omedia.services.AutomobileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("automobiles")
public class AutomobileController {
    private final AutomobileService service;

    @Autowired
    public AutomobileController(AutomobileService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get Automobiles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Automobiles", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Iterable<Automobile>> getAutomobiles(
            @Min(value = 0, message = "incorrect value") @Parameter(description = "page index(start from 0)")
            @RequestParam(required = false, defaultValue = "${page}", name = "page") final int page

            , @Min(30) @Max(500) @Parameter(description = "page size(min 30  max 500)")
            @RequestParam(required = false, defaultValue = "${pageSize}", name = "pageSize") final int pageSize) {

        return new ResponseEntity<>(service.getAutomobiles(PageRequest.of(page, pageSize)), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get Automobile by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Automobile by id", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Automobile with the given ID does not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Automobile> getAutomobile(@PathVariable final long id) {
        return new ResponseEntity<>(service.getAutomobile(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remove Automobile by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete Automobile by id", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Automobile with the given ID does not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Automobile> removeAutomobile(@PathVariable final long id) {
        service.removeAutomobileById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add Automobile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "add Automobile", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "409", description = "Automobile with the given ID already exists",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Automobile> addAutomobile(@Valid @RequestBody final Automobile automobile) {
        System.out.println(automobile);
        return new ResponseEntity<>(service.addAutomobile(automobile), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update Automobile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update Automobile", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Automobile with the given ID dose not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Automobile> updateAutomobile(@PathVariable final long id, @Valid @RequestBody final Automobile automobile) {
        return new ResponseEntity<>(service.updateAutomobile(id, automobile), HttpStatus.OK);
    }


    @GetMapping("{personalNo}")
    @Operation(summary = "Get Automobiles by owner personal no")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Automobiles", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Iterable<Automobile>> getAutomobilesByOwnerPersonalNo(
            @Min(value = 0, message = "incorrect value") @Parameter(description = "page index(start from 0)")
            @RequestParam(required = false, defaultValue = "${page}", name = "page") final int page

            , @Min(30) @Max(500) @Parameter(description = "page size(min 30  max 500)")
            @RequestParam(required = false, defaultValue = "${pageSize}", name = "pageSize") final int pageSize

            ,
            @Pattern(regexp="\\d{11}",message="only numbers! length must be 11")
            @Parameter(description = "owner personal no")
            @PathVariable final String personalNo)
    {

        System.out.println(personalNo);
        return new ResponseEntity<>(service.getAutomobilesByOwnerPersonalNo(personalNo,PageRequest.of(page, pageSize)), HttpStatus.OK);
    }


}
