package dev.omedia.controllers;

import dev.omedia.domains.Automobile;
import dev.omedia.services.AutomobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("automobiles")
public class AutomobileController {
    private final AutomobileService service;

    @Autowired
    public AutomobileController(AutomobileService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<Automobile>> getAutomobiles() {
        return new ResponseEntity<>(service.getAutomobiles(), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<Automobile>  getAutomobile( @PathVariable final  long id) {
        return new ResponseEntity<>(service.getAutomobile(id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Automobile> removeAutomobile( @PathVariable final long id) {
        service.removeAutomobileById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Automobile> addAutomobile(@Valid @RequestBody final Automobile automobile) {
        System.out.println(automobile);
        return new ResponseEntity<>(service.addAutomobile(automobile),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Automobile> updateAutomobile(@PathVariable final long id,@Valid @RequestBody final Automobile automobile) {
        return new ResponseEntity<>(service.updateAutomobile(id,automobile),HttpStatus.CREATED);
    }

}
