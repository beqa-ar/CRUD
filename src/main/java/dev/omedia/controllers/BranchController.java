package dev.omedia.controllers;

import dev.omedia.domains.Branch;
import dev.omedia.services.BranchService;
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

@RestController
@RequestMapping("branches")
public class BranchController {
    private final BranchService service;

    @Autowired
    public BranchController(BranchService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get Branches")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Branches", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Iterable<Branch>> getBranches(
            @Min(value = 0, message = "incorrect value") @Parameter(description = "page index(start from 0)")
            @RequestParam(required = false, defaultValue = "${page}", name = "page") final int page

            , @Min(30) @Max(500) @Parameter(description = "page size(min 30  max 500)")
            @RequestParam(required = false, defaultValue = "${pageSize}", name = "pageSize") final int pageSize) {

        return new ResponseEntity<>(service.getBranches(PageRequest.of(page, pageSize)), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get Branch by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Branch by id", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Branch with the given ID does not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Branch> getBranch(@PathVariable final long id) {
        return new ResponseEntity<>(service.getBranch(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remove Branch by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete Branch by id", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Branch with the given ID does not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Branch> removeBranch(@PathVariable final long id) {
        service.removeBranchById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add Branch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "add Branch", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "409", description = "Branch with the given ID already exists",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Branch> addBranch(@Valid @RequestBody final Branch branch) {
        System.out.println(branch);
        return new ResponseEntity<>(service.addBranch(branch), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update Branch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update Branch", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Branch with the given ID dose not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Branch> updateBranch(@PathVariable final long id, @Valid @RequestBody final Branch branch) {
        return new ResponseEntity<>(service.updateBranch(id, branch), HttpStatus.OK);
    }


}
