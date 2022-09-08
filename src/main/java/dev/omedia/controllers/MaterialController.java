package dev.omedia.controllers;

import dev.omedia.domains.Material;
import dev.omedia.services.MaterialService;
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
@RequestMapping("materials")
public class MaterialController {
    private final MaterialService service;

    @Autowired
    public MaterialController(MaterialService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get Materials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Materials", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Iterable<Material>> getMaterials(
            @Min(value = 0, message = "incorrect value") @Parameter(description = "page index(start from 0)")
            @RequestParam(required = false, defaultValue = "${page}", name = "page") final int page

            , @Min(30) @Max(500) @Parameter(description = "page size(min 30  max 500)")
            @RequestParam(required = false, defaultValue = "${pageSize}", name = "pageSize") final int pageSize) {

        return new ResponseEntity<>(service.getMaterials(PageRequest.of(page, pageSize)), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get Material by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Material by id", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Material with the given ID does not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Material> getMaterial(@PathVariable final long id) {
        return new ResponseEntity<>(service.getMaterial(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remove Material by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete Material by id", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Material with the given ID does not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Material> removeMaterial(@PathVariable final long id) {
        service.removeMaterialById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add Material")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "add Material", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "409", description = "Material with the given ID already exists",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Material> addMaterial(@Valid @RequestBody final Material material) {
        System.out.println(material);
        return new ResponseEntity<>(service.addMaterial(material), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update Material")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update Material", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Material with the given ID dose not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Material> updateMaterial(@PathVariable final long id, @Valid @RequestBody final Material material) {
        return new ResponseEntity<>(service.updateMaterial(id, material), HttpStatus.OK);
    }


}
