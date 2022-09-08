package dev.omedia.controllers;

import dev.omedia.domains.Technic;
import dev.omedia.enums.LoanStatus;
import dev.omedia.services.TechnicService;
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
@RequestMapping("technics")
public class TechnicController {
    private final TechnicService service;

    @Autowired
    public TechnicController(TechnicService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get Technics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Technics", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Iterable<Technic>> getTechnics(
            @Min(value = 0, message = "incorrect value") @Parameter(description = "page index(start from 0)")
            @RequestParam(required = false, defaultValue = "${page}", name = "page") final int page

            , @Min(30) @Max(500) @Parameter(description = "page size(min 30  max 500)")
            @RequestParam(required = false, defaultValue = "${pageSize}", name = "pageSize") final int pageSize) {

        return new ResponseEntity<>(service.getTechnics(PageRequest.of(page, pageSize)), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get Technic by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Technic by id", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Technic with the given ID does not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Technic> getTechnic(@PathVariable final long id) {
        return new ResponseEntity<>(service.getTechnic(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remove Technic by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete Technic by id", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Technic with the given ID does not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Technic> removeTechnic(@PathVariable final long id) {
        service.removeTechnicById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add Technic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "add Technic", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "409", description = "Technic with the given ID already exists",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Technic> addTechnic(@Valid @RequestBody final Technic technic) {
        System.out.println(technic);
        return new ResponseEntity<>(service.addTechnic(technic), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update Technic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update Technic", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Technic with the given ID dose not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Technic> updateTechnic(@PathVariable final long id, @Valid @RequestBody final Technic technic) {
        return new ResponseEntity<>(service.updateTechnic(id, technic), HttpStatus.OK);
    }


    @GetMapping("/byPersonalNo")
    @Operation(summary = "Get Technics by owner personal no")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Technics", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Iterable<Technic>> getTechnicsByOwnerPersonalNo(
            @Min(value = 0, message = "incorrect value") @Parameter(description = "page index(start from 0)")
            @RequestParam(required = false, defaultValue = "${page}", name = "page") final int page

            , @Min(30) @Max(500) @Parameter(description = "page size(min 30  max 500)")
            @RequestParam(required = false, defaultValue = "${pageSize}", name = "pageSize") final int pageSize

            ,
            @Pattern(regexp="\\d{11}",message="only numbers! length must be 11")
            @Parameter(description = "owner personal no")
            @RequestParam final String personalNo)
    {

        System.out.println(personalNo);
        return new ResponseEntity<>(service.getTechnicsByOwnerPersonalNo(personalNo,PageRequest.of(page, pageSize)), HttpStatus.OK);
    }

    @GetMapping("/byLoanStatus")
    @Operation(summary = "Get Technics by loan status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Technics", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Iterable<Technic>> getTechnicsByLoanStatus(
            @Min(value = 0, message = "incorrect value") @Parameter(description = "page index(start from 0)")
            @RequestParam(required = false, defaultValue = "${page}", name = "page") final int page

            , @Min(30) @Max(500) @Parameter(description = "page size(min 30  max 500)")
            @RequestParam(required = false, defaultValue = "${pageSize}", name = "pageSize") final int pageSize

            ,
            @Pattern(regexp="\\d{11}",message="only numbers! length must be 11")
            @Parameter(description = "owner personal no")
            @RequestParam final LoanStatus loanStatus)
    {

        System.out.println(loanStatus);
        return new ResponseEntity<>(service.getTechnicsByLoanStatus(loanStatus,PageRequest.of(page, pageSize)), HttpStatus.OK);
    }


}
