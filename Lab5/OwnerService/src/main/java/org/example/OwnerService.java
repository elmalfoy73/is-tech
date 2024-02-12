package org.example;

import org.example.dto.OwnerDto;
import org.example.other.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    private final OwnerMapper ownerMapper;

    public OwnerService(OwnerRepository ownerRepository, OwnerMapper ownerMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }

    public OwnerDto save(OwnerDto ownerDto) {
        Owner cat = ownerMapper.dtoToEntity(ownerDto);
        ownerRepository.save(cat);
        return ownerDto;
    }

    public void deleteById(@PathVariable long id){
        ownerRepository.deleteById(id);
    }

    public void deleteAll() {
        ownerRepository.deleteAll();
    }

    public OwnerDto getById(@PathVariable long id){
        return ownerMapper.entityToDto(ownerRepository.getById(id));
    }

    public List<OwnerDto> getAll() {
        return ownerMapper.entitiesToDtos(ownerRepository.findAll());
    }

    public List<OwnerDto> getAllByName(String name){
        return ownerMapper.entitiesToDtos(ownerRepository.getAllByName(name));
    }
}
