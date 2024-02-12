package org.example;

import org.example.dto.OwnerDto;
import org.example.other.RequestTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OwnerListener {

    private final OwnerService ownerService;

    public OwnerListener(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RabbitListener(queues = "addOwnerQueue")
    public void save(@Payload OwnerDto ownerDTO) {
        ownerService.save(ownerDTO);
    }

    @RabbitListener(queues = "deleteOwnerByIdQueue")
    public void delete(RequestTemplate requestTemplate) {
        var parameters = requestTemplate.getParameters();
        ownerService.deleteById((long) parameters.get(0));
    }

    @RabbitListener(queues = "deleteAllOwnerQueue")
    public void deleteAll(RequestTemplate requestTemplate) {
        var parameters = requestTemplate.getParameters();
        ownerService.deleteAll();
    }

    @RabbitListener(queues = "getOwnerByIdQueue")
    public OwnerDto getById(@Payload RequestTemplate requestTemplate) {
        var parameters = requestTemplate.getParameters();
        return ownerService.getById((long) parameters.get(0));
    }

    @RabbitListener(queues = "getAllOwnerQueue")
    public List<OwnerDto> getAll(@Payload RequestTemplate requestTemplate) {
        return ownerService.getAll();
    }

    @RabbitListener(queues = "getOwnerByNameQueue")
    public List<OwnerDto> getAllByName(RequestTemplate requestTemplate) {
        var parameters = requestTemplate.getParameters();
        return ownerService.getAllByName((String) parameters.get(0));
    }
}
