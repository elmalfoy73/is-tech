package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.dto.FleaDto;
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
@RequestMapping("/api/flea")
@Tag(name = "flea")
@AllArgsConstructor
public class FleaController {

    private final RabbitTemplate template;

    private final DecodeTokenUtil decodeTokenUtil;

    @Operation(summary = "save")
    @PostMapping("/")
    public void save(FleaDto fleaDto) {
        template.convertAndSend("addFleaQueue", fleaDto);
    }

    @Operation(summary = "deleteById")
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(id, username);
        template.convertAndSend("deleteFleaByIdQueue bcx", requestTemplate);
    }

    @Operation(summary = "deleteAll")
    @DeleteMapping("/")
    void deleteAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(username);
        requestTemplate.setRoute("getAllQueue");
        template.convertSendAndReceive("deleteAllFleaQueue", requestTemplate);
    }

    @Operation(summary = "getById")
    @GetMapping("/{id}")
    FleaDto getById(@PathVariable long id, HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(id, username);
        requestTemplate.setRoute("getFleaByIdQueue");
        return (FleaDto) template.convertSendAndReceive("getFleaByIdQueue", requestTemplate);

    }

    @Operation(summary = "getAll")
    @GetMapping("/")
    List<FleaDto> getAll(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(username);
        requestTemplate.setRoute("getAllFleaQueue");
        return (ArrayList<FleaDto>) template.convertSendAndReceive("getAllFleaQueue", requestTemplate);
    }

    @Operation(summary = "getAllByCatId")
    @GetMapping("/byCatId")
    List<FleaDto> getAllByCatId(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(id, username);
        requestTemplate.setRoute("getAllFleaByCatIdQueue");
        return (ArrayList<FleaDto>) template.convertSendAndReceive("getAllFleaByCatIdQueue", requestTemplate);
    }

    @Operation(summary = "getAllByName")
    @GetMapping("/byName")
    List<FleaDto> getAllByName(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(id, username);
        requestTemplate.setRoute("getFleaByNameQueue");
        return (ArrayList<FleaDto>) template.convertSendAndReceive("getFleaByNameQueue", requestTemplate);
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(ChangeSetPersister.NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }
}
