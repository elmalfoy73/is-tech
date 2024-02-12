package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.dto.OwnerDto;
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
@RequestMapping("/api/owner")
@Tag(name = "owner")
@AllArgsConstructor
public class OwnerController {

    private final RabbitTemplate template;

    private final DecodeTokenUtil decodeTokenUtil;

    @Operation(summary = "save")
    @PostMapping("/")
    public void save(OwnerDto ownerDto) {
        template.convertAndSend("addCatQueue", ownerDto);
    }

    @Operation(summary = "deleteById")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(id, username);
        template.convertAndSend("deleteOwnerByIdQueue", requestTemplate);
    }

    @Operation(summary = "deleteAll")
    @DeleteMapping("/")
    public void deleteAll(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(username);
        requestTemplate.setRoute("deleteAllOwnerQueue");
        template.convertSendAndReceive("deleteAllOwnerQueue", requestTemplate);
    }

    @Operation(summary = "getById")
    @GetMapping("/{id}")
    public OwnerDto getById(@PathVariable long id, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(id, username);
        requestTemplate.setRoute("getOwnerByIdQueue");
        return (OwnerDto) template.convertSendAndReceive("getOwnerByIdQueue", requestTemplate);

    }

    @Operation(summary = "getAll")
    @GetMapping("/")
    public List<OwnerDto> getAll(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(username);
        requestTemplate.setRoute("getAllOwnerQueue");
        return (ArrayList<OwnerDto>) template.convertSendAndReceive("getAllOwnerQueue", requestTemplate);
    }

    @Operation(summary = "getAllByName")
    @GetMapping("/byName")
    List<OwnerDto> getAllByName(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(id, username);
        requestTemplate.setRoute("getOwnerByNameQueue");
        return (ArrayList<OwnerDto>) template.convertSendAndReceive("getOwnerByNameQueue", requestTemplate);
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(ChangeSetPersister.NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }
}
