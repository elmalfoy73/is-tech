package org.example.service;

import org.example.DTO.OwnerDto;
import org.example.entity.Owner;
import org.example.mapper.OwnerMapper;
import org.example.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    private OwnerMapper ownerMapper = new OwnerMapper();

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
        return ownerMapper.entitysToDtos(ownerRepository.findAll());
    }

    public List<OwnerDto> getAllByName(String name){
        return ownerMapper.entitysToDtos(ownerRepository.getAllByName(name));
    }
}
