package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.DTO.OwnerDto;
import org.example.service.OwnerService;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owner")
@Tag(name = "owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Operation(summary = "save")
    @PostMapping("/post/")
    public OwnerDto save(OwnerDto ownerDto) {
        ownerService.save(ownerDto);
        return ownerDto;
    }

    @Operation(summary = "deleteById")
    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable long id){
        ownerService.deleteById(id);
    }

    @Operation(summary = "deleteAll")
    @DeleteMapping("/delete/")
    void deleteAll() {
        ownerService.deleteAll();
    }

    @Operation(summary = "getById")
    @GetMapping("/delete/{id}")
    OwnerDto getById(@PathVariable long id){
        return ownerService.getById(id);
    }

    @Operation(summary = "getAll")
    @GetMapping("/get/")
    List<OwnerDto> getAll() {
        return ownerService.getAll();
    }

    @Operation(summary = "getAllByName")
    @GetMapping("/get/byName")
    List<OwnerDto> getAllByName(String name){
        return ownerService.getAllByName(name);
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(ChangeSetPersister.NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }
}
