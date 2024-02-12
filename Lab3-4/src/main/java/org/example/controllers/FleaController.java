package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.DTO.FleaDto;
import org.example.service.FleaService;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flea")
@Tag(name = "flea")
public class FleaController {
    @Autowired
    private FleaService fleaService = new FleaService();

    @Operation(summary = "save")
    @PostMapping("/post/")
    public FleaDto save(FleaDto fleaDto) {
        fleaService.save(fleaDto);
        return fleaDto;
    }

    @Operation(summary = "deleteById")
    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable long id){
        fleaService.deleteById(id);
    }

    @Operation(summary = "deleteAll")
    @DeleteMapping("/delete/")
    void deleteAll(){
        fleaService.deleteAll();
    }

    @Operation(summary = "getById")
    @GetMapping("/get/{id}")
    FleaDto getById(@PathVariable long id){
        return fleaService.getById(id);
    }

    @Operation(summary = "getAll")
    @GetMapping("/get/")
    List<FleaDto> getAll(){
        return fleaService.getAll();
    }

    @Operation(summary = "getAllByCatId")
    @GetMapping("/get/byCatId")
    List<FleaDto> getAllByCatId(Long id){
        return fleaService.getAllByCatId(id);
    }

    @Operation(summary = "getAllByName")
    @GetMapping("/get/byName")
    List<FleaDto> getAllByName(String name){
        return fleaService.getAllByName(name);
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(ChangeSetPersister.NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }
}
