package org.example.service;

import org.example.DTO.CatDto;
import org.example.mapper.CatMapper;
import org.example.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CatService {
    @Autowired
    private CatRepository catRepository;
    private CatMapper catMapper = new CatMapper();

    public CatDto save(CatDto catDto) {
        catRepository.save(catMapper.dtoToEntity(catDto));
        return catDto;
    }

    public void deleteById(@PathVariable long id){
        catRepository.deleteById(id);
    }

    public void deleteAll(){
        catRepository.deleteAll();
    }

    public CatDto getById(@PathVariable long id){
        return catMapper.entityToDto(catRepository.getById(id));
    }

    public List<CatDto> getAll(){
        return catMapper.entitysToDtos(catRepository.findAll());
    }

    public List<CatDto> getAllByOwnerId(Long id){
        return catMapper.entitysToDtos(catRepository.getAllByOwnerId(id));
    }

    public List<CatDto> getAllByName(String name){
        return catMapper.entitysToDtos(catRepository.getAllByName(name));
    }


}
