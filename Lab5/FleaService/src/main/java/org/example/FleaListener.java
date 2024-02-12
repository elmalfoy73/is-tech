package org.example;

import lombok.AllArgsConstructor;
import org.example.dto.FleaDto;
import org.example.other.RequestTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FleaListener {

    private final FleaService fleaService;


    @RabbitListener(queues = "addFleaQueue")
    public void save(@Payload FleaDto fleaDTO) {
        fleaService.save(fleaDTO);
    }

    @RabbitListener(queues = "deleteFleaByIdQueue")
    public void delete(RequestTemplate requestTemplate) {
        var parameters = requestTemplate.getParameters();
        fleaService.deleteById((long) parameters.get(0));
    }

    @RabbitListener(queues = "deleteAllFleaQueue")
    public void deleteAll(RequestTemplate requestTemplate) {
        var parameters = requestTemplate.getParameters();
        fleaService.deleteAll();
    }

    @RabbitListener(queues = "getByIdQueue")
    public FleaDto getById(@Payload RequestTemplate requestTemplate) {
        var parameters = requestTemplate.getParameters();
        return fleaService.getById((long) parameters.get(0));
    }

    @RabbitListener(queues = "getAllFleaQueue")
    public List<FleaDto> getAll(@Payload RequestTemplate requestTemplate) {
        return fleaService.getAll();
    }

    @RabbitListener(queues = "getAllFleaByCatIdQueue")
    public List<FleaDto> getAllByCatId(RequestTemplate requestTemplate) {
        var parameters = requestTemplate.getParameters();
        return fleaService.getAllByCatId((long) parameters.get(0));
    }

    @RabbitListener(queues = "getFleaByNameQueue")
    public List<FleaDto> getAllByName(RequestTemplate requestTemplate) {
        var parameters = requestTemplate.getParameters();
        return fleaService.getAllByName((String) parameters.get(0));
    }
}
