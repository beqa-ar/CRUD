package dev.omedia.controllers;

import dev.omedia.domains.Payment;
import dev.omedia.services.PaymentService;
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
@RequestMapping("payments")
public class PaymentController {
    private final PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get Payments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Payments", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Iterable<Payment>> getPayments(
            @Min(value = 0, message = "incorrect value") @Parameter(description = "page index(start from 0)")
            @RequestParam(required = false, defaultValue = "${page}", name = "page") final int page

            , @Min(30) @Max(500) @Parameter(description = "page size(min 30  max 500)")
            @RequestParam(required = false, defaultValue = "${pageSize}", name = "pageSize") final int pageSize) {

        return new ResponseEntity<>(service.getPayments(PageRequest.of(page, pageSize)), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get Payment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Payment by id", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Payment with the given ID does not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Payment> getPayment(@PathVariable final long id) {
        return new ResponseEntity<>(service.getPayment(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remove Payment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete Payment by id", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Payment with the given ID does not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Payment> removePayment(@PathVariable final long id) {
        service.removePaymentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add Payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "add Payment", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "409", description = "Payment with the given ID already exists",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Payment> addPayment(@Valid @RequestBody final Payment payment) {
        System.out.println(payment);
        return new ResponseEntity<>(service.addPayment(payment), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update Payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update Payment", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Payment with the given ID dose not exist",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<Payment> updatePayment(@PathVariable final long id, @Valid @RequestBody final Payment payment) {
        return new ResponseEntity<>(service.updatePayment(id, payment), HttpStatus.OK);
    }


}
