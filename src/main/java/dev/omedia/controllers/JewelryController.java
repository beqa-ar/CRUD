package dev.omedia.controllers;

import dev.omedia.domains.Jewelry;
import dev.omedia.enums.LoanStatus;
import dev.omedia.services.JewelryService;
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
@RequestMapping("jeweleries")
public class JewelryController {
    private final JewelryService service;

    @Autowired
    public JewelryController(JewelryService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get Jewelries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Jewelries", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Iterable<Jewelry>> getJewelries(
            @Min(value = 0, message = "incorrect value") @Parameter(description = "page index(start from 0)")
            @RequestParam(required = false, defaultValue = "${page}", name = "page") final int page

            , @Min(30) @Max(500) @Parameter(description = "page size(min 30  max 500)")
            @RequestParam(required = false, defaultValue = "${pageSize}", name = "pageSize") final int pageSize) {

        return new ResponseEntity<>(service.getJewelries(PageRequest.of(page, pageSize)), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get Jewelry by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Jewelry by id", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Jewelry with the given ID does not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Jewelry> getJewelry(@PathVariable final long id) {
        return new ResponseEntity<>(service.getJewelry(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remove Jewelry by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete Jewelry by id", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Jewelry with the given ID does not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Jewelry> removeJewelry(@PathVariable final long id) {
        service.removeJewelryById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add Jewelry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "add Jewelry", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "409", description = "Jewelry with the given ID already exists",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Jewelry> addJewelry(@Valid @RequestBody final Jewelry jewelry) {
        System.out.println(jewelry);
        return new ResponseEntity<>(service.addJewelry(jewelry), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update Jewelry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update Jewelry", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Jewelry with the given ID dose not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Jewelry> updateJewelry(@PathVariable final long id, @Valid @RequestBody final Jewelry jewelry) {
        return new ResponseEntity<>(service.updateJewelry(id, jewelry), HttpStatus.OK);
    }


    @GetMapping("/byPersonalNo")
    @Operation(summary = "Get Jewelries by owner personal no")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Jewelries", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Iterable<Jewelry>> getJewelriesByOwnerPersonalNo(
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
        return new ResponseEntity<>(service.getJewelriesByOwnerPersonalNo(personalNo,PageRequest.of(page, pageSize)), HttpStatus.OK);
    }

    @GetMapping("/byLoanStatus")
    @Operation(summary = "Get Jewelries by loan status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Jewelries", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Iterable<Jewelry>> getJewelriesByLoanStatus(
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
        return new ResponseEntity<>(service.getJewelriesByLoanStatus(loanStatus,PageRequest.of(page, pageSize)), HttpStatus.OK);
    }


}
