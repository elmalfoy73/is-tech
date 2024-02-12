package org.example;

import lombok.AllArgsConstructor;
import org.example.dto.FleaDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@AllArgsConstructor
public class FleaService {


    private final FleaRepository fleaRepository;

    private final FleaMapper fleaMapper;


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
        return fleaMapper.entitiesToDtos(fleaRepository.findAll());
    }


    public List<FleaDto> getAllByCatId(Long id){
        return fleaMapper.entitiesToDtos(fleaRepository.getAllByCatId(id));
    }


    public List<FleaDto> getAllByName(String name){
        return fleaMapper.entitiesToDtos(fleaRepository.getAllByName(name));
    }


}
