package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.dto.CatDto;
import org.example.other.RequestTemplate;
import org.example.security.DecodeTokenUtil;
import org.springdoc.api.ErrorMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cat")
@Tag(name = "cat")
@AllArgsConstructor
public class CatController {


    private final RabbitTemplate template;

    private final DecodeTokenUtil decodeTokenUtil;


    @Operation(summary = "save")
    @PostMapping("/")
    public void save(CatDto catDto) {
        template.convertAndSend("addCatQueue", catDto);
    }

    @Operation(summary = "deleteById")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
            String username = decodeTokenUtil.decodeToken(request, response);
            RequestTemplate requestTemplate = new RequestTemplate(id, username);
            template.convertAndSend("deleteCatByIdQueue", requestTemplate);
    }

    @Operation(summary = "deleteAll")
    @DeleteMapping("/")
    public void deleteAll(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(username);
        requestTemplate.setRoute("deleteAllCatQueue");
        template.convertSendAndReceive("deleteAllCatQueue", requestTemplate);
    }

    @Operation(summary = "getById")
    @GetMapping("/{id}")
    public CatDto getById(@PathVariable long id, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(id, username);
        requestTemplate.setRoute("getByIdQueue");
        return (CatDto) template.convertSendAndReceive("getCatByIdQueue", requestTemplate);

    }

    @Operation(summary = "getAll")
    @GetMapping("/")
    public List<CatDto> getAll(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(username);
        requestTemplate.setRoute("getAllCatQueue");
        return (ArrayList<CatDto>) template.convertSendAndReceive("getAllCatQueue", requestTemplate);
    }

    @Operation(summary = "getAllByOwnerId")
    @GetMapping("/byOwnerId")
    public List<CatDto> getAllByOwnerId(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(id, username);
        requestTemplate.setRoute("getAllCatByOwnerIdQueue");
        return (ArrayList<CatDto>) template.convertSendAndReceive("getAllCatByOwnerIdQueue", requestTemplate);
    }

    @Operation(summary = "getAllByName")
    @GetMapping("/byName")
    List<CatDto> getAllByName(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(id, username);
        requestTemplate.setRoute("getAllCatByNameQueue");
        return (ArrayList<CatDto>) template.convertSendAndReceive("getAllCatByNameQueue", requestTemplate);
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(ChangeSetPersister.NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }
}
