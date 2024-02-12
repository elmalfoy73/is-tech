package org.example;

import lombok.AllArgsConstructor;
import org.example.dto.CatDto;
import org.example.other.RequestTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CatListener {
    private final CatService catService;

    @RabbitListener(queues = "addCatQueue")
    public void save(@Payload CatDto catDTO) {
        catService.save(catDTO);
    }

    @RabbitListener(queues = "deleteCatByIdQueue")
    public void delete(RequestTemplate requestTemplate) {
        var parameters = requestTemplate.getParameters();
        catService.deleteById((long) parameters.get(0));
    }

    @RabbitListener(queues = "deleteAllCatQueue")
    public void deleteAll(RequestTemplate requestTemplate) {
        var parameters = requestTemplate.getParameters();
        catService.deleteAll();
    }

    @RabbitListener(queues = "getCatByIdQueue")
    public CatDto getById(@Payload RequestTemplate requestTemplate) {
        var parameters = requestTemplate.getParameters();
        return catService.getById((long) parameters.get(0));
    }

    @RabbitListener(queues = "getAllCatQueue")
    public List<CatDto> getAll(@Payload RequestTemplate requestTemplate) {
        return catService.getAll();
    }

    @RabbitListener(queues = "getAllCatByOwnerIdQueue")
    public List<CatDto> getAllByOwnerId(RequestTemplate requestTemplate) {
        var parameters = requestTemplate.getParameters();
        return catService.getAllByOwnerId((long) parameters.get(0));
    }

    @RabbitListener(queues = "getAllCatByNameQueue")
    public List<CatDto> getAllByName(RequestTemplate requestTemplate) {
        var parameters = requestTemplate.getParameters();
        return catService.getAllByName((String) parameters.get(0));
    }
}
