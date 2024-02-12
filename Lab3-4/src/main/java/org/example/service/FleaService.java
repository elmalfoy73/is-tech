package org.example.service;

import org.example.DTO.FleaDto;
import org.example.mapper.FleaMapper;
import org.example.repository.FleaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class FleaService {

    @Autowired
    private FleaRepository fleaRepository;

    private FleaMapper fleaMapper;

    public FleaDto save(FleaDto fleaDto) {
        fleaRepository.save(fleaMapper.dtoToEntity(fleaDto));
        return fleaDto;
    }


    public void deleteById(@PathVariable long id){
        fleaRepository.deleteById(id);
    }


    public void deleteAll(){
        fleaRepository.deleteAll();
    }


    public FleaDto getById(@PathVariable long id){
        return fleaMapper.entityToDto(fleaRepository.getById(id));
    }


    public List<FleaDto> getAll(){
        return fleaMapper.entitysToDtos(fleaRepository.findAll());
    }


    public List<FleaDto> getAllByCatId(Long id){
        return fleaMapper.entitysToDtos(fleaRepository.getAllByCatId(id));
    }


    public List<FleaDto> getAllByName(String name){
        return fleaMapper.entitysToDtos(fleaRepository.getAllByName(name));
    }


}
