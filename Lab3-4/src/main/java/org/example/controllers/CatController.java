package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.DTO.CatDto;
import org.example.service.CatService;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cat")
@Tag(name = "cat")
public class CatController {

    @Autowired
    private CatService catService;

    @Operation(summary = "save")
    @PostMapping("/post/")
    public CatDto save(CatDto catDto) {
        catService.save(catDto);
        return catDto;
    }

    @Operation(summary = "deleteById")
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable long id){
        catService.deleteById(id);
    }

    @Operation(summary = "deleteAll")
    @DeleteMapping("/delete/")
    public void deleteAll(){
        catService.deleteAll();
    }

    @Operation(summary = "getById")
    @GetMapping("/get/{id}")
    public CatDto getById(@PathVariable long id){
        return catService.getById(id);
    }

    @Operation(summary = "getAll")
    @GetMapping("/get/")
    public List<CatDto> getAll(){
        return catService.getAll();
    }

    @Operation(summary = "getAllByOwnerId")
    @GetMapping("/get/byOwnerId")
    public List<CatDto> getAllByOwnerId(Long id){
        return catService.getAllByOwnerId(id);
    }

    @Operation(summary = "getAllByName")
    @GetMapping("/get/byName")
    List<CatDto> getAllByName(String name){
        return catService.getAllByName(name);
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(ChangeSetPersister.NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }
}
